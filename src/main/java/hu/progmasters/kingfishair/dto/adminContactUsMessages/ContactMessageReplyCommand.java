package hu.progmasters.kingfishair.dto.adminContactUsMessages;

public class ContactMessageReplyCommand {

    private String clientEmail;

    private String reply;

    private Long messageId;


    public ContactMessageReplyCommand() {
    }

    public ContactMessageReplyCommand(String clientEmail, String reply, Long messageId) {
        this.clientEmail = clientEmail;
        this.reply = reply;
        this.messageId = messageId;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
