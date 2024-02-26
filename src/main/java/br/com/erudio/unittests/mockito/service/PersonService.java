package br.com.erudio.unittests.mockito.service;

import br.com.erudio.controller.PersonController;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;
import br.com.erudio.requests.v1.PersonResponseBody;
import br.com.erudio.requests.v1.PersonRequestBody;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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
        List<PersonResponseBody> personResponseBodyList = personMapper.toPersonResponseBodyList(personList);
        personResponseBodyList.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

        return personResponseBodyList;
    }


    public PersonResponseBody findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        PersonResponseBody personResponse = personMapper.toPersonResponseBody(person);
        personResponse.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return personResponse;
    }

    @Transactional
    public PersonResponseBody create(PersonRequestBody personRequestBody) {
        Person person = personMapper.toPerson(personRequestBody);
        personRepository.save(person);
        PersonResponseBody personResponse = personMapper.toPersonResponseBody(person);
        personResponse.add(linkTo(methodOn(PersonController.class).findById(personResponse.getKey())).withSelfRel());

        return personResponse;
    }

    public PersonResponseBody update(PersonResponseBody personResponseBody) {
        Person savedPerson = personRepository.findById(personResponseBody.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        Person person = personMapper.toPerson(personResponseBody);
        person.setId(savedPerson.getId());
        personRepository.save(person);
        PersonResponseBody personResponse = personMapper.toPersonResponseBody(person);
        personResponse.add(linkTo(methodOn(PersonController.class).findById(personResponse.getKey())).withSelfRel());

        return personResponse;
    }

    public void delete(Long id) {
        Person person= personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        personRepository.delete(person);

    }





}


