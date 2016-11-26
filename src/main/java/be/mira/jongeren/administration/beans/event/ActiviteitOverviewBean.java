package be.mira.jongeren.administration.beans.event;

import be.mira.jongeren.administration.domain.Activiteit;
import be.mira.jongeren.administration.repository.ActiviteitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequestScope
public class ActiviteitOverviewBean {
    
    private List<Activiteit> allActiviteiten;
    
    @Autowired
    private ActiviteitRepository activiteitRepository;
    
    @PostConstruct
    public void setUp(){
        this.allActiviteiten = activiteitRepository.findAll();
    }

    public List<Activiteit> getAllActiviteiten() {
        return allActiviteiten;
    }

    public void setAllActiviteiten(List<Activiteit> allActiviteiten) {
        this.allActiviteiten = allActiviteiten;
    }
}
