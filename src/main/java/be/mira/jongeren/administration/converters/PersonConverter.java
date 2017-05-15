package be.mira.jongeren.administration.converters;

import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter implements Converter<String, Person> {

    private PersonRepository personRepository;

    @Autowired
    public PersonConverter(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person convert(String id) {
        try {
            Long personId = Long.valueOf(id);
            return personRepository.findOne(personId);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
