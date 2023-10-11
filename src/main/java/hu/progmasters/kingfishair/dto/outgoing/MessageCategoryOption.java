package hu.progmasters.kingfishair.dto.outgoing;

import hu.progmasters.kingfishair.domain.MessageCategory;

public class MessageCategoryOption {


    private String categoryName;

    private String messageCategory;


    public MessageCategoryOption(MessageCategory category) {
        this.categoryName = category.getDisplayName();

        this.messageCategory = category.toString();
    }

    public MessageCategoryOption() {
    }

    public String getCategory() {
        return categoryName;
    }

    public void setCategory(String category) {
        this.categoryName = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMessageCategory() {
        return messageCategory;
    }

    public void setMessageCategory(String messageCategory) {
        this.messageCategory = messageCategory;
    }
}
