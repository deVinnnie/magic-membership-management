package be.mira.jongeren.administration.beans.statistics;

import be.mira.jongeren.administration.domain.Activiteit;
import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.service.EventService;
import be.mira.jongeren.administration.service.PersoonService;
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
    private PersoonService personService;

    @Autowired
    private EventService eventService;

    private List<Activiteit> events;

    public List<Person> getAllPersons(){
        return this.personService.findAll();
    }

    public List<Activiteit> getColumns(){
        return eventService.findAll();
    }
}
