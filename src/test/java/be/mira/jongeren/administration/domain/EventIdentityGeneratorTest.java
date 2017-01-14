package be.mira.jongeren.administration.domain;

import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertEquals;

public class EventIdentityGeneratorTest {

    private EventIdentityGenerator generator = new EventIdentityGenerator();

    @Test
    public void generateGivesCorrectResult(){
        Event event = EventMother.createEvent();

        Long id = (Long) generator.generate(null, event);
        assertEquals(201503140L, (long) id);
    }

    @Test
    public void generateGivesCorrectResultWhenDayOfMonthIsSmallerThan10(){
        Event event = EventMother.createEarlyEvent();

        Long id = (Long) generator.generate(null, event);
        assertEquals(201503040L, (long) id);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void generateGivesExceptionWhenNotAnEvent(){
        generator.generate(null, new Object());
    }

    @Test
    public void generateGivesUniqueIdForEventsOnSameDate(){
        Event event1 = EventMother.createEvent();
        Event event2 = EventMother.createEvent();
        generator.generate(null, event1);
    }
}
