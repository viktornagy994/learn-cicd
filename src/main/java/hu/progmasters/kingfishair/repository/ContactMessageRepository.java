package hu.progmasters.kingfishair.repository;

import hu.progmasters.kingfishair.domain.ContactMessage;
import hu.progmasters.kingfishair.domain.MessageStatus;
import hu.progmasters.kingfishair.dto.adminContactUsMessages.ContactMessageFromRegisteredUserListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    @Query("select c from ContactMessage c where c.customer != null and c.messageStatus= :status")
    List<ContactMessage> getContactMessagesByCustomerAndUnhandled(@Param("status")MessageStatus status);

    @Query("select c from ContactMessage c where c.customer != null and c.messageStatus= :status")
    List<ContactMessage> getContactMessagesByCustomerAndHandled(@Param("status")MessageStatus status);


    @Query("select c from ContactMessage c where c.customer = null and c.messageStatus= :status")
    List<ContactMessage> getContactMessagesByVisitorAndUnhandled(@Param("status")MessageStatus status);

    @Query("select c from ContactMessage c where c.customer = null and c.messageStatus= :status")
    List<ContactMessage> getContactMessagesByVisitorAndHandled(@Param("status")MessageStatus status);

}
