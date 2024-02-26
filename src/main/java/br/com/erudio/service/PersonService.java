package br.com.erudio.service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;
import br.com.erudio.requests.v1.PersonResponseBody;
import br.com.erudio.requests.v1.PersonRequestBody;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    private Logger logger = Logger.getLogger(PersonService.class.getName());


    public List<PersonResponseBody> findAll() {
        List<Person> personList = personRepository.findAll();
        return personMapper.toPersonResponseBodyList(personList);
    }


    public PersonResponseBody findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        return personMapper.toPersonResponseBody(person);
    }

    @Transactional
    public PersonResponseBody create(PersonRequestBody personRequestBody) {
        Person person = personMapper.toPerson(personRequestBody);
        personRepository.save(person);

        return personMapper.toPersonResponseBody(person);
    }

    public void update(PersonResponseBody personResponseBody) {
        Person savedPerson = personRepository.findById(personResponseBody.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        Person person = PersonMapper.INSTANCE.toPerson(personResponseBody);
        person.setId(savedPerson.getId());
        personRepository.save(person);
    }

    public void delete(Long id) {
        Person person= personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        personRepository.delete(person);

    }





}


