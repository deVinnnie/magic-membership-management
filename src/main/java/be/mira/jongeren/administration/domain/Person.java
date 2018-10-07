package be.mira.jongeren.administration.domain;

import be.mira.jongeren.administration.util.date.Past;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.UUID;

@Entity
public class Person{

    @Id
    @GeneratedValue
    private UUID id;

    @Version
    private Long version;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }


    @NotBlank
    private String voornaam;

    @NotBlank
    private String achternaam;

    @ManyToOne
    private City city;

    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.X;

    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;
//
//    @OneToMany
//    private List<Membership> memberships;

    public Person(){}

    public Person(String voornaam, String achternaam){
        this.voornaam = voornaam;
        this.achternaam = achternaam;
    }

    private Person(PersonBuilder builder) {
        this(builder.voornaam, builder.achternaam);
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "voornaam='" + voornaam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", city=" + city +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (voornaam != null ? !voornaam.equals(person.voornaam) : person.voornaam != null) return false;
        if (achternaam != null ? !achternaam.equals(person.achternaam) : person.achternaam != null) return false;
        if (city != null ? !city.equals(person.city) : person.city != null) return false;
        if (gender != person.gender) return false;
        return birthDate != null ? birthDate.equals(person.birthDate) : person.birthDate == null;
    }

    @Override
    public int hashCode() {
        int result = voornaam != null ? voornaam.hashCode() : 0;
        result = 31 * result + (achternaam != null ? achternaam.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }

    public static class PersonBuilder{
        private String voornaam = "Harry";
        private String achternaam = "Potter";

        public Person build(){
            return new Person(this);
        }

        public PersonBuilder voornaam(String voornaam){
            this.voornaam = voornaam;
            return this;
        }

        public PersonBuilder achternaam(String achternaam){
            this.achternaam = achternaam;
            return this;
        }
    }

    public static class AlphabeticComparator implements Comparator<Person> {

        @Override
        public int compare(Person person1, Person person2) {
            String name1 = person1.getVoornaam() + person1.getAchternaam();
            String name2 = person2.getVoornaam() + person2.getAchternaam();
            return name1.compareTo(name2);
        }
    }
}
