package be.mira.jongeren.administration.domain;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Event{

    /**
     * YYYYMMDDN
     * 201510030
     *
     * YYYY: Year
     * MM: Month (01 = january)
     * DD: Day of month (Starting at 01)
     * N: A reference number. Starts at 0. (Currently not used, so always '0')
     * Events on the same date are uniquely identified by the reference number.
     *
     * 201510030: The first event on October 03 2015.
     * 201510031: A second event on October 03 2015.
     *
     */
    @Id
    @GenericGenerator(
            name = "event-identity",
            strategy = "be.mira.jongeren.administration.domain.EventIdentityGenerator"
    )
    @GeneratedValue(
            generator = "event-identity",
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Version
    private Long version;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate datum;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Partaking> partakings = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @NotNull
    private EventType eventType;

    public Event() {
    }

    public Event(LocalDate datum, EventType eventType) {
        this.datum = datum;
        this.eventType = eventType;
    }

    private Event(EventBuilder builder) {
        this(builder.datum, builder.eventType);
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Set<Partaking> getPartakings() {
        return partakings;
    }

    public void setPartakings(Set<Partaking> partakings) {
        this.partakings = partakings;
    }

    public void addPartaking(Partaking partaking){
        this.partakings.add(partaking);
    }

    public void addPartakings(List<Partaking> partakings){
        for(Partaking partaking : partakings) {
            this.partakings.add(partaking);
        }
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }


    public long getNumberOfPartakingsOfType(PartakingType type){
        return this.partakings.stream()
                .filter(d -> d.getPartakingType().equals(type))
                .count();
    }

    public long getNumberOfParticipants(){
        return this.getNumberOfPartakingsOfType(PartakingType.DEELNEMER);
    }

    public long getNumberOfAssistants(){
        return this.getNumberOfPartakingsOfType(PartakingType.LEIDING);
    }

    public long getNumberOfExternalParticipants(){
        return this.getNumberOfPartakingsOfType(PartakingType.EXTERN);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", version=" + version +
                ", datum=" + datum +
                ", partakings=" + partakings +
                ", eventType=" + eventType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (datum != null ? !datum.equals(event.datum) : event.datum != null) return false;
        return eventType == event.eventType;
    }

    @Override
    public int hashCode() {
        int result = datum != null ? datum.hashCode() : 0;
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        return result;
    }

    public static class EventBuilder {
        private LocalDate datum = LocalDate.now();
        private EventType eventType = EventType.MAIN_SEQUENCE;

        public Event build(){
            return new Event(this);
        }

        public EventBuilder datum(LocalDate datum){
            this.datum = datum;
            return this;
        }

        public EventBuilder type(EventType type){
            this.eventType = type;
            return this;
        }
    }
}
