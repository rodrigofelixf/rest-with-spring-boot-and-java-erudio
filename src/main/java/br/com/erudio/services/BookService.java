package br.com.erudio.services;

import br.com.erudio.controller.BookController;
import br.com.erudio.controller.PersonController;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.ErudioMapper;
import br.com.erudio.model.Book;
import br.com.erudio.repositories.BookRepository;
import br.com.erudio.requests.v1.requests.BookRequestBody;
import br.com.erudio.requests.v1.responses.BookResponseBody;
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

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ErudioMapper erudioMapper;

    @Autowired
    PagedResourcesAssembler<BookResponseBody> assembler;


    private Logger logger = Logger.getLogger(BookService.class.getName());


    public PagedModel<EntityModel<BookResponseBody>> findAll(Pageable pageable) {
        var bookList = bookRepository.findAll(pageable);
        var bookPageResponseBody = bookList.map(ErudioMapper.INSTANCE::toBookResponseBody);
        bookPageResponseBody.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));

        Link link = linkTo(
                methodOn(BookController.class)
                        .findAll(pageable.getPageNumber(),
                                pageable.getPageSize(),
                                "asc")
        ).withSelfRel();

        return assembler.toModel(bookPageResponseBody, link);
    }

    public BookResponseBody findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        BookResponseBody bookResponseBody = erudioMapper.toBookResponseBody(book);
        bookResponseBody.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());


        return bookResponseBody;
    }

    @Transactional
    public BookResponseBody create(BookRequestBody bookRequestBody) {
        Book book = erudioMapper.toBook(bookRequestBody);
        bookRepository.save(book);
        BookResponseBody bookResponseBody = erudioMapper.toBookResponseBody(book);
        bookResponseBody.add(linkTo(methodOn(BookController.class).findById(bookResponseBody.getKey())).withSelfRel());
        return bookResponseBody;
    }

    public BookResponseBody update(BookResponseBody bookResponseBody) {
        Book savedBook = bookRepository.findById(bookResponseBody.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        Book book = erudioMapper.toBook(bookResponseBody);
        book.setId(savedBook.getId());
        bookRepository.save(book);
        BookResponseBody bookResponse = erudioMapper.toBookResponseBody(book);
        bookResponse.add(linkTo(methodOn(BookController.class).findById(bookResponseBody.getKey())).withSelfRel());
        return bookResponse;
    }

    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        bookRepository.delete(book);

    }





}


