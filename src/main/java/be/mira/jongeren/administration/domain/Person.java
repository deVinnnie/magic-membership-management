package be.mira.jongeren.administration.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
public class Person extends AbstractEntity{

    @NotBlank
    private String voornaam;

    @NotBlank
    private String achternaam;

    @ManyToOne
    private City city;

    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.X;

    @Temporal(TemporalType.DATE)
    @Past
    private Date birthDate;

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
}
