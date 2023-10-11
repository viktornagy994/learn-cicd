package hu.progmasters.kingfishair.dto.outgoing;

import hu.progmasters.kingfishair.config.UserRole;
import hu.progmasters.kingfishair.domain.RegisteredUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class RegisteredUserDetails {


    private String email;
    private List<String> userRoles;
    private String name;


    public RegisteredUserDetails(UserDetails user, String name) {
        this.email = user.getUsername();
        this.userRoles = mapRoles(user);
        this.name = name;
    }

    public RegisteredUserDetails(RegisteredUser user) {
        this.email = user.getEmail();
        List<UserRole> roles = new ArrayList<>();
        roles.add(user.getUserRole());
        this.userRoles = roles.stream()
                .map(Objects::toString)
                .collect(Collectors.toList());
       this.name = user.getName();
    }

    private List<String> mapRoles(UserDetails user) {
        return user.getAuthorities()
                .stream()
                .filter(authority -> authority.getAuthority().startsWith("ROLE_"))
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
