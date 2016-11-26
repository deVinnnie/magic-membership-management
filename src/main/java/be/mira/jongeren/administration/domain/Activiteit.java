package be.mira.jongeren.administration.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Activiteit extends AbstractEntity{

    @Temporal(TemporalType.DATE)
    private Date datum;

    @OneToMany(mappedBy = "activiteit", fetch = FetchType.EAGER)
    private List<Deelname> deelnames;

    @Enumerated(EnumType.STRING)
    private ActiviteitType activiteitType;

    public Activiteit() {
    }

    public Activiteit(Date datum, ActiviteitType activiteitType) {
        this.datum = datum;
        this.activiteitType = activiteitType;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public List<Deelname> getDeelnames() {
        return deelnames;
    }

    public void setDeelnames(List<Deelname> deelnames) {
        this.deelnames = deelnames;
    }

    public ActiviteitType getActiviteitType() {
        return activiteitType;
    }

    public void setActiviteitType(ActiviteitType activiteitType) {
        this.activiteitType = activiteitType;
    }


    public long getNumberOfPartakingsOfType(DeelnameType type){
        return this.deelnames.stream()
                .filter(d -> d.getDeelnameType().equals(type))
                .count();
    }

    public long getNumberOfParticipants(){
        return this.getNumberOfPartakingsOfType(DeelnameType.DEELNEMER);
    }

    public long getNumberOfAssistants(){
        return this.getNumberOfPartakingsOfType(DeelnameType.LEIDING);
    }

    public long getNumberOfExternalParticipants(){
        return this.getNumberOfPartakingsOfType(DeelnameType.EXTERN);
    }
}
