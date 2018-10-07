package be.mira.jongeren.administration.controller;

import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.domain.Partaking;
import be.mira.jongeren.administration.domain.PartakingType;
import be.mira.jongeren.administration.repository.EventRepository;
import be.mira.jongeren.administration.repository.PartakingRepository;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.UUID;

import static com.ninja_squad.dbsetup.Operations.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PartakingControllerTest extends MockMvcTest{

    @Autowired
    private PartakingRepository partakingRepository;

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
    public void createPartaking() throws Exception {
        mockMvc()
            .perform(
                post("/events/10/partakings/")
                    .param("person", "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                    .param("partakingType", "DEELNEMER")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            )
            .andExpect(
                status().is3xxRedirection()
            );

        assertEquals(1, partakingRepository.count());
        Partaking partaking =  partakingRepository.findAll().get(0);
        assertEquals(PartakingType.DEELNEMER, partaking.getPartakingType());
        assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), partaking.getPerson().getId());
    }

    @Test
    public void createPartakingForSamePersonTwiceDoesNothing() throws Exception {
        mockMvc()
            .perform(
                post("/events/10/partakings/")
                    .param("person", "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                    .param("partakingType", "DEELNEMER")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            );
        mockMvc()
            .perform(
                post("/events/10/partakings/")
                    .param("person", "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                    .param("partakingType", "DEELNEMER")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        );

        Event event = eventRepository.getOne(10L);
        assertEquals(1, event.getPartakings().size());
    }

    @Test
    public void searchPersonsReturns() throws Exception {
        mockMvc()
            .perform(
                get("/events/10/partakings/new")
                    .param("search", "Harry")
            )
            .andExpect(status().is2xxSuccessful())
            .andExpect(model().attribute("persons", hasSize(1)))
            .andExpect(model().attribute("searchTerm", "Harry"));
    }
}
