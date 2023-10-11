package hu.progmasters.kingfishair.dto.incoming;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class UserRegisterCommand {


    private String email;

    private String name;

    private String address;

    private String phoneNumber;

    private String password;

    public UserRegisterCommand(String email, String name, String address, String phoneNumber, String password) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public UserRegisterCommand() {
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
}
