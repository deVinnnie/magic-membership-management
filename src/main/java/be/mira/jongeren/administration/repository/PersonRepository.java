package be.mira.jongeren.administration.repository;

import be.mira.jongeren.administration.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
