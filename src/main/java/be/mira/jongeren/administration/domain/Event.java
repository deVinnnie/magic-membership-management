package be.mira.jongeren.administration.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event{

    /**
     * YYYYMMDDN
     * 201510030
     *
     * YYYY: Year
     * MM: Month (01 = january)
     * DD: Day of month (Starting at 01)
     * N: A reference number. Starts at 0.
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

    @Temporal(TemporalType.DATE)
    private Date datum;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private List<Partaking> partakings = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @NotNull
    private EventType eventType;

    public Event() {
    }

    public Event(Date datum, EventType eventType) {
        this.datum = datum;
        this.eventType = eventType;
    }

    private Event(EventBuilder builder) {
        this(builder.datum, builder.eventType);
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public List<Partaking> getPartakings() {
        return partakings;
    }

    public void setPartakings(List<Partaking> partakings) {
        this.partakings = partakings;
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


    public static class EventBuilder {
        private Date datum = new Date();
        private EventType eventType = EventType.MAIN_SEQUENCE;

        public Event build(){
            return new Event(this);
        }

        public EventBuilder datum(Date datum){
            this.datum = datum;
            return this;
        }

        public EventBuilder type(EventType type){
            this.eventType = type;
            return this;
        }
    }
}
