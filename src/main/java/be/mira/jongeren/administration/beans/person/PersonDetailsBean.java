package be.mira.jongeren.administration.beans.person;

import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.service.PersoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.IOException;

@Component
@ManagedBean
@RequestScope
public class PersonDetailsBean {

    private Person person;

    private Long id;

    @Autowired
    private PersoonService personService;

    @PostConstruct
    public void setUp(){
        this.person = personService.findOne(10L);
    }

    public void onParametersLoaded() throws IOException {
        person = personService.findOne(id);

        if(person == null){
            person = new Person();
            //redirectionBean.throw404();
            return;
        }
    }

    public Person getPerson(){
        return this.person;
    }
    
    public void setPerson(Person person){
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
