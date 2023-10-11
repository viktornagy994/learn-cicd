package hu.progmasters.kingfishair.repository;

import hu.progmasters.kingfishair.domain.PasswordResetToken;
import hu.progmasters.kingfishair.domain.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {

    @Query("select p.user from PasswordResetToken p where p.token = :token")
    public RegisteredUser findUserByToken(@Param("token") String token);


    @Query("select p from PasswordResetToken p where p.token = :token")
    public PasswordResetToken findPasswordResetTokenByToken(@Param("token") String token);
}
