package be.mira.jongeren.administration.repository;

import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.domain.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

     @Query(
        "SELECT event " +
        "FROM Event event " +
        "WHERE YEAR(event.datum) = :year AND (:type is null OR event.eventType = :type)"
     )
     List<Event> findByYear(
         @Param("year") int year,
         @Param("type") EventType type
     );
}
