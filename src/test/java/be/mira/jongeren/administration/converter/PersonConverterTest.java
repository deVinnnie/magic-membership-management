package be.mira.jongeren.administration.converter;

import be.mira.jongeren.administration.converters.PersonConverter;
import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.repository.PersonRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(MockitoJUnitRunner.class)
public class PersonConverterTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonConverter converter;

    @Before
    public void setup(){
        Person person = new Person("Harry", "Potter");
        person.setId(10L);
        Mockito.when(personRepository.findOne(10L)).thenReturn(person);
        Mockito.when(personRepository.findOne(155L)).thenReturn(null);
    }

    @Test
    public void convertWithInvalidNumberReturnsNull(){
        Person result = converter.convert("not-a-number");
        assertNull(result);
    }

    @Test
    public void convertWithExistingIdReturnsPerson(){
        Person result = converter.convert("10");
        assertNotNull(result);
    }

    @Test
    public void convertWithNonExistingIdReturnsNull(){
        Person result = converter.convert("155");
        assertNull(result);
    }
}
