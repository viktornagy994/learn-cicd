package hu.progmasters.kingfishair.service;

import hu.progmasters.kingfishair.domain.ContactMessage;
import hu.progmasters.kingfishair.domain.MessageStatus;
import hu.progmasters.kingfishair.domain.RegisteredUser;
import hu.progmasters.kingfishair.dto.adminContactUsMessages.ContactMessageFromRegisteredUserListItem;
import hu.progmasters.kingfishair.dto.adminContactUsMessages.ContactMessageFromVisitorListItem;
import hu.progmasters.kingfishair.dto.adminContactUsMessages.ContactMessageReplyCommand;
import hu.progmasters.kingfishair.dto.incoming.ContactMessageCommand;
import hu.progmasters.kingfishair.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactMessageService {

    private ContactMessageRepository contactMessageRepository;
    private UserService userService;

    private EmailSenderService senderService;


    @Autowired
    public ContactMessageService(ContactMessageRepository contactMessageRepository, UserService userService, EmailSenderService senderService) {
        this.contactMessageRepository = contactMessageRepository;
        this.userService = userService;
        this.senderService = senderService;
    }


    public void saveMessage(ContactMessageCommand contactMessageCommand) {

        ContactMessage contactMessage = new ContactMessage(contactMessageCommand);

        if (userService.findAuthenticatedUsersAccount()!=null){
            RegisteredUser registeredUser = userService.findAuthenticatedUsersAccount();
            contactMessage.setCustomer(registeredUser);
        }

        contactMessageRepository.save(contactMessage);
    }

    public List<ContactMessageFromRegisteredUserListItem> getUnhandledRequestForRegistered() {
        MessageStatus status = MessageStatus.UNHANDLED;
        return contactMessageRepository.getContactMessagesByCustomerAndUnhandled(status).stream()
                .map(ContactMessageFromRegisteredUserListItem::new).collect(Collectors.toList());
    }

    public List<ContactMessageFromRegisteredUserListItem> getHandledRequestForRegistered() {
        MessageStatus status = MessageStatus.HANDLED;
        return contactMessageRepository.getContactMessagesByCustomerAndHandled(status).stream()
                .map(ContactMessageFromRegisteredUserListItem::new).collect(Collectors.toList());

    }

    public List<ContactMessageFromVisitorListItem> getUnhandledRequestForVisitor() {
        MessageStatus status = MessageStatus.UNHANDLED;
        return contactMessageRepository.getContactMessagesByVisitorAndUnhandled(status).stream()
                .map(ContactMessageFromVisitorListItem::new).collect(Collectors.toList());
    }

    public List< ContactMessageFromVisitorListItem> getHandledRequestForVisitor() {
        MessageStatus status = MessageStatus.HANDLED;
        return contactMessageRepository.getContactMessagesByVisitorAndHandled(status).stream()
                .map(ContactMessageFromVisitorListItem::new).collect(Collectors.toList());
    }

    public void saveReply(ContactMessageReplyCommand replyCommand) {

        ContactMessage contactMessage = contactMessageRepository.findById(replyCommand.getMessageId()).orElseThrow();
        contactMessage.setMessageStatus(MessageStatus.HANDLED);
        contactMessageRepository.save(contactMessage);
        this.senderService.sendEmail(replyCommand.getClientEmail(), "Your request has been processed", replyCommand.getReply());

    }
}
