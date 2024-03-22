package br.com.erudio.util;

import br.com.erudio.model.Person;
import br.com.erudio.requests.v1.requests.PersonRequestBody;
import br.com.erudio.requests.v1.responses.PersonResponseBody;

public class PersonCreator {


    public static Person createPersonToBeSaved() {
        return Person.builder()
                .firstName("Aristofeu")
                .lastName("Aristofoles")
                .address("Rua dos Aristofarios")
                .gender("male")
                .build();
    }

    public static  Person createValidPerson() {
        return Person.builder()
                .id(1L)
                .firstName("Aristofeu")
                .lastName("Aristofoles")
                .address("Rua dos Aristofarios")
                .gender("male")
                .build();
    }

    public static  Person createValidUpdatePerson() {
        return Person.builder()
                .id(1L)
                .firstName("Aristofeu II")
                .lastName("Aristofoles Jr")
                .address("Rua dos Aristofarios st")
                .gender("unknown")
                .build();
    }




    public static PersonRequestBody createPersonRequestToBeSaved() {
        return PersonRequestBody.builder()
                .firstName("Aristofeu")
                .lastName("Aristofoles")
                .address("Rua dos Aristofarios")
                .gender("male")
                .build();
    }

    public static PersonResponseBody createPersonResponseToBeSaved() {
        return PersonResponseBody.builder()
                .firstName("Aristofeu")
                .lastName("Aristofoles")
                .address("Rua dos Aristofarios")
                .gender("male")
                .build();
    }

    public static  PersonResponseBody createValidPersonResponse() {
        return PersonResponseBody.builder()
                .key(1L)
                .firstName("Aristofeu")
                .lastName("Aristofoles")
                .address("Rua dos Aristofarios")
                .gender("male")
                .build();
    }

    public static  PersonResponseBody createValidUpdatePersonResponse() {
        return PersonResponseBody.builder()
                .key(1L)
                .firstName("Aristofeu II")
                .lastName("Aristofoles Jr")
                .address("Rua dos Aristofarios st")
                .gender("unknown")
                .build();
    }
}
