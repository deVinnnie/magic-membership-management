package be.mira.jongeren.administration.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest extends BeanValidatorTest{

    private Person.PersonBuilder builder;

    @BeforeEach
    void setUp(){
        this.builder = new Person.PersonBuilder();
    }

    @Test
    void birthDateInFutureGivesValidationError(){
        Person person = builder.build();

        LocalDate dateThenYearsInTheFuture = LocalDate.now().plusYears(10);
        person.setBirthDate(dateThenYearsInTheFuture);

        Set<ConstraintViolation<Person>> violations = validator().validateProperty(person, "birthDate");
        assertEquals(1, violations.size());
    }
}
