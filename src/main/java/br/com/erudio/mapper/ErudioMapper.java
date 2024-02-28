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
public interface ErudioMapper {
    ErudioMapper INSTANCE = Mappers.getMapper(ErudioMapper.class);

    PersonRequestBody toPersonRequestBody(Person person);

    @Mapping(target = "key", source = "id")
    PersonResponseBody toPersonResponseBody(Person person);

    Person toPerson(PersonRequestBody personRequestBody);

    Person toPerson(PersonResponseBody personResponseBody);

    List<PersonResponseBody> toPersonResponseBodyList(List<Person> personList);


    BookRequestBody toBookRequestBody(Book book);

    @Mapping(target = "key", source = "id")
    BookResponseBody toBookResponseBody(Book book);

    Book toBook(BookRequestBody bookRequestBody);

    Book toBook(BookResponseBody bookResponseBody);

    List<BookResponseBody> toBookResponseBodyList(List<Book> bookList);

}
