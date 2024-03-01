package br.com.erudio.unittests.mockito.services;

import br.com.erudio.mapper.ErudioMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

import br.com.erudio.requests.v1.responses.PersonResponseBody;
import br.com.erudio.util.PersonCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

import static br.com.erudio.util.PersonCreator.*;

@SpringBootTest
class PersonServiceTest {
    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepositoryMock;


    @BeforeEach
    void setUp(){
        Mockito.when(personRepositoryMock.findAll())
                .thenReturn(List.of(createValidPerson()));

        Mockito.when(personRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(createValidPerson()));

        Mockito.when(personRepositoryMock.save(ArgumentMatchers.any(Person.class)))
                .thenReturn(ErudioMapper.INSTANCE.toPerson(createPersonRequestToBeSaved()));

        Mockito.doNothing().when(personRepositoryMock).delete(ArgumentMatchers.any(Person.class));
    }

    @Test
    @DisplayName("Return a list of person when successful")
    void findAll_ReturnListOfPerson_WhenSuccessful() {
        String expectedFirstName = createValidPersonResponse().getFirstName();
        List<PersonResponseBody> personList = personService.findAll();

        Assertions.assertThat(personList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(personList.get(0).getFirstName()).isEqualTo(expectedFirstName);

    }

    @Test
    @DisplayName("FindById  return person when successful")
    void findById_ReturnPerson_WhenSuccessful() {
        Long expectedId = createValidPersonResponse().getKey();
        PersonResponseBody person = personService.findById(1L);

        Assertions.assertThat(person).isNotNull();
        Assertions.assertThat(person.getKey()).isNotNull().isEqualTo(expectedId);

    }

    @Test
    @DisplayName("Create save person when successful")
    void create_ReturnPerson_WhenSuccessful() {
        PersonResponseBody personSaved = personService.create(PersonCreator.createPersonRequestToBeSaved());

        Assertions.assertThat(personSaved)
                .isNotNull().isEqualTo(PersonCreator.createPersonResponseToBeSaved());


    }


    @Test
    @DisplayName("Update person when successful")
    void update_Person_WhenSuccessful() {
        PersonResponseBody personToUpdate = PersonCreator.createValidUpdatePersonResponse();

        Assertions.assertThatCode(() -> personService.update(personToUpdate))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("Delete removes person when successful")
    void delete_RemovePerson_WhenSuccessful() {

        Assertions.assertThatCode(() -> personService.delete(1L))
                .doesNotThrowAnyException();

    }

}