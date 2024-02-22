package br.com.erudio.restwithspringbootandjavaerudio.service;

import br.com.erudio.restwithspringbootandjavaerudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;


@Service
public class PersonService {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }


    public Person findById(String id) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Rubinho");
        person.setLastName("Barriquelo");
        person.setAddress("Rua do Bairro");
        person.setGender("Male");
        return person;
    }

    public Person create(Person person) {
        logger.info("Creating one Person!");
        return person;
    }

    public Person update(Person person) {
        logger.info("Creating one Person!");
        return person;
    }

    public void delete(String id) {
        logger.info("Creating one Person!");
    }



    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Name " + i);
        person.setLastName("LastName " + i);
        person.setAddress("Same Address " + i);
        person.setGender("Male");
        return person;
    }

}


