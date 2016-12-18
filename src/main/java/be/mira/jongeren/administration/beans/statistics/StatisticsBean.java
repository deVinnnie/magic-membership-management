package be.mira.jongeren.administration.beans.statistics;

import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.service.EventService;
import be.mira.jongeren.administration.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.faces.bean.ManagedBean;
import java.util.List;

@Component
@ManagedBean
@RequestScope
public class StatisticsBean {

    @Autowired
    private PersonService personService;

    @Autowired
    private EventService eventService;

    private List<Event> events;

    public List<Person> getAllPersons(){
        return this.personService.findAll();
    }

    public List<Event> getColumns(){
        return eventService.findAll();
    }
}
