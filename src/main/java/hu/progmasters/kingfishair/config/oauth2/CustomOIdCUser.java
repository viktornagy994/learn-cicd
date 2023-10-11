package hu.progmasters.kingfishair.config.oauth2;

import hu.progmasters.kingfishair.config.UserRole;
import hu.progmasters.kingfishair.domain.RegisteredUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomOIdCUser implements OidcUser {

    private final Map<String, Object> claims;
    private final OidcUserInfo userInfo;
    private final OidcIdToken idToken;
    private final Map<String, Object> attributes;
    private final Collection<? extends GrantedAuthority> authorities;
    private final String name;
    private Long userId;

    public CustomOIdCUser(RegisteredUser registeredUser, OidcUser oidcUser) {
        this.userId = registeredUser.getId();
        claims = oidcUser.getClaims();
        userInfo = oidcUser.getUserInfo();
        idToken = oidcUser.getIdToken();
        attributes = oidcUser.getAttributes();
        name = registeredUser.getName();
        List<UserRole> roles = new ArrayList<>();
        roles.add(registeredUser.getUserRole());
        this.authorities = roles.stream()
                .map(Enum::toString)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getClaims() {
        return claims;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return userInfo;
    }

    @Override
    public OidcIdToken getIdToken() {
        return idToken;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
