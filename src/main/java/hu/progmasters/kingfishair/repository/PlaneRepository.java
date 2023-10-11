package hu.progmasters.kingfishair.repository;

import hu.progmasters.kingfishair.domain.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends JpaRepository<Plane,Long> {
}
