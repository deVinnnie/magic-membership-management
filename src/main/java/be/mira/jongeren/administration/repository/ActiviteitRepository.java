package be.mira.jongeren.administration.repository;

import be.mira.jongeren.administration.domain.Activiteit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiviteitRepository extends JpaRepository<Activiteit, Long> {
}
