package hu.progmasters.kingfishair.dto.incoming;

import hu.progmasters.kingfishair.domain.MessageCategory;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public class ContactMessageCommand {


    private String clientName;


    private String clientEmail;


    private String message;


    private String category;


    public ContactMessageCommand(String clientName, String clientEmail, String message, String category) {
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.message = message;
        this.category = category;
    }

    public ContactMessageCommand() {
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
