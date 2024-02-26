package br.com.erudio.mapper;

import br.com.erudio.model.Person;
import br.com.erudio.requests.v1.PersonResponseBody;
import br.com.erudio.requests.v1.PersonRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PersonMapper {
   PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonRequestBody toPersonRequestBody(Person person);


    @Mapping(target = "key", source = "id")
    PersonResponseBody toPersonResponseBody(Person person);

    Person toPerson(PersonRequestBody personRequestBody);
    Person toPerson(PersonResponseBody personResponseBody);

    List<PersonResponseBody> toPersonResponseBodyList(List<Person> personList);

}
