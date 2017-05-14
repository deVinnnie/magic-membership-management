package be.mira.jongeren.administration.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Membership extends AbstractEntity<Long>{

    @OneToOne
    @NotNull
    private Person person;

    @Embedded
    private IssueNumber issueNumber;

    @NotNull
    private LocalDate issueDate = LocalDate.now();

    public Long getYear(){
        return 2015L;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "person=" + person +
                ", issueNumber=" + issueNumber +
                ", issueDate=" + issueDate +
                '}';
    }
}
