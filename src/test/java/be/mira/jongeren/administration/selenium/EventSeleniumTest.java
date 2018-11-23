package be.mira.jongeren.administration.selenium;

import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.domain.EventType;
import be.mira.jongeren.administration.repository.EventRepository;
import be.mira.jongeren.administration.selenium.pages.EventCreationPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EventSeleniumTest extends SeleniumTest  {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void addEventFormPersistsEvent(){
        EventCreationPage eventCreationPage = new EventCreationPage(driver(), getBaseUrl());
        eventCreationPage.enterDate("2015-01-01")
                         .selectEventType("SUPERNOVA")
                         .submit();

        List<Event> allEvents = eventRepository.findAll();
        assertEquals(1, allEvents.size());

        Event event = allEvents.get(0);
        assertEquals(EventType.SUPERNOVA, event.getEventType());
        assertNotNull(event.getId());

        //assertEquals(new Date(2015,0, 01), event.getDatum());
    }
}
