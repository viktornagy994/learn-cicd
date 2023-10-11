package hu.progmasters.kingfishair.dto.incoming;

public class EmailForRequestPasswordReset {

    private String username;

    public EmailForRequestPasswordReset(String username) {
        this.username = username;
    }

    public EmailForRequestPasswordReset() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
