package be.mira.jongeren.administration.util;

import be.mira.jongeren.administration.domain.Gender;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class GenderOptions {
    public Gender[] getOptions(){
        return Gender.values();
    }
}
