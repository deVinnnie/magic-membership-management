package be.mira.jongeren.administration.domain;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class EventTest extends BeanValidatorTest{

    private Event.EventBuilder builder;

    @Before
    public void setUp(){
        this.builder = new Event.EventBuilder();
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
}
