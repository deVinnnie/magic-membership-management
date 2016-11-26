package be.mira.jongeren.administration.beans.partaking;

import be.mira.jongeren.administration.beans.event.ActiviteitDetailsBean;
import be.mira.jongeren.administration.domain.City;
import be.mira.jongeren.administration.domain.Deelname;
import be.mira.jongeren.administration.domain.DeelnameType;
import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.service.DeelnameService;
import be.mira.jongeren.administration.service.PersoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddDeelnameBean {

    @Autowired
    private DeelnameService deelnameService;

    @Autowired
    private PersoonService persoonService;

    @Autowired
    private ActiviteitDetailsBean activiteitDetailsBean;

    private Person person = new Person();

    private City city = new City();

    private DeelnameType deelnameType = DeelnameType.DEELNEMER;

    public String toForm(){
        return "/partaking/add?faces-redirect=true";
    }

    public String save(){
        Person savedPerson = persoonService.save(person);

        Deelname deelname = new Deelname();
        deelname.setActiviteit(activiteitDetailsBean.getActiviteit());
        deelname.setPerson(savedPerson);
        deelname.setDeelnameType(deelnameType);

        this.deelnameService.save(deelname);

        return "/activiteiten/detail?faces-redirect=true";
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public DeelnameType getDeelnameType() {
        return deelnameType;
    }

    public void setDeelnameType(DeelnameType deelnameType) {
        this.deelnameType = deelnameType;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
