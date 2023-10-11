package hu.progmasters.kingfishair.domain;

import hu.progmasters.kingfishair.dto.incoming.ContactMessageCommand;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "messages")
public class ContactMessage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column()
    @NotNull
    private String clientName;

    @Column()
    @NotNull
    private String clientEmail;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_category")
    @NotNull
    private MessageCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_status")
    @NotNull
    private MessageStatus messageStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private RegisteredUser customer;

    public ContactMessage(ContactMessageCommand contactMessageCommand) {
        this.id = id;
        this.clientName = contactMessageCommand.getClientName();
        this.clientEmail = contactMessageCommand.getClientEmail();
        this.message = contactMessageCommand.getMessage();
        this.category = MessageCategory.valueOf(contactMessageCommand.getCategory());
        this.customer = null;
        this.messageStatus=MessageStatus.UNHANDLED;
    }


    public ContactMessage() {
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public MessageCategory getCategory() {
        return category;
    }

    public void setCategory(MessageCategory category) {
        this.category = category;
    }

    public RegisteredUser getCustomer() {
        return customer;
    }

    public void setCustomer(RegisteredUser customer) {
        this.customer = customer;
    }
}
