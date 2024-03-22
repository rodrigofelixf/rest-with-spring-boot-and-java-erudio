package br.com.erudio.mapper;

import br.com.erudio.model.Book;
import br.com.erudio.model.Person;
import br.com.erudio.requests.v1.requests.BookRequestBody;
import br.com.erudio.requests.v1.responses.BookResponseBody;
import br.com.erudio.requests.v1.responses.PersonResponseBody;
import br.com.erudio.requests.v1.requests.PersonRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")

public abstract class ErudioMapper {
    public static final ErudioMapper INSTANCE = Mappers.getMapper(ErudioMapper.class);

    public abstract PersonRequestBody toPersonRequestBody(Person person);

    @Mapping(target = "key", source = "id")
    public abstract PersonResponseBody toPersonResponseBody(Person person);

    public abstract Person toPerson(PersonRequestBody personRequestBody);

    public abstract Person toPerson(PersonResponseBody personResponseBody);

    public abstract List<PersonResponseBody> toPersonResponseBodyList(List<Person> personList);


    public abstract BookRequestBody toBookRequestBody(Book book);

    @Mapping(target = "key", source = "id")
    public abstract BookResponseBody toBookResponseBody(Book book);

    public abstract Book toBook(BookRequestBody bookRequestBody);

    public abstract Book toBook(BookResponseBody bookResponseBody);

    public abstract List<BookResponseBody> toBookResponseBodyList(List<Book> bookList);

}
