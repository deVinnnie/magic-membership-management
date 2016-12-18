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
    public void generateGivesWhenNotAnEvent(){
        generator.generate(null, new Object());
    }
}
