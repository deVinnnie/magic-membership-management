package be.mira.jongeren.administration.beans.event;

import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.repository.EventRepository;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EventDetailsBean {

    @Inject
    private EventRepository repository;

    private Event event;

    private Long id;

    @PostConstruct
    public void setUp(){
        this.event = repository.findOne(10L);
    }

    public void onParametersLoaded() {
        this.event = repository.findOne(id);
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
