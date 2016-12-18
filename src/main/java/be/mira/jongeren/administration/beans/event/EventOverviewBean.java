package be.mira.jongeren.administration.beans.event;

import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequestScope
public class EventOverviewBean {
    
    private List<Event> allActiviteiten;
    
    @Autowired
    private EventRepository eventRepository;
    
    @PostConstruct
    public void setUp(){
        this.allActiviteiten = eventRepository.findAll();
    }

    public List<Event> getAllActiviteiten() {
        return allActiviteiten;
    }

    public void setAllActiviteiten(List<Event> allActiviteiten) {
        this.allActiviteiten = allActiviteiten;
    }
}
