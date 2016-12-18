package be.mira.jongeren.administration.beans.partaking;

import be.mira.jongeren.administration.beans.event.EventDetailsBean;
import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.domain.Partaking;
import be.mira.jongeren.administration.domain.PartakingType;
import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.service.PartakingService;
import be.mira.jongeren.administration.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddExistingPersonBean {

    private Long personId;

    private Event event;

    private PartakingType partakingType = PartakingType.DEELNEMER;

    @Autowired
    private PersonService personService;

    @Autowired
    private EventDetailsBean eventDetailsBean;

    @Autowired
    private PartakingService partakingService;

    public String toForm(){
        return "/partaking/add-existing?faces-redirect=true";
    }

    public List<Person> getAllPersons(){
        return personService.findAll();
    }

    public String save(){
        Person existingPerson = personService.findOne(personId);

        Partaking partaking = new Partaking();
        partaking.setEvent(eventDetailsBean.getEvent());
        partaking.setPerson(existingPerson);
        partaking.setPartakingType(partakingType);

        this.partakingService.save(partaking);

        return "/activiteiten/detail?faces-redirect=true";

    }


    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public PartakingType getPartakingType() {
        return partakingType;
    }

    public void setPartakingType(PartakingType partakingType) {
        this.partakingType = partakingType;
    }
}
