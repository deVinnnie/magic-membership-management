package be.mira.jongeren.administration.domain;

import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EventIdentityGeneratorTest {

    private EventIdentityGenerator generator = new EventIdentityGenerator();

    @Test
    public void generateGivesCorrectResult(){
        Long id = (Long) generator.generate(LocalDate.of(2015, Month.MARCH, 14));
        assertEquals(201503140L, (long) id);
    }

    @Test
    public void generateGivesCorrectResultWhenDayOfMonthIsSmallerThan10(){
        Long id = (Long) generator.generate(LocalDate.of(2015, Month.MARCH, 4));
        assertEquals(201503040L, (long) id);
    }

    /**
     * Make sure the EventIdentityGenerator doesn't use 'year-week'-style in formatting.
     */
    @Test
    public void generateGivesCorrectResultDateIsAtBeginningOfYear(){
        Long id = (Long) generator.generate(LocalDate.of(2016, Month.JANUARY, 1));
        assertEquals(201601010L, (long) id);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void generateGivesExceptionWhenNotAnEvent(){
        generator.generate(null, new Object());
    }

    @Test
    @Ignore
    public void generateGivesUniqueIdForEventsOnSameDate(){
        Long id1 = (Long) generator.generate(LocalDate.of(2015, Month.MARCH, 4));
        Long id2 = (Long) generator.generate(LocalDate.of(2015, Month.MARCH, 4));

        assertNotEquals(id1, id2);
    }
}
