package be.mira.jongeren.administration.beans.event;

import be.mira.jongeren.administration.domain.ActiviteitType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class ActiviteitTypeOptions {

    public ActiviteitType[] getOptions(){
        return  ActiviteitType.values();
    }
}
