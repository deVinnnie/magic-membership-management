package be.mira.jongeren.administration.service;

import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.repository.PersoonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersoonService {

    @Autowired
    private PersoonRepository repository;

    public Person save(Person person){
        return this.repository.save(person);
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person findOne(Long id){
        return repository.findOne(id);
    }
}
