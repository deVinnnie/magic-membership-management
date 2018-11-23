package be.mira.jongeren.administration.selenium;

import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.repository.EventRepository;
import be.mira.jongeren.administration.selenium.pages.PartakingCreationPage;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ninja_squad.dbsetup.Operations.*;
import static org.junit.Assert.assertEquals;

@Disabled
class PartakingSeleniumTest extends SeleniumTest {

    @Autowired
    private EventRepository eventRepository;

    @BeforeEach
    @Override
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
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource()), operation);
        dbSetup.launch();
    }


    @Test
    void testAddPartakingGivesCorrectResult() {
        PartakingCreationPage partakingCreationPage = new PartakingCreationPage(driver(), getBaseUrl());

        partakingCreationPage.submit();

        partakingCreationPage
                .searchForPerson("Harry Potter");
        //partakingCreationPage.submit();
        //partakingCreationPage.submit();

        Event event = eventRepository.getOne(10L);
        assertEquals(1, event.getPartakings().size());
    }
}
