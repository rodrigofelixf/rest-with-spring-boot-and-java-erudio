package br.com.erudio.controller;

import br.com.erudio.model.Person;
import br.com.erudio.requests.PersonGetRequestBody;
import br.com.erudio.requests.PersonPostRequestBody;
import br.com.erudio.requests.PersonPutRequestBody;
import br.com.erudio.requests.v2.PersonPostRequestBodyV2;
import br.com.erudio.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonGetRequestBody>> findAll(){
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonGetRequestBody> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PersonPostRequestBody> create(@RequestBody PersonPostRequestBody personPostRequestBody) {
        return new ResponseEntity<>(personService.create(personPostRequestBody), HttpStatus.CREATED);
    }
    @PostMapping(path = "/v2")
    public ResponseEntity<PersonPostRequestBodyV2> createV2(@RequestBody PersonPostRequestBodyV2 personPostRequestBody) {
        return new ResponseEntity<>(personService.createV2(personPostRequestBody), HttpStatus.CREATED);
    }



    @PutMapping
    public ResponseEntity<Void> update(@RequestBody PersonPutRequestBody personPutRequestBody) {
        personService.update(personPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
