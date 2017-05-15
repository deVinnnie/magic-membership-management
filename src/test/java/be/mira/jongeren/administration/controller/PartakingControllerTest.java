package be.mira.jongeren.administration.controller;

import be.mira.jongeren.administration.domain.Partaking;
import be.mira.jongeren.administration.domain.PartakingType;
import be.mira.jongeren.administration.repository.PartakingRepository;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.ninja_squad.dbsetup.Operations.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PartakingControllerTest extends MockMvcTest{

    @Autowired
    private PartakingRepository partakingRepository;

    @Before
    public void setup(){
        Operation operation = sequenceOf(
                deleteAllFrom("partaking", "event", "person"),
                insertInto("person")
                        .columns("id", "voornaam", "achternaam", "gender")
                        .values("15", "Harry", "Potter", "M")
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
    public void createMembership() throws Exception {
        mockMvc()
                .perform(
                    post("/events/10/partakings/")
                        .param("person", "15")
                        .param("partakingType", "DEELNEMER")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(
                    status().is3xxRedirection()
                );

        assertEquals(1, partakingRepository.count());
        Partaking partaking =  partakingRepository.findAll().get(0);
        assertEquals(PartakingType.DEELNEMER, partaking.getPartakingType());
        assertEquals(15L, (long) partaking.getPerson().getId());
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
