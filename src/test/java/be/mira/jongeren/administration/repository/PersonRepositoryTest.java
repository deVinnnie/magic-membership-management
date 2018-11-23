package be.mira.jongeren.administration.repository;

import be.mira.jongeren.administration.controller.MockMvcTest;
import be.mira.jongeren.administration.domain.Person;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

class PersonRepositoryTest extends MockMvcTest{

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void setup(){
        Operation operation = sequenceOf(
                deleteAllFrom("partaking", "event", "person"),
                insertInto("person")
                        .columns("id", "voornaam", "achternaam")
                        .values("A0EEBC999C0B4EF8BB6D6BB9BD380A11", "Harry", "Potter")
                        .values("B0EEBC999C0B4EF8BB6D6BB9BD380A11", "Hermione", "Granger")
                        .values("C0EEBC999C0B4EF8BB6D6BB9BD380A11", "Ron", "Weasley")
                        .build()
        );
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
    }

    @Test
    void searchByNameWithFullFirstName() {
        List<Person> results = personRepository.searchByName("Harry");
        assertThat(results, hasSize(equalTo(1)));
    }

    @Test
    void searchByNameWithFullLastName() {
        List<Person> results = personRepository.searchByName("Granger");
        assertThat(results, hasSize(equalTo(1)));
    }

    @Test
    void searchByNameWithSingleLetter() {
        List<Person> results = personRepository.searchByName("H");
        assertThat(results, hasSize(equalTo(2)));
    }

    @Test
    void searchByNameWithFirstNameAndLastName() {
        List<Person> results = personRepository.searchByName("rry Po");
        assertThat(results, hasSize(equalTo(1)));
    }

    @Test
    void searchByNameWithLowerCaseTerm() {
        List<Person> results = personRepository.searchByName("harry");
        assertThat(results, hasSize(equalTo(1)));
    }

    @Test
    void searchByNameWithEmptyStringReturnsAllPersons() {
        List<Person> results = personRepository.searchByName("");
        assertThat(results, hasSize(equalTo(3)));
    }

    @Test
    void searchByNameWithNullReturnsEmptyList() {
        List<Person> results = personRepository.searchByName(null);
        assertThat(results, hasSize(equalTo(0)));
    }
}
