package br.com.erudio.service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;
import br.com.erudio.requests.PersonGetRequestBody;
import br.com.erudio.requests.PersonPostRequestBody;
import br.com.erudio.requests.PersonPutRequestBody;
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


    public List<PersonGetRequestBody> findAll() {
        List<Person> personList = personRepository.findAll();
        return personMapper.toPersonGetRequestBodyList(personList);
    }


    public PersonGetRequestBody findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        return personMapper.toPersonGetRequestBody(person);
    }

    @Transactional
    public Person create(PersonPostRequestBody personPostRequestBody) {
        Person toPerson = PersonMapper.INSTANCE.toPerson(personPostRequestBody);
        return personRepository.save(toPerson);
    }

    public void update(PersonPutRequestBody personPutRequestBody) {
        Person savedPerson = personRepository.findById(personPutRequestBody.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        Person person = PersonMapper.INSTANCE.toPerson(personPutRequestBody);
        person.setId(savedPerson.getId());
        personRepository.save(person);
    }

    public void delete(Long id) {
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        personRepository.delete(entity);

    }





}


