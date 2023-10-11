package hu.progmasters.kingfishair.dto.outgoing;

import hu.progmasters.kingfishair.domain.RegisteredUser;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class UserListItem {

    private Long id;

    private String email;

    private String name;

    private String address;

    private String phoneNumber;

    public UserListItem(RegisteredUser registeredUser) {
        this.id = registeredUser.getId();
        this.email = registeredUser.getEmail();
        this.name = registeredUser.getName();
        this.address = registeredUser.getAddress();
        this.phoneNumber = registeredUser.getPhoneNumber();
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
}
