package be.mira.jongeren.administration.util;

import be.mira.jongeren.administration.domain.PartakingType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class PartakingTypeOptions {

    public PartakingType[] getOptions(){
        return PartakingType.values();
    }
}
