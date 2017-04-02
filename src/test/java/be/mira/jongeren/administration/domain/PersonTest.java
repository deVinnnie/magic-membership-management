package be.mira.jongeren.administration.domain;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PersonTest extends BeanValidatorTest{

    private Person.PersonBuilder builder;

    @Before
    public void setUp(){
        this.builder = new Person.PersonBuilder();
    }

    @Test
    public void birthDateInFutureGivesValidationError(){
        Person person = builder.build();

        LocalDate dateThenYearsInTheFuture = LocalDate.now().plusYears(10);
        person.setBirthDate(dateThenYearsInTheFuture);

        Set<ConstraintViolation<Person>> violations = validator().validateProperty(person, "birthDate");
        assertEquals(1, violations.size());
    }
}
