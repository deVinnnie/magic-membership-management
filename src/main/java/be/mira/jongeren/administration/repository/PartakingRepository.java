package be.mira.jongeren.administration.repository;

import be.mira.jongeren.administration.domain.Partaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PartakingRepository extends JpaRepository<Partaking, Long> {

    @Query("SELECT p FROM Partaking p WHERE p.person.id = :id")
    List<Partaking> findForPerson(
            @Param("id") UUID id
    );
}
