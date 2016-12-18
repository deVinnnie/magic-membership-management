package be.mira.jongeren.administration.beans.partaking;

import be.mira.jongeren.administration.beans.event.EventDetailsBean;
import be.mira.jongeren.administration.domain.City;
import be.mira.jongeren.administration.domain.Partaking;
import be.mira.jongeren.administration.domain.PartakingType;
import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.service.PartakingService;
import be.mira.jongeren.administration.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddPartakingBean {

    @Autowired
    private PartakingService partakingService;

    @Autowired
    private PersonService personService;

    @Autowired
    private EventDetailsBean eventDetailsBean;

    private Person person = new Person();

    private City city = new City();

    private PartakingType partakingType = PartakingType.DEELNEMER;

    public String toForm(){
        return "/partaking/add?faces-redirect=true";
    }

    public String save(){
        Person savedPerson = personService.save(person);

        Partaking partaking = new Partaking();
        partaking.setEvent(eventDetailsBean.getEvent());
        partaking.setPerson(savedPerson);
        partaking.setPartakingType(partakingType);

        this.partakingService.save(partaking);

        return "/activiteiten/detail?faces-redirect=true";
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PartakingType getPartakingType() {
        return partakingType;
    }

    public void setPartakingType(PartakingType partakingType) {
        this.partakingType = partakingType;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
