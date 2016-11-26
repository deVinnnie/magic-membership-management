package be.mira.jongeren.administration.beans.person;

import be.mira.jongeren.administration.domain.Gender;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.faces.bean.ManagedBean;

@Component
@ManagedBean
@ApplicationScope
public class GenderOptions {
    public Gender[] getOptions(){
        return Gender.values();
    }
}
