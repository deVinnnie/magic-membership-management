package be.mira.jongeren.administration.beans.person;

import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@Component
@ManagedBean
@ViewScoped
public class PersonAddBean {

    @Autowired
    private PersonService personService;

    private Person person = new Person();

    public String save(){
        personService.save(person);
        return "/persons/all?faces-redirect=true";
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
