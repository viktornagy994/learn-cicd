package hu.progmasters.kingfishair.config.oauth2;

import hu.progmasters.kingfishair.config.UserRole;
import hu.progmasters.kingfishair.domain.RegisteredUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomOAuth2User implements OAuth2User {


    private OAuth2User oAuth2User;
    private List<GrantedAuthority> authorities;
    private Long userId;
    private Map<String, Object> attributes;
    private String id;
    private String name;
    private String login;
    private String email;


    public CustomOAuth2User(RegisteredUser registeredUser, OAuth2User oAuth2User) {
        this.userId = registeredUser.getId();
        this.oAuth2User = oAuth2User;
        this.id = String.valueOf(userId);
        this.name = oAuth2User.getAttribute("name");
        this.login = oAuth2User.getAttribute("login");
        this.email = oAuth2User.getAttribute("email");
        List<UserRole> roles = new ArrayList<>();
        roles.add(registeredUser.getUserRole());
        this.authorities = roles.stream()
                .map(Enum::toString)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    @Override
    public Map<String, Object> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new HashMap<>();
            this.attributes.put("id", this.getId());
            this.attributes.put("name", this.getName());
            this.attributes.put("login", this.getLogin());
            this.attributes.put("email", this.getEmail());
        }
        return attributes;
    }

    public OAuth2User getoAuth2User() {
        return oAuth2User;
    }

    public void setoAuth2User(OAuth2User oAuth2User) {
        this.oAuth2User = oAuth2User;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
