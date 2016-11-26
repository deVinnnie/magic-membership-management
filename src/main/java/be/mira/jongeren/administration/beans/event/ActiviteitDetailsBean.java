package be.mira.jongeren.administration.beans.event;

import be.mira.jongeren.administration.domain.Activiteit;
import be.mira.jongeren.administration.repository.ActiviteitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
@Component
@RequestScope
public class ActiviteitDetailsBean {

    @Autowired
    private ActiviteitRepository repository;

    private Activiteit activiteit;

    private Long id;

    @PostConstruct
    public void setUp(){
        this.activiteit = repository.findOne(10L);
    }

    public void onParametersLoaded() {
        this.activiteit = repository.findOne(id);
    }

    public Activiteit getActiviteit() {
        return activiteit;
    }

    public void setActiviteit(Activiteit activiteit) {
        this.activiteit = activiteit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
