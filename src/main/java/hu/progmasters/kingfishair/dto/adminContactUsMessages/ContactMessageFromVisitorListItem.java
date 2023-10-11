package hu.progmasters.kingfishair.dto.adminContactUsMessages;

import hu.progmasters.kingfishair.domain.ContactMessage;

public class ContactMessageFromVisitorListItem {

    private Long messageId;

    private String visitorEmail;

    private String visitorFullName;

    private String message;

    private String messageCategory;


    public ContactMessageFromVisitorListItem(ContactMessage message) {
        this.messageId = message.getId();
        this.visitorEmail = message.getClientEmail();
        this.visitorFullName = message.getClientName();
        this.message = message.getMessage();
        this.messageCategory = message.getCategory().getDisplayName();
    }

    public ContactMessageFromVisitorListItem() {
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getVisitorEmail() {
        return visitorEmail;
    }

    public void setVisitorEmail(String visitorEmail) {
        this.visitorEmail = visitorEmail;
    }

    public String getVisitorFullName() {
        return visitorFullName;
    }

    public void setVisitorFullName(String visitorFullName) {
        this.visitorFullName = visitorFullName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageCategory() {
        return messageCategory;
    }

    public void setMessageCategory(String messageCategory) {
        this.messageCategory = messageCategory;
    }
}
