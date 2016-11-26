package be.mira.jongeren.administration.beans.event;

import be.mira.jongeren.administration.domain.Activiteit;
import be.mira.jongeren.administration.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventAddBean {

    private Activiteit event = new Activiteit();

    @Autowired
    private EventService eventService;

    public Activiteit getEvent() {
        return event;
    }

    public void setEvent(Activiteit event) {
        this.event = event;
    }

    public String save(){
        this.eventService.save(event);
        return "/activiteiten/all?faces-redirect=true";
    }
}
