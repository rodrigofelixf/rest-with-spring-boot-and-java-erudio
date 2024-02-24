package br.com.erudio.mapper;

import br.com.erudio.model.Person;
import br.com.erudio.requests.PersonGetRequestBody;
import br.com.erudio.requests.PersonPostRequestBody;
import br.com.erudio.requests.PersonPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PersonMapper {
   PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toPerson(PersonPostRequestBody personPostRequestBody);
    Person toPerson(PersonPutRequestBody personPutRequestBody);

    List<PersonGetRequestBody> toPersonGetRequestBodyList(List<Person> personList);
    PersonGetRequestBody toPersonGetRequestBody(Person person);
}
