package hu.progmasters.kingfishair.config.oauth2;

import hu.progmasters.kingfishair.domain.RegisteredUser;
import hu.progmasters.kingfishair.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
public class CustomOIdCUserService extends OidcUserService {


    private UserService userService;
@Autowired
    public CustomOIdCUserService(UserService userService) {
        this.userService = userService;
    }
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        String email = oidcUser.getAttribute("email");
        try {
            RegisteredUser registeredUser = userService.findUserAccountByEmail(email);
            return new CustomOIdCUser(registeredUser, oidcUser);
        } catch (EntityNotFoundException exception) {
            RegisteredUser registeredUser = userService.registerNewUserFromOidc(oidcUser);
            return new CustomOIdCUser(registeredUser, oidcUser);
        }
    }

}
