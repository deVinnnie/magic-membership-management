package be.mira.jongeren.administration.beans.partaking;

import be.mira.jongeren.administration.beans.event.ActiviteitDetailsBean;
import be.mira.jongeren.administration.domain.Activiteit;
import be.mira.jongeren.administration.domain.Deelname;
import be.mira.jongeren.administration.domain.DeelnameType;
import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.service.DeelnameService;
import be.mira.jongeren.administration.service.PersoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddExistingPersonBean {

    private Long personId;

    private Activiteit event;

    private DeelnameType deelnameType = DeelnameType.DEELNEMER;

    @Autowired
    private PersoonService persoonService;

    @Autowired
    private ActiviteitDetailsBean activiteitDetailsBean;

    @Autowired
    private DeelnameService deelnameService;

    public String toForm(){
        return "/partaking/add-existing?faces-redirect=true";
    }

    public List<Person> getAllPersons(){
        return persoonService.findAll();
    }

    public String save(){
        Person existingPerson = persoonService.findOne(personId);

        Deelname deelname = new Deelname();
        deelname.setActiviteit(activiteitDetailsBean.getActiviteit());
        deelname.setPerson(existingPerson);
        deelname.setDeelnameType(deelnameType);

        this.deelnameService.save(deelname);

        return "/activiteiten/detail?faces-redirect=true";

    }


    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Activiteit getEvent() {
        return event;
    }

    public void setEvent(Activiteit event) {
        this.event = event;
    }

    public DeelnameType getDeelnameType() {
        return deelnameType;
    }

    public void setDeelnameType(DeelnameType deelnameType) {
        this.deelnameType = deelnameType;
    }
}
