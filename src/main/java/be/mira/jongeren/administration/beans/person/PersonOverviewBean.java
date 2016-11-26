package be.mira.jongeren.administration.beans.person;

import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.repository.PersoonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequestScope
public class PersonOverviewBean {

    @Autowired
    private PersoonRepository persoonRepository;

    private List<Person> allPersons;

    @PostConstruct
    public void setUp(){
        this.allPersons = persoonRepository.findAll();
    }

    public List<Person> getAllPersons() {
        return allPersons;
    }

    public void setAllPersons(List<Person> allPersons) {
        this.allPersons = allPersons;
    }
}
