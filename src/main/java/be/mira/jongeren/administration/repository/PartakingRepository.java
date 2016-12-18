package be.mira.jongeren.administration.repository;

import be.mira.jongeren.administration.domain.Partaking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartakingRepository extends JpaRepository<Partaking, Long> {
}
