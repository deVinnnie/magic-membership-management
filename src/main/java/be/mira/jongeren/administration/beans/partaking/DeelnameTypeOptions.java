package be.mira.jongeren.administration.beans.partaking;

import be.mira.jongeren.administration.domain.DeelnameType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class DeelnameTypeOptions {

    public DeelnameType[] getOptions(){
        return DeelnameType.values();
    }
}
