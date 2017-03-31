package be.mira.jongeren.administration.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Partaking extends AbstractEntity{

    @ManyToOne
    @NotNull
    private Person person;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PartakingType partakingType;

    @ManyToOne
    private Event event;

    public Partaking() {
    }

    public Partaking(Person person, PartakingType partakingType, Event event) {
        this.person = person;
        this.partakingType = partakingType;
        this.event = event;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Partaking{" +
                "person=" + person +
                ", partakingType=" + partakingType +
                ", event=" + event.getId() +
                '}';
    }

    public static class PartakingBuilder {
        private Person person;
        private PartakingType partakingType = PartakingType.DEELNEMER;
    }
}
