package hu.progmasters.kingfishair.dto.adminContactUsMessages;

import hu.progmasters.kingfishair.domain.ContactMessage;

public class ContactMessageFromRegisteredUserListItem {

    private Long messageId;

    private Long userId;

    private String userEmail;

    private String userFullName;

    private String message;

    private String messageCategory;

    public ContactMessageFromRegisteredUserListItem(ContactMessage message) {
        this.messageId = message.getId();
        this.userId = message.getCustomer().getId();
        this.userEmail = message.getCustomer().getEmail();
        this.userFullName = message.getCustomer().getName();
        this.message = message.getMessage();
        this.messageCategory = message.getCategory().getDisplayName();
    }

    public ContactMessageFromRegisteredUserListItem() {
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
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
