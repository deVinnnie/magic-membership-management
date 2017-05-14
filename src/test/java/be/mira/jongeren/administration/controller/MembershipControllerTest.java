package be.mira.jongeren.administration.controller;

import be.mira.jongeren.administration.repository.MembershipRepository;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MembershipControllerTest extends MockMvcTest{

    @Autowired
    private MembershipRepository membershipRepository;

    @Before
    public void setup() {
        Operation operation = sequenceOf(
                deleteAllFrom("partaking", "event", "person"),
                insertInto("person")
                        .columns("id", "voornaam", "achternaam", "gender")
                        .values("15", "Harry", "Potter", "M")
                        .build()
        );
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource()), operation);
        dbSetup.launch();
    }

    @Test
    public void createMembership() throws Exception {
        mockMvc()
                .perform(
                        post("/memberships/")
                                .param("person.id", "15")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(
                        status().is3xxRedirection()
                );

        assertEquals(1, membershipRepository.count());
    }
}