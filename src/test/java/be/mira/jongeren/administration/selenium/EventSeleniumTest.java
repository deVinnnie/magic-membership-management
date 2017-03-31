package be.mira.jongeren.administration.selenium;

import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.domain.EventType;
import be.mira.jongeren.administration.repository.EventRepository;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EventSeleniumTest extends SeleniumTest  {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void addEventFormPersistsEvent(){
        driver().navigate().to(getBaseUrl() + "/events/new");

        WebElement datumInputField = driver().findElement(By.tagName("form"))
                .findElement(By.name("datum"));

        datumInputField.sendKeys("2015-01-01");

        Select typeSelectBox = new Select(
                driver()
                    .findElement(By.tagName("form"))
                    .findElement(By.name("eventType"))
        );

        typeSelectBox.selectByValue("SUPERNOVA");

        WebElement formElement = driver().findElement(By.tagName("form"));
        formElement.submit();

        List<Event> allEvents = eventRepository.findAll();
        assertEquals(1, allEvents.size());

        Event event = allEvents.get(0);
        assertEquals(EventType.SUPERNOVA, event.getEventType());
        assertNotNull(event.getId());

        //assertEquals(new Date(2015,0, 01), event.getDatum());


    }
}
