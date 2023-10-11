package hu.progmasters.kingfishair.domain;

public enum MessageStatus {

    HANDLED("handled"),

    UNHANDLED("unhandled");

    private final String messageStatus;

    MessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getMessageStatus() {
        return messageStatus;
    }
}
