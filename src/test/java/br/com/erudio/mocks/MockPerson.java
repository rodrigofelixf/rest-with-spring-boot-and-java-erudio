package br.com.erudio.mocks;

import br.com.erudio.model.Person;
import br.com.erudio.requests.v1.PersonResponseBody;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {

    public Person mockEntity() {
        return mockEntity(0);
    }

    public PersonResponseBody mockPersonResponse() {
        return mockPersonResponse(0);
    }

    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonResponseBody> mockResponseList() {
        List<PersonResponseBody> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockPersonResponse(i));
        }
        return persons;
    }

    private Person mockEntity(Integer number) {
        Person person = new Person();
        person.setAddress("Addres Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

    private PersonResponseBody mockPersonResponse(Integer number) {
        PersonResponseBody person = new PersonResponseBody();
        person.setAddress("Addres Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setKey(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }
}
