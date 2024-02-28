package br.com.erudio.repositories;

import br.com.erudio.model.Person;
import br.com.erudio.util.PersonCreator;
import org.assertj.core.api.Assertions;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;


@DisplayName("Tests for Anime Repository")
@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @DisplayName("Save create person when successful")
    void save_PersistPerson_WhenSuccessful() {

        Person personToBeSaved = PersonCreator.createPersonToBeSaved();
        Person personSaved = this.personRepository.save(personToBeSaved);
        assertPerson(personSaved, personToBeSaved);

        Optional<Person> retrievedPerson = personRepository.findById(personSaved.getId());
        Assertions.assertThat(retrievedPerson).isPresent().contains(personSaved);
    }

    @Test
    @DisplayName("Save updates person when successful")
    void save_UpdatePerson_WhenSuccessful() {

        Person personToBeSaved = PersonCreator.createPersonToBeSaved();
        Person personSaved = this.personRepository.save(personToBeSaved);

        personSaved.setFirstName("Maradona");
        Person personUpdated = this.personRepository.save(personSaved);
        assertPerson(personUpdated, personSaved);

        Optional<Person> retrievedPerson = personRepository.findById(personUpdated.getId());
        Assertions.assertThat(retrievedPerson).isPresent().contains(personUpdated);


    }

    @Test
    @DisplayName("Delete removes person when successful")
    void delete_RemovePerson_WhenSuccessful() {

        Person personToBeSaved = PersonCreator.createPersonToBeSaved();
        Person personSaved = this.personRepository.save(personToBeSaved);

        this.personRepository.delete(personSaved);

        Optional<Person> personOptional = this.personRepository.findById(personSaved.getId());
        Assertions.assertThat(personOptional).isNotPresent();
    }

    @Test
    @DisplayName("Save throws ConstraintViolationException when first name is empty")
    void save_ThrowsConstraintViolationException_WhenFistNameIsEmpty() {
        Person person = new Person();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.personRepository.save(person))
                .withMessageContaining("a");


    }



    private void assertPerson(Person actual, Person expected) {
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getId()).isNotNull();

        Assertions.assertThat(actual.getFirstName()).isEqualTo(expected.getFirstName());
        Assertions.assertThat(actual.getLastName()).isEqualTo(expected.getLastName());
        Assertions.assertThat(actual.getAddress()).isEqualTo(expected.getAddress());
        Assertions.assertThat(actual.getGender()).isEqualTo(expected.getGender());
    }

}
