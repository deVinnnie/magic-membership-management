package be.mira.jongeren.administration.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartakingCreationPage extends PageObject {

    @FindBy(tagName= "input")
    private WebElement saveButton;

    @FindBy(name = "search")
    private WebElement searchBox;

    @FindBy(className = "search-form")
    private WebElement searchForm;

    public PartakingCreationPage(WebDriver webDriver, String baseUrl) {
        super(webDriver, baseUrl, "/events/10/partakings/new");
    }

    public PartakingCreationPage searchForPerson(String searchTerm){
        searchBox.sendKeys(searchTerm);
        searchForm.submit();
        return new PartakingCreationPage(driver(), baseUrl());
    }

    public void submit(){
        saveButton.click();
    }




}
