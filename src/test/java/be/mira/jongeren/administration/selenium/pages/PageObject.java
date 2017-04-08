package be.mira.jongeren.administration.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObject {

    private WebDriver webDriver;

    public PageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public PageObject(WebDriver webDriver, String baseUrl, String page){
        this(webDriver);
        this.webDriver.navigate().to(baseUrl + page);
        this.initElements();
    }

    protected void initElements(){
        PageFactory.initElements(this.webDriver, this);
    }
}
