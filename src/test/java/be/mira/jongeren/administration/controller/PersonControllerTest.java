package be.mira.jongeren.administration.controller;

import be.mira.jongeren.administration.repository.PersonRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PersonControllerTest extends MockMvcTest{

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void doPostPersistsPerson() throws Exception {
        mockMvc()
            .perform(
                post("/persons/")
                    .param("voornaam", "Hermione")
                    .param("achternaam", "Granger")
                    .param("gender", "F")
                    .param("birthDate","1979-09-19")
                    .param("postcode", "1850")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            )
            .andExpect(
                    status().is3xxRedirection()
            );

        assertThat(personRepository.findAll(), hasSize(equalTo(1)));
    }
}
