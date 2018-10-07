package be.mira.jongeren.administration.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PersonCreationPage extends PageObject{

    @FindBy(name = "voornaam")
    private WebElement voornaamField;

    @FindBy(name = "achternaam")
    private WebElement achternaamField;

    @FindBy(name = "gender")
    private WebElement genderSelectElement;

    @FindBy(name = "birthDate")
    private WebElement birthDateField;

    @FindBy(tagName = "form")
    private WebElement formElement;

    public PersonCreationPage(WebDriver webDriver, String baseUrl) {
        super(webDriver, baseUrl, "/persons/new");
    }

    public PersonCreationPage enterFirstName(String firstName) {
        voornaamField.sendKeys(firstName);
        return this;
    }

    public PersonCreationPage enterLastName(String lastName){
        achternaamField.sendKeys(lastName);
        return this;
    }

    public PersonCreationPage enterBirthDate(String birthDate){
        birthDateField.sendKeys(birthDate);
        return this;
    }

    public PersonCreationPage selectGender(String gender){
        Select genderSelectBox = new Select(genderSelectElement);
        genderSelectBox.selectByValue(gender);
        return this;
    }

    public void submit(){
        formElement.submit();
    }
}
