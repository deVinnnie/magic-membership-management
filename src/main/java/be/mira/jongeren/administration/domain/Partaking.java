package be.mira.jongeren.administration.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"person_id", "event_id"})})
public class Partaking extends AbstractEntity<Long>{

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
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Partaking partaking = (Partaking) o;

         return Objects.equals(person, partaking.person) &&
                Objects.equals(event, partaking.event);
    }

    @Override
    public int hashCode() {
        int result = person != null ? person.hashCode() : 0;
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
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
