package hu.progmasters.kingfishair.controller;

import hu.progmasters.kingfishair.domain.Gender;
import hu.progmasters.kingfishair.domain.MessageCategory;
import hu.progmasters.kingfishair.domain.RegisteredUser;
import hu.progmasters.kingfishair.dto.incoming.*;
import hu.progmasters.kingfishair.dto.outgoing.*;
import hu.progmasters.kingfishair.service.ContactMessageService;
import hu.progmasters.kingfishair.service.PasswordResetTokenService;
import hu.progmasters.kingfishair.service.ReCaptchaService;
import hu.progmasters.kingfishair.service.UserService;
import hu.progmasters.kingfishair.validator.EmailForRequestPasswordResetValidator;
import hu.progmasters.kingfishair.validator.PasswordForResetCommandValidator;
import hu.progmasters.kingfishair.validator.UserRegisterCommandValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/users")

public class UserController {

    @Value("${frontend.url}")
    private String frontendUrl;

    private UserService userService;
    private PasswordResetTokenService passwordResetTokenService;

    private ReCaptchaService reCaptchaService;

    private EmailForRequestPasswordResetValidator emailForRequestPasswordResetValidator;

    private UserRegisterCommandValidator userRegisterCommandValidator;

    private PasswordForResetCommandValidator passwordForResetCommandValidator;

    private ContactMessageService contactMessageService;


    @Autowired
    public UserController(UserService userService, PasswordResetTokenService passwordResetTokenService, ReCaptchaService reCaptchaService, EmailForRequestPasswordResetValidator emailForRequestPasswordResetValidator, UserRegisterCommandValidator userRegisterCommandValidator, PasswordForResetCommandValidator passwordForResetCommandValidator, ContactMessageService contactMessageService) {
        this.userService = userService;
        this.passwordResetTokenService = passwordResetTokenService;

        this.reCaptchaService = reCaptchaService;
        this.emailForRequestPasswordResetValidator = emailForRequestPasswordResetValidator;
        this.userRegisterCommandValidator = userRegisterCommandValidator;
        this.passwordForResetCommandValidator = passwordForResetCommandValidator;
        this.contactMessageService = contactMessageService;
    }

    @InitBinder("emailForRequestPasswordReset")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(emailForRequestPasswordResetValidator);
    }

    @InitBinder("userRegisterCommand")
    public void initBinder2(WebDataBinder binder) {
        binder.addValidators(userRegisterCommandValidator);
    }

    @InitBinder("passwordForResetCommand")
    public void initBinder3(WebDataBinder binder) {
        binder.addValidators(passwordForResetCommandValidator);
    }

    @PostMapping("/registration")
    public ResponseEntity<SimpleIdResponse> registerCustomUser(@RequestBody @Valid UserRegisterCommand userRegisterCommand) throws MessagingException {


        return new ResponseEntity<>(userService.registerCustomUser(userRegisterCommand), HttpStatus.CREATED);
    }

    @PostMapping("/contact-us")
    public ResponseEntity<Void>  saveContactMessage(@RequestBody @Valid ContactMessageCommand contactMessageCommand) {


        contactMessageService.saveMessage(contactMessageCommand);

        return new ResponseEntity<>( HttpStatus.CREATED);
    }



    @GetMapping("/registration/{token}")
    public ResponseEntity<Void> VerifyUser(@PathVariable String token, HttpServletResponse response) throws IOException {
        userService.verifyUser(token);
        response.sendRedirect(frontendUrl+"/login");
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/set-new-password/{id}")
    public ResponseEntity<Void> setNewPassword(@PathVariable String id, @RequestBody @Valid PasswordForResetCommand passwordCommand) {
        userService.verifyPasswordReset(id);
        userService.saveNewPassword(id, passwordCommand);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/forgotten-password-email")
    public ResponseEntity<Void> emailSendForForgottenPassword(@RequestBody @Valid EmailForRequestPasswordReset email) {
        userService.emailSendForForgottenPassword(email);
        return new ResponseEntity<>(HttpStatus.OK);


    }

    @GetMapping("/login")
    public ResponseEntity<RegisteredUserDetails> getUserInfo(@RequestParam String captchaToken) {

        if (!reCaptchaService.isValidToken(captchaToken)){
            return ResponseEntity.badRequest().build();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails user = (UserDetails) authentication.getPrincipal();
            String username = userService.getAuthenticatedUser().getName();
            RegisteredUserDetails registeredUserDetails = new RegisteredUserDetails(user, username);


            return new ResponseEntity<RegisteredUserDetails>(registeredUserDetails, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<SimpleIdResponse> userInfo() {

        return new ResponseEntity<>(new SimpleIdResponse(userService.findAuthenticatedUsersAccount().getId()), HttpStatus.OK);
    }


    @GetMapping("/me-oauth2")
    public ResponseEntity<RegisteredUserDetails> getUserInfoOauth2() {
        RegisteredUser userAccount = userService.findAuthenticatedUsersAccount();
        if (userAccount != null) {
            return ResponseEntity.ok(new RegisteredUserDetails(userService.findAuthenticatedUsersAccount()));
        } else return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/my-profile")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<UserListItem> getUserListItem() {

        return new ResponseEntity<>(userService.getUserListItem(), HttpStatus.OK);
    }

    @PutMapping("/update-profile")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Void> modifyUserInfo(@RequestBody UpdateUserCommand command) {
        userService.updateUser(command);
        return new ResponseEntity<>( HttpStatus.OK);
    }


    @GetMapping("/password-reset-check/{token}")
    public ResponseEntity<PasswordResetTokenListItem> getPasswordResetTokenStatus(@PathVariable String token, HttpServletResponse response) throws IOException {

        return new ResponseEntity<>(passwordResetTokenService.checkStatus(token),HttpStatus.OK);

    }

    @GetMapping("/get-contact-form-data-categories")
    public ResponseEntity<ContactMessageFormData> getContactFormData() {
        ContactMessageFormData contactMessageFormData = new ContactMessageFormData(getCategories());
        return new ResponseEntity<>(contactMessageFormData, HttpStatus.OK);
    }

    private List<MessageCategoryOption> getCategories() {
        List<MessageCategoryOption> categoryOptions = new ArrayList<>();
        for (MessageCategory messageCategory : MessageCategory.values()) {
            categoryOptions.add(new MessageCategoryOption(messageCategory));
        }
        return categoryOptions;
    }


}




