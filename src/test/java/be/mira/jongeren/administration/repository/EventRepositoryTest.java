package be.mira.jongeren.administration.repository;

import be.mira.jongeren.administration.controller.MockMvcTest;
import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.domain.EventType;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

public class EventRepositoryTest extends MockMvcTest{

    @Autowired
    private EventRepository repository;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setup(){
        Operation operation = sequenceOf(
                deleteAllFrom("partaking", "event", "person"),
                insertInto("event")
                    .columns("id", "version", "datum", "event_type")
                    .values("10","1","2015-01-17", "SUPERNOVA")
                    .values("20","1","2015-02-17", "MAIN_SEQUENCE")
                    .build()
        );
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
    }

    @Test
    public void findByYearWithoutTypeGivesEventsFromAllTypes() {
        List<Event> results = repository.findByYear(2015, null);
        assertThat(results, hasSize(equalTo(2)));
    }

    @Test
    public void findByYearWithTypeGivesOnlyEventsFromType() {
        List<Event> results = repository.findByYear(2015, EventType.MAIN_SEQUENCE);
        assertThat(results, hasSize(equalTo(1)));
    }

}
