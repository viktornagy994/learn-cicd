package hu.progmasters.kingfishair.config.oauth2;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Configuration("oauth2SuccessHandler")
@Data
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${successful-login-redirect-url}")
    private String successfulRedirectUrl;


    @Value("${frontend.url}")
    private String frontendUrl;

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        System.out.println(authentication.getPrincipal() +"---------------------------------------------------------------------------------");
//        response.sendRedirect("http://localhost:4200/home");
//    }


//    http://localhost:8080/api/users/mee




//
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomOIdCUser) {
            CustomOIdCUser customOidcUser = (CustomOIdCUser) principal;
//            if (customOidcUser.getVerificationCode() != null) {
//                response.sendRedirect(frontendUrl + "/registration/" + customOidcUser.getVerificationCode());
//            }
           redirectToDefault(response);
        }
        else  if (principal instanceof CustomOAuth2User) {
            CustomOAuth2User customOAuth2User = (CustomOAuth2User) principal;
//            if (customOAuth2User.getVerificationCode() != null) {
//                response.sendRedirect(frontendUrl + "/registration/" + customOAuth2User.getVerificationCode());
//            }
           redirectToDefault(response);
        }
        else redirectToDefault(response);
    }

    private void redirectToDefault(HttpServletResponse response) throws IOException {
        response.sendRedirect(frontendUrl+"/my-profile");
    }

}

