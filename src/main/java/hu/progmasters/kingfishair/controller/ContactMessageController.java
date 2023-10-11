package hu.progmasters.kingfishair.controller;

import hu.progmasters.kingfishair.dto.adminContactUsMessages.ContactMessageFromRegisteredUserListItem;
import hu.progmasters.kingfishair.dto.adminContactUsMessages.ContactMessageFromVisitorListItem;
import hu.progmasters.kingfishair.dto.adminContactUsMessages.ContactMessageReplyCommand;
import hu.progmasters.kingfishair.dto.outgoing.ReservationListItemForUser;
import hu.progmasters.kingfishair.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class ContactMessageController {


    private ContactMessageService contactMessageService;

    @Autowired
    public ContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }


    @GetMapping("/registered-unhandled")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<ContactMessageFromRegisteredUserListItem>> getUnhandledRequestForRegistered() {

        return new ResponseEntity<>(contactMessageService.getUnhandledRequestForRegistered(), HttpStatus.OK);
    }

    @GetMapping("/registered-handled")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<ContactMessageFromRegisteredUserListItem>> getHandledRequestForRegistered() {

        return new ResponseEntity<>(contactMessageService.getHandledRequestForRegistered(), HttpStatus.OK);
    }

    @GetMapping("/visitor-unhandled")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<ContactMessageFromVisitorListItem>> getUnhandledRequestForVisitor() {

        return new ResponseEntity<>(contactMessageService.getUnhandledRequestForVisitor(), HttpStatus.OK);
    }

    @GetMapping("/visitor-handled")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<ContactMessageFromVisitorListItem>> getHandledRequestForVisitor() {

        return new ResponseEntity<>(contactMessageService.getHandledRequestForVisitor(), HttpStatus.OK);
    }

    @PutMapping("/save-reply")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> saveReply(@RequestBody ContactMessageReplyCommand replyCommand) {
        contactMessageService.saveReply(replyCommand);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
