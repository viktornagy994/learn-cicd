package hu.progmasters.kingfishair.dto.outgoing;

import java.util.List;

public class ContactMessageFormData {


    private List<MessageCategoryOption> categories;


    public ContactMessageFormData(List<MessageCategoryOption> categories) {
        this.categories = categories;
    }

    public ContactMessageFormData() {
    }

    public List<MessageCategoryOption> getCategories() {
        return categories;
    }

    public void setCategories(List<MessageCategoryOption> categories) {
        this.categories = categories;
    }
}
