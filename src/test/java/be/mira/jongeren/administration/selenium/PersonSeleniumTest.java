package be.mira.jongeren.administration.selenium;

import be.mira.jongeren.administration.domain.Gender;
import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.repository.PersonRepository;
import be.mira.jongeren.administration.selenium.pages.PersonCreationPage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonSeleniumTest extends SeleniumTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void addPersonFormPersistsPerson() {
        PersonCreationPage personCreationPage = new PersonCreationPage(driver(), getBaseUrl());

        personCreationPage.enterFirstName("Hermione")
                          .enterLastName("Granger")
                          .selectGender("F")
                          .enterBirthDate("1979-09-19")
                          .submit();

        List<Person> allPersons = personRepository.findAll();
        assertEquals(1, allPersons.size());

        Person person = allPersons.get(0);
        assertEquals("Hermione", person.getVoornaam());
        assertEquals("Granger", person.getAchternaam());
        assertEquals(Gender.F, person.getGender());
    }
}
