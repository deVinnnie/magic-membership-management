package be.mira.jongeren.administration.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class EventCreationPage extends PageObject{

    @FindBy(name = "datum")
    private WebElement datumInputField;

    @FindBy(name = "eventType")
    private WebElement typeSelectElement;

    @FindBy(tagName = "form")
    private WebElement formElement;

    public EventCreationPage(WebDriver webDriver, String baseUrl) {
        super(webDriver, baseUrl, "/events/new");
    }

    public EventCreationPage enterDate(String date){
        datumInputField.sendKeys(date);
        return this;
    }

    public EventCreationPage selectEventType(String value){
        Select typeSelectBox = new Select(this.typeSelectElement);
        typeSelectBox.selectByValue(value);
        return this;
    }

    public void submit(){
        this.formElement.submit();
    }
}
