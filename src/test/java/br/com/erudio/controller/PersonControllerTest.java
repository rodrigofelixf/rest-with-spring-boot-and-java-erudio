package br.com.erudio.controller;

import br.com.erudio.requests.v1.requests.PersonRequestBody;
import br.com.erudio.requests.v1.responses.PersonResponseBody;
import br.com.erudio.unittests.mockito.services.PersonService;
import br.com.erudio.util.PersonCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static br.com.erudio.util.PersonCreator.createValidPersonResponse;


@SpringBootTest
class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personServiceMock;

    @BeforeEach
    void setUp(){
        List<PersonResponseBody> personResponseBodies = new ArrayList<>(List.of(createValidPersonResponse()));
        Mockito.when(personServiceMock.findAll())
                .thenReturn(List.of(createValidPersonResponse()));

        Mockito.when(personServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(createValidPersonResponse());

        Mockito.when(personServiceMock.create(ArgumentMatchers.any(PersonRequestBody.class)))
                .thenReturn(createValidPersonResponse());

        Mockito.when(personServiceMock.update(ArgumentMatchers.any(PersonResponseBody.class)))
                .thenReturn(createValidPersonResponse());
    }

    @Test
    @DisplayName("return a list of person when successful")
    void findAll_ReturnListOfPerson_WhenSuccessful() {
        String expectedFirstName = createValidPersonResponse().getFirstName();
        List<PersonResponseBody> personList = personController.findAll().getBody();

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
        PersonResponseBody person = personController.findById(1L).getBody();

        Assertions.assertThat(person).isNotNull();
        Assertions.assertThat(person.getKey()).isNotNull().isEqualTo(expectedId);

    }

    @Test
    @DisplayName("create save person when successful")
    void create_ReturnPerson_WhenSuccessful() {
        PersonResponseBody personSaved = personController.create(PersonCreator.createPersonRequestToBeSaved()).getBody();

        Assertions.assertThat(personSaved).isNotNull().isEqualTo(PersonCreator.createValidPersonResponse());

    }


    @Test
    @DisplayName("update person when successful")
    void update_Person_WhenSuccessful() {
        PersonResponseBody personToUpdate = PersonCreator.createValidUpdatePersonResponse();
        Mockito.when(personServiceMock.update(ArgumentMatchers.any(PersonResponseBody.class)))
                .thenReturn(personToUpdate);


        PersonResponseBody responseEntity = personController.update(personToUpdate).getBody();

        Mockito.verify(personServiceMock, Mockito.times(1)).update(personToUpdate);


        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity).isNotNull().isEqualTo(personToUpdate);
    }



}