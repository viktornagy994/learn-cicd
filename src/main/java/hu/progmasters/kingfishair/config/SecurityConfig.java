package hu.progmasters.kingfishair.config;


import hu.progmasters.kingfishair.config.oauth2.CustomOIdCUserService;
import hu.progmasters.kingfishair.config.oauth2.CustomOauth2UserService;
import hu.progmasters.kingfishair.config.oauth2.CustomSuccessHandler;
import hu.progmasters.kingfishair.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.Arrays;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
@EnableWebSecurity
public class SecurityConfig extends GlobalMethodSecurityConfiguration {

    @Value("${cors-policies}")
    private String[] corsPolicies;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    private CustomSuccessHandler customSuccessHandler;

    private CustomOauth2UserService customOauth2UserService;

    private CustomOIdCUserService customOIdCUserService;



    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, UserService userService, CustomSuccessHandler customSuccessHandler, CustomOauth2UserService customOauth2UserService, CustomOIdCUserService customOIdCUserService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;

        this.customSuccessHandler = customSuccessHandler;
        this.customOauth2UserService = customOauth2UserService;
        this.customOIdCUserService = customOIdCUserService;
    }

   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        configureBasicAuth(http);
       configureOauth(http);


        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    private void configureBasicAuth(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/api/users/login").permitAll()
                .anyRequest().permitAll()
                .and().httpBasic()
                .and().logout().logoutUrl("/api/users/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
        ;
        //@formatter:on
    }


    private void configureOauth(HttpSecurity http) throws Exception {
        //@formatter:off
        http.oauth2Login()

                .authorizationEndpoint().baseUri("/api/oauth2/authorization")
                .and()
                .redirectionEndpoint()
                .baseUri("/api/login/oauth2/code/*")
                .and().userInfoEndpoint()
                .oidcUserService(customOIdCUserService)
                .userService(customOauth2UserService)
                .and()
                .successHandler(customSuccessHandler)

        ;
        //@formatter:on
    }




    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        // @formatter:off
        CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList(corsPolicies));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
            configuration.setAllowCredentials(true);
            configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
        return source;
        // @formatter:on
    }


}
