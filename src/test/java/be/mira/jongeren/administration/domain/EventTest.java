package be.mira.jongeren.administration.domain;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class EventTest extends BeanValidatorTest{

    private Event.EventBuilder builder;
    private Person.PersonBuilder personBuilder;

    @Before
    public void setUp(){
        this.builder = new Event.EventBuilder();
        this.personBuilder = new Person.PersonBuilder();
    }

    @Test
    public void eventTypeNullGivesValidationError(){
        Event event = builder.type(null).build();
        Set<ConstraintViolation<Event>> violations = validator().validateProperty(event, "eventType");
        assertEquals(1, violations.size());
    }

    @Test
    public void getNumberOfParticipantsReturnsZeroForEmptyPartakingsList(){
        Event event = builder.build();
        assertEquals(0, event.getNumberOfParticipants());
    }

    @Test
    public void getNumberOfParticipantsReturnsOneForPartakingsListWithSingleParticipant(){
        Event event = EventMother.createEventWithSingleParticipant();
        assertEquals(1, event.getNumberOfParticipants());
    }

    @Test
    public void getNumberOfPartakingsOfTypeReturnsCorrectResult(){
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
    public void addPartakingWithDuplicatePersonDoesNothing(){
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
    public void addPartakingWithDuplicatePersonAndDifferentPartakingTypeDoesNothing(){
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

