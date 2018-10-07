package be.mira.jongeren.administration.rest;

import be.mira.jongeren.administration.controller.MockMvcTest;
import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.domain.PartakingType;
import be.mira.jongeren.administration.repository.EventRepository;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.UUID;

import static com.ninja_squad.dbsetup.Operations.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PartakingsResourceTest extends MockMvcTest {

    @Autowired
    private EventRepository eventRepository;

    @Before
    public void setup(){
        Operation operation = sequenceOf(
                deleteAllFrom("partaking", "event", "person"),
                insertInto("person")
                        .columns("id", "voornaam", "achternaam", "gender")
                        .values("A0EEBC999C0B4EF8BB6D6BB9BD380A11", "Harry", "Potter", "M")
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
    public void doPostAddsPartaking() throws Exception {
        PartakingDto partakingDto = new PartakingDto(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), PartakingType.DEELNEMER);

        mockMvc()
            .perform(
                post("/api/events/10/partakings")
                        .content(asJsonString(partakingDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(
                status().is2xxSuccessful()
            );

        Event event = eventRepository.findOne(10L);
        assertEquals(1, event.getNumberOfParticipants());
    }

}