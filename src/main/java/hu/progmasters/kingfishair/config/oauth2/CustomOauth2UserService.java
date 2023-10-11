package hu.progmasters.kingfishair.config.oauth2;

import hu.progmasters.kingfishair.domain.RegisteredUser;
import hu.progmasters.kingfishair.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {


    private UserService userService;
@Autowired
    public CustomOauth2UserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");
        try {
            RegisteredUser userAccount = userService.findUserAccountByEmail(email);
            return new CustomOAuth2User(userAccount, oAuth2User);
        } catch (EntityNotFoundException exception) {
            RegisteredUser userAccount = userService.registerNewUserFromOAuth2User(oAuth2User);
            return new CustomOAuth2User(userAccount, oAuth2User);
        }
    }
}
