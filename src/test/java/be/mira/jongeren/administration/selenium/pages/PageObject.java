package be.mira.jongeren.administration.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObject {

    private WebDriver webDriver;

    private String baseUrl;

    public PageObject(WebDriver webDriver, String baseUrl) {
        this.webDriver = webDriver;
        this.baseUrl = baseUrl;
    }

    public PageObject(WebDriver webDriver, String baseUrl, String page){
        this(webDriver, baseUrl);
        this.webDriver.navigate().to(baseUrl + page);
        this.initElements();
    }

    protected void initElements(){
        PageFactory.initElements(this.webDriver, this);
    }

    protected WebDriver driver(){
        return this.webDriver;
    }

    protected String baseUrl(){
        return this.baseUrl;

    }
}
