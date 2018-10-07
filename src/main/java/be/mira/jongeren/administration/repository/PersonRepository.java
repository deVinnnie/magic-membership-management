package be.mira.jongeren.administration.repository;

import be.mira.jongeren.administration.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    @Query("SELECT p FROM Person p WHERE LOWER(CONCAT(p.voornaam, ' ', p.achternaam)) LIKE CONCAT('%',LOWER(:name),'%')")
    List<Person> searchByName(@Param("name") String name);

}
