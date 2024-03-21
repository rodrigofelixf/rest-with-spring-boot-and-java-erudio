package br.com.erudio.services;

import br.com.erudio.controller.PersonController;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.ErudioMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import br.com.erudio.requests.v1.requests.PersonRequestBody;
import br.com.erudio.requests.v1.responses.PersonResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    PagedResourcesAssembler<PersonResponseBody> assembler;


    private Logger logger = Logger.getLogger(PersonService.class.getName());


    public PagedModel<EntityModel<PersonResponseBody>> findAll(Pageable pageable) {
        logger.info("Finding all people!");

        var personPageList = personRepository.findAll(pageable);
        var personPageResponseList = personPageList.map(ErudioMapper.INSTANCE::toPersonResponseBody);
        personPageResponseList.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

        Link link = linkTo(
                methodOn(PersonController.class)
                        .findAll(pageable.getPageNumber(),
                                pageable.getPageSize(),
                                "asc")
        ).withSelfRel();

        return assembler.toModel(personPageResponseList, link);
    }

    public PagedModel<EntityModel<PersonResponseBody>> findPersonByName(String firstName, Pageable pageable) {
        logger.info("Finding all people!");

        var personPageList = personRepository.findPersonByName(firstName, pageable);
        var personPageResponseList = personPageList.map(ErudioMapper.INSTANCE::toPersonResponseBody);
        personPageResponseList.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

        Link link = linkTo(
                methodOn(PersonController.class)
                        .findAll(pageable.getPageNumber(),
                                pageable.getPageSize(),
                                "asc")
        ).withSelfRel();

        return assembler.toModel(personPageResponseList, link);
    }


    public PersonResponseBody findById(Long id) {

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        PersonResponseBody personResponse = ErudioMapper.INSTANCE.toPersonResponseBody(person);
        personResponse.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());


        return personResponse;
    }

    @jakarta.transaction.Transactional
    public PersonResponseBody create(PersonRequestBody personRequestBody) {
        Person person = ErudioMapper.INSTANCE.toPerson(personRequestBody);
        personRepository.save(person);
        PersonResponseBody personResponse = ErudioMapper.INSTANCE.toPersonResponseBody(person);
        personResponse.add(linkTo(methodOn(PersonController.class).findById(personResponse.getKey())).withSelfRel());

        return personResponse;
    }

    public PersonResponseBody update(PersonResponseBody personResponseBody) {
        Person savedPerson = personRepository.findById(personResponseBody.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        Person person = ErudioMapper.INSTANCE.toPerson(personResponseBody);
        person.setId(savedPerson.getId());
        personRepository.save(person);
        PersonResponseBody personResponse = ErudioMapper.INSTANCE.toPersonResponseBody(person);
        personResponse.add(linkTo(methodOn(PersonController.class).findById(personResponse.getKey())).withSelfRel());

        return personResponse;
    }

    @Transactional
    public PersonResponseBody disablePerson(Long id) {
        personRepository.disablePerson(id);
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        PersonResponseBody personResponse = ErudioMapper.INSTANCE.toPersonResponseBody(person);
        personResponse.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return personResponse;
    }

    public void delete(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        personRepository.delete(person);

    }


}


