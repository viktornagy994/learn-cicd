package hu.progmasters.kingfishair.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
public class PasswordResetToken {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private String token;

    @OneToOne(targetEntity = RegisteredUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private RegisteredUser user;

    private Date expiryDate;

    private boolean isUsed;

    public PasswordResetToken(RegisteredUser user, Date expiryDate) {
        this.token = UUID.randomUUID().toString();
        this.user = user;
        this.expiryDate = expiryDate;
    }

    public PasswordResetToken() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RegisteredUser getUser() {
        return user;
    }

    public void setUser(RegisteredUser user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
