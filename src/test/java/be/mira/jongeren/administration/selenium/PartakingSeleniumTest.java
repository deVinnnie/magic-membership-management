package be.mira.jongeren.administration.selenium;

import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.repository.EventRepository;
import be.mira.jongeren.administration.selenium.SeleniumTest;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import static com.ninja_squad.dbsetup.Operations.*;
import static org.junit.Assert.assertEquals;

public class PartakingSeleniumTest extends SeleniumTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EventRepository eventRepository;

    @Before
    public void setup(){
        Operation operation = sequenceOf(
            deleteAllFrom("partaking", "event", "person"),
            insertInto("person")
                .columns("id", "voornaam", "achternaam", "gender", "city_id")
                .values("15", "Harry", "Potter", "M", "140")
                .build(),
            insertInto("event")
                .columns("id", "version", "datum", "event_type")
                .values("10","1","2015-01-17", "SUPERNOVA")
                .build()
            );
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
    }


    @Test
    public void testAddPartakingGivesCorrectResult() {
        driver().navigate().to(getBaseUrl() + "/events/10/partakings/new");

        WebElement saveButton = driver().findElement(By.tagName("input"));
        saveButton.click();

        Event event = eventRepository.getOne(10L);
        assertEquals(1, event.getPartakings().size());
    }

    @Test
    public void testAddPartakingForSamePersonDoesNothing() {
        driver().navigate().to(getBaseUrl() + "/events/10/partakings/new");

        WebElement saveButton = driver().findElement(By.tagName("input"));
        saveButton.click();

        driver().navigate().to(getBaseUrl() + "/events/10/partakings/new");
        saveButton = driver().findElement(By.tagName("input"));
        saveButton.click();

        Event event = eventRepository.getOne(10L);
        assertEquals(1, event.getPartakings().size());
    }
}
