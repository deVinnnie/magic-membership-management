package be.mira.jongeren.administration.controller;

import be.mira.jongeren.administration.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EventControllerTest extends MockMvcTest{

    @Autowired
    private EventRepository eventRepository;

    @Test
    void doPostPersistsEvent() throws Exception {
        mockMvc()
            .perform(
                post("/events/")
                    .param("eventType", "SUPERNOVA")
                    .param("datum", "2016-01-01")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            )
            .andExpect(
                    status().is3xxRedirection()
            );

        assertThat(eventRepository.findAll(), hasSize(equalTo(1)));
        assertNotNull(eventRepository.getOne(201601010L));
    }
}
