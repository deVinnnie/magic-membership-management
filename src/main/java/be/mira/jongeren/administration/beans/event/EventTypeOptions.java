package be.mira.jongeren.administration.beans.event;

import be.mira.jongeren.administration.domain.EventType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class EventTypeOptions {

    public EventType[] getOptions(){
        return  EventType.values();
    }
}
