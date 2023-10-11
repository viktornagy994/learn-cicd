package hu.progmasters.kingfishair.repository;

import hu.progmasters.kingfishair.domain.RegisteredUser;
import hu.progmasters.kingfishair.domain.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {


@Query("select v.user from VerificationToken v where v.token = :token")
public RegisteredUser findUserByToken(@Param("token") String token);

}
