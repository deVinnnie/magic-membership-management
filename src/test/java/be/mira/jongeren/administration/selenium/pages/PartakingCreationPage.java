package be.mira.jongeren.administration.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PartakingCreationPage extends PageObject {

    @FindBy(tagName= "input")
    private WebElement saveButton;

    @FindBy(name = "person")
    private WebElement personSelectElement;

    public PartakingCreationPage(WebDriver webDriver, String baseUrl) {
        super(webDriver, baseUrl, "/events/10/partakings/new");
    }

    public PartakingCreationPage selectPerson(String name){
        Select typeSelectBox = new Select(this.personSelectElement);
        typeSelectBox.selectByVisibleText(name);
        return this;
    }

    public void submit(){
        saveButton.click();
    }




}
