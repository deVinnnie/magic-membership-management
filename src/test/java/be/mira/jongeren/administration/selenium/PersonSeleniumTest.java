package be.mira.jongeren.administration.selenium;

import be.mira.jongeren.administration.domain.Gender;
import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.repository.PersonRepository;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonSeleniumTest extends SeleniumTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void addPersonFormPersistsPerson() {
        driver().navigate().to(getBaseUrl() + "/persons/new");

        WebElement voornaamField = driver().findElement(By.name("voornaam"));
        voornaamField.sendKeys("Hermione");

        WebElement achternaamField = driver().findElement(By.name("achternaam"));
        achternaamField.sendKeys("Granger");

        Select genderSelectBox = new Select(
                driver().findElement(By.name("gender"))
        );

        genderSelectBox.selectByValue("F");

        WebElement birthDateField = driver().findElement(By.name("birthDate"));
        birthDateField.sendKeys("1979-09-19");

        WebElement formElement = driver().findElement(By.tagName("form"));
        formElement.submit();


        List<Person> allPersons = personRepository.findAll();
        assertEquals(1, allPersons.size());

        Person person = allPersons.get(0);
        assertEquals("Hermione", person.getVoornaam());
        assertEquals("Granger", person.getAchternaam());
        assertEquals(Gender.F, person.getGender());
    }
}
