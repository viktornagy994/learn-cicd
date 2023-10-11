package hu.progmasters.kingfishair.dto.incoming;

public class PasswordForResetCommand {


    private String password;

    public PasswordForResetCommand(String password) {
        this.password = password;
    }

    public PasswordForResetCommand() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
