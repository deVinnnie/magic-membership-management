package be.mira.jongeren.administration.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class Deelname extends AbstractEntity{

    @ManyToOne
    private Person person;

    @Enumerated(EnumType.STRING)
    private DeelnameType deelnameType;

    @ManyToOne
    private Activiteit activiteit;

    public Deelname() {
    }

    public Deelname(Person person, DeelnameType deelnameType, Activiteit activiteit) {
        this.person = person;
        this.deelnameType = deelnameType;
        this.activiteit = activiteit;
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

    public Activiteit getActiviteit() {
        return activiteit;
    }

    public void setActiviteit(Activiteit activiteit) {
        this.activiteit = activiteit;
    }
}
