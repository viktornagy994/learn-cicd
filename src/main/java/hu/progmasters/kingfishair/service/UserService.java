package hu.progmasters.kingfishair.service;

import hu.progmasters.kingfishair.config.oauth2.CustomOAuth2User;
import hu.progmasters.kingfishair.config.oauth2.CustomOIdCUser;
import hu.progmasters.kingfishair.domain.PasswordResetToken;
import hu.progmasters.kingfishair.domain.RegisteredUser;
import hu.progmasters.kingfishair.domain.VerificationToken;
import hu.progmasters.kingfishair.dto.incoming.EmailForRequestPasswordReset;
import hu.progmasters.kingfishair.dto.incoming.PasswordForResetCommand;
import hu.progmasters.kingfishair.dto.incoming.UpdateUserCommand;
import hu.progmasters.kingfishair.dto.incoming.UserRegisterCommand;
import hu.progmasters.kingfishair.dto.outgoing.SimpleIdResponse;
import hu.progmasters.kingfishair.dto.outgoing.UserListItem;
import hu.progmasters.kingfishair.repository.PasswordResetTokenRepository;
import hu.progmasters.kingfishair.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Value("${backend.url}")
    private String backendUrl;

    @Value("${frontend.url}")
    private String frontendUrl;

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private EmailSenderService senderService;
    private VerificationTokenService verificationTokenService;

    private PasswordResetTokenService passwordResetTokenService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailSenderService senderService, VerificationTokenService verificationTokenService, PasswordResetTokenService passwordResetTokenService,
                       PasswordResetTokenRepository passwordResetTokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.senderService = senderService;
        this.verificationTokenService = verificationTokenService;
        this.passwordResetTokenService = passwordResetTokenService;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        RegisteredUser user = userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);


        return User
                .withUsername(user.getEmail())
                .authorities(String.valueOf(user.getUserRole()))
                .password(user.getPassword())
                .build();
    }

    public RegisteredUser findUserAccountByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

    public RegisteredUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails loggedInUser = (User) authentication.getPrincipal();
        return userRepository.findByEmail(loggedInUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found by email."));
    }


    public SimpleIdResponse registerCustomUser(UserRegisterCommand userRegisterCommand) throws MessagingException {

        RegisteredUser registeredUser = new RegisteredUser(userRegisterCommand);
        registeredUser.setPassword(passwordEncoder.encode(userRegisterCommand.getPassword()));
        userRepository.save(registeredUser);
        emailForVerification(registeredUser);
        return new SimpleIdResponse(registeredUser.getId());
    }

    public RegisteredUser registerNewUserFromOAuth2User(OAuth2User oAuth2User) {
        RegisteredUser registeredUser = new RegisteredUser(oAuth2User);
        userRepository.save(registeredUser);
        return registeredUser;
    }

    public RegisteredUser registerNewUserFromOidc(OidcUser oidcUser) {

        RegisteredUser registeredUser = new RegisteredUser(oidcUser);
        userRepository.save(registeredUser);
        return registeredUser;
    }

    public VerificationToken createVerificationToken(RegisteredUser registeredUser, Date date) {

        return new VerificationToken(registeredUser, date);
    }

    public void emailForVerification(RegisteredUser registeredUser) throws MessagingException {
        Date date = new Date();
        Date expireDate = verificationTokenService.addDays(date, 30);
        VerificationToken verificationToken = createVerificationToken(registeredUser, expireDate);
        verificationTokenService.saveVerificationToken(verificationToken);
        String registrationLink = backendUrl + "/api/users/registration/" + verificationToken.getToken();
        senderService.sendWelcomeMailFromTemplateForRegistration(registeredUser.getEmail(),registeredUser.getName(),registrationLink);

    }

    public void verifyUser(String token) {

        RegisteredUser registeredUser = this.verificationTokenService.findUserByVerificationToken(token);
        registeredUser.setEnabled(true);
        this.userRepository.save(registeredUser);
    }

    public void verifyPasswordReset(String token) {

        RegisteredUser registeredUser = this.passwordResetTokenService.findUserByPasswordResetToken(token);
        registeredUser.setPasswordResetEnabled(true);
        this.userRepository.save(registeredUser);
    }

    public RegisteredUser findAuthenticatedUsersAccount() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                return findUserAccountByEmail(userDetails.getUsername());
            } else if (principal instanceof CustomOIdCUser) {
                CustomOIdCUser user = (CustomOIdCUser) principal;
                return findUserAccountByEmail(user.getAttributes().get("email").toString());
            } else if (principal instanceof CustomOAuth2User) {
                CustomOAuth2User user = (CustomOAuth2User) principal;
                return findUserAccountByEmail(user.getAttributes().get("email").toString());
            }
        }
        return null;
    }

    public UserListItem getUserListItem() {

        RegisteredUser registeredUser = findAuthenticatedUsersAccount();

        return new UserListItem(registeredUser);
    }

    public void updateUser(UpdateUserCommand command) {

        RegisteredUser registeredUser = findAuthenticatedUsersAccount();
        registeredUser.setName(command.getName());
        registeredUser.setAddress(command.getAddress());
        registeredUser.setPhoneNumber(command.getPhoneNumber());
        userRepository.save(registeredUser);
    }

    public void emailSendForForgottenPassword(EmailForRequestPasswordReset emailDTO) {

        Optional<RegisteredUser> optionalRegisteredUser = userRepository.findByEmail(emailDTO.getUsername());
        if (optionalRegisteredUser.isPresent()){
            RegisteredUser registeredUser = optionalRegisteredUser.get();
        emailForPasswordReset(registeredUser);}
    }

    public void emailForPasswordReset(RegisteredUser registeredUser) {
        Date date = new Date();
        Date expireDate = passwordResetTokenService.addDays(date, 30);
        PasswordResetToken passwordResetToken = createPasswordResetToken(registeredUser, expireDate);
        passwordResetToken.setUsed(false);
        passwordResetTokenService.savePasswordResetToken(passwordResetToken);
        String registrationLink = frontendUrl + "/set-new-password/" + passwordResetToken.getToken();
        senderService.sendEmail(registeredUser.getEmail(), "Password reset on kingfishair.progmasters.hu", "You have requested password reset for your account! Please click on this link to create a new password:" + registrationLink);

    }

    public PasswordResetToken createPasswordResetToken(RegisteredUser registeredUser, Date date) {

        return new PasswordResetToken(registeredUser, date);
    }

    public void saveNewPassword(String token, PasswordForResetCommand passwordCommand) {

        RegisteredUser registeredUser = passwordResetTokenService.findUserByPasswordResetToken(token);
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findPasswordResetTokenByToken(token);
        if (registeredUser.isPasswordResetEnabled()&&!passwordResetToken.isUsed()){
            registeredUser.setPassword(passwordEncoder.encode(passwordCommand.getPassword()));
            userRepository.save(registeredUser);
            passwordResetToken.setUsed(true);
            passwordResetTokenRepository.save(passwordResetToken);
        }
    }
}
