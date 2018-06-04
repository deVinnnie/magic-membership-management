package be.mira.jongeren.administration.rest;

import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.domain.Partaking;
import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.repository.EventRepository;
import be.mira.jongeren.administration.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/events/{eventId}/partakings")
public class PartakingsResource {

    @Autowired
    PersonRepository personRepository;
    
    @Autowired
    EventRepository eventRepository;

    @PostMapping(value = "")
    public void create(
        @PathVariable("eventId") Long eventId,
        @RequestBody PartakingDto partakingDto
    ){
        Event event = eventRepository.findOne(eventId);
        Person person = personRepository.findOne(partakingDto.getId());

        Partaking partaking = new Partaking(person, partakingDto.getType(), event);

        event.addPartaking(partaking);

        eventRepository.flush();
    }
}
