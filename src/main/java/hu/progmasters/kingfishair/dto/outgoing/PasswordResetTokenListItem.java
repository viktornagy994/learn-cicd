package hu.progmasters.kingfishair.dto.outgoing;

import hu.progmasters.kingfishair.domain.PasswordResetToken;

public class PasswordResetTokenListItem {

    private boolean used;

    public PasswordResetTokenListItem(PasswordResetToken passwordResetToken) {
        this.used = passwordResetToken.isUsed();
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
