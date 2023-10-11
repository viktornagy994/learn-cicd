package hu.progmasters.kingfishair.domain;

import hu.progmasters.kingfishair.config.UserRole;
import hu.progmasters.kingfishair.dto.incoming.UserRegisterCommand;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class RegisteredUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    @NotNull
    private String email;
    @Column
    @NotNull
    private String name;
    @Column
    @NotNull
    private String address;
    @Column
    @NotNull
    private String phoneNumber;
    @Column
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservationList;

    @OneToMany(mappedBy = "customer")
    private List<ContactMessage> messageList;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "password_reset_enabled")
    private boolean passwordResetEnabled;



    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public RegisteredUser(UserRegisterCommand userRegisterCommand) {
        this.id = id;
        this.email = userRegisterCommand.getEmail();
        this.name = userRegisterCommand.getName();
        this.address = userRegisterCommand.getAddress();
        this.phoneNumber = userRegisterCommand.getPhoneNumber();
        this.userRole = UserRole.ROLE_USER;
        this.reservationList = new ArrayList<>();
        this.passwordResetEnabled = false;
        this.enabled = false;
        this.messageList=new ArrayList<>();
    }

    public RegisteredUser(RegisteredUser user) {
        this.passwordResetEnabled = passwordResetEnabled;
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userRole = userRole;
        this.reservationList = reservationList;
        this.enabled = enabled;
    }
    public RegisteredUser(OAuth2User oAuth2User) {
        this.email = oAuth2User.getAttribute("email");
        this.name = oAuth2User.getAttribute("name");
        this.passwordResetEnabled = false;
        this.address = "";
        this.phoneNumber = "";
        this.password = "";
        this.userRole = UserRole.ROLE_USER;
        this.reservationList = new ArrayList<>();
        this.enabled = true;
    }

    public RegisteredUser(OidcUser oidcUser) {
        this.email = oidcUser.getEmail();
        this.name = oidcUser.getFullName();
        this.passwordResetEnabled = false;
        this.address = "";
        this.phoneNumber = "";
        this.password = "";
        this.userRole = UserRole.ROLE_USER;
        this.reservationList = new ArrayList<>();
        this.enabled = true;
    }


    public RegisteredUser() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public boolean isPasswordResetEnabled() {
        return passwordResetEnabled;
    }

    public void setPasswordResetEnabled(boolean passwordResetEnabled) {
        this.passwordResetEnabled = passwordResetEnabled;
    }

    public List<ContactMessage> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<ContactMessage> messageList) {
        this.messageList = messageList;
    }
}
