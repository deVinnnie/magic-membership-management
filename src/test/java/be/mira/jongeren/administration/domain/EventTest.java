package be.mira.jongeren.administration.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EventTest extends BeanValidatorTest{

    private Event.EventBuilder builder;
    private Person.PersonBuilder personBuilder;

    @BeforeEach
    void setUp(){
        this.builder = new Event.EventBuilder();
        this.personBuilder = new Person.PersonBuilder();
    }

    @Test
    void eventTypeNullGivesValidationError(){
        Event event = builder.type(null).build();
        Set<ConstraintViolation<Event>> violations = validator().validateProperty(event, "eventType");
        assertEquals(1, violations.size());
    }

    @Test
    void getNumberOfParticipantsReturnsZeroForEmptyPartakingsList(){
        Event event = builder.build();
        assertEquals(0, event.getNumberOfParticipants());
    }

    @Test
    void getNumberOfParticipantsReturnsOneForPartakingsListWithSingleParticipant(){
        Event event = EventMother.createEventWithSingleParticipant();
        assertEquals(1, event.getNumberOfParticipants());
    }

    @Test
    void getNumberOfPartakingsOfTypeReturnsCorrectResult(){
        Event event = builder.build();
        event.addPartakings(Arrays.asList(
            new Partaking(personBuilder.voornaam("Harry").build(), PartakingType.DEELNEMER, event),
            new Partaking(personBuilder.voornaam("Hermione").build(), PartakingType.EXTERN, event),
            new Partaking(personBuilder.voornaam("Ron").build(), PartakingType.LEIDING, event)
        ));

        assertEquals(1, event.getNumberOfPartakingsOfType(PartakingType.DEELNEMER));
        assertEquals(1, event.getNumberOfPartakingsOfType(PartakingType.EXTERN));
        assertEquals(1, event.getNumberOfPartakingsOfType(PartakingType.LEIDING));
    }

    @Test
    void addPartakingWithDuplicatePersonDoesNothing(){
        Person person = personBuilder
                            .voornaam("Hermione")
                            .achternaam("Granger")
                            .build();
        Event event = builder.build();
        event.addPartaking(new Partaking(
            person, PartakingType.DEELNEMER, event
        ));

        event.addPartaking(new Partaking(
                person, PartakingType.DEELNEMER, event
        ));
        assertEquals(1, event.getNumberOfParticipants());
    }

    @Test
    void addPartakingWithDuplicatePersonAndDifferentPartakingTypeDoesNothing(){
        Person person = personBuilder
                .voornaam("Hermione")
                .achternaam("Granger")
                .build();
        Event event = builder.build();
        event.addPartaking(new Partaking(
                person, PartakingType.DEELNEMER, event
        ));

        event.addPartaking(new Partaking(
                person, PartakingType.LEIDING, event
        ));
        assertEquals(1, event.getNumberOfParticipants());
    }
}

