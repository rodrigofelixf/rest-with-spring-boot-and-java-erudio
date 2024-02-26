package br.com.erudio.controller;

import br.com.erudio.requests.v1.PersonResponseBody;
import br.com.erudio.requests.v1.PersonRequestBody;
import br.com.erudio.service.PersonService;
import br.com.erudio.util.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/person/v1")
public class PersonController {

    private final PersonService personService;

    @GetMapping(produces = { MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML
    })
    public ResponseEntity<List<PersonResponseBody>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping(value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML
            })
    public ResponseEntity<PersonResponseBody> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    public ResponseEntity<PersonResponseBody> create(@RequestBody PersonRequestBody personRequestBody) {
        return new ResponseEntity<>(personService.create(personRequestBody), HttpStatus.CREATED);
    }

    @PutMapping(consumes = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML}
    )
    public ResponseEntity<Void> update(@RequestBody PersonResponseBody personResponseBody) {
        personService.update(personResponseBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
