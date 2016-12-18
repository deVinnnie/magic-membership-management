package be.mira.jongeren.administration.beans.event;

import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EventAddBean {

    private Event event = new Event();

    @Inject
    private EventService eventService;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String save(){
        this.eventService.save(event);
        return "/activiteiten/all?faces-redirect=true";
    }
}
