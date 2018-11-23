package be.mira.jongeren.administration.converter;

import be.mira.jongeren.administration.converters.PersonConverter;
import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


@ExtendWith(MockitoExtension.class)
class PersonConverterTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonConverter converter;

    Person person;

    @BeforeEach
    void setup(){
        person = new Person("Harry", "Potter");
        person.setId(UUID.fromString("a98adb54-c6d1-4881-9154-023b0e7e545e"));
    }

    @Test
    void convertWithInvalidNumberReturnsNull(){
        Person result = converter.convert("not-a-number");
        assertNull(result);
    }

    @Test
    void convertWithExistingIdReturnsPerson(){
        Mockito.when(personRepository.getOne(UUID.fromString("a98adb54-c6d1-4881-9154-023b0e7e545e"))).thenReturn(person);

        Person result = converter.convert("a98adb54-c6d1-4881-9154-023b0e7e545e");
        assertNotNull(result);
    }

    @Test
    void convertWithNonExistingIdReturnsNull(){
        Mockito.when(personRepository.getOne(UUID.fromString("a98adb54-c6d1-4881-1555-023b0e7e545e"))).thenReturn(null);

        Person result = converter.convert("a98adb54-c6d1-4881-1555-023b0e7e545e");
        assertNull(result);
    }
}
