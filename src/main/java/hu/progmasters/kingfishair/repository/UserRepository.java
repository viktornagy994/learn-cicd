package hu.progmasters.kingfishair.repository;

import hu.progmasters.kingfishair.domain.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<RegisteredUser,Long> {
    @Query("select u from RegisteredUser u where u.email = :email")
    Optional<RegisteredUser> findByEmail(@Param("email") String email);
}
