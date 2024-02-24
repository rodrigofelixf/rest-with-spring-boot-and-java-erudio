package br.com.erudio.mapper;

import br.com.erudio.model.Person;
import br.com.erudio.requests.PersonGetRequestBody;
import br.com.erudio.requests.PersonPostRequestBody;
import br.com.erudio.requests.PersonPutRequestBody;
import br.com.erudio.requests.v2.PersonGetRequestBodyV2;
import br.com.erudio.requests.v2.PersonPostRequestBodyV2;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.List;


@Mapper(componentModel = "spring")
public interface PersonMapper {
   PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toPerson(PersonPostRequestBody personPostRequestBody);
    Person toPerson(PersonPutRequestBody personPutRequestBody);

    List<PersonGetRequestBody> toPersonGetRequestBodyList(List<Person> personList);
    PersonGetRequestBody toPersonGetRequestBody(Person person);
    PersonPostRequestBody toPersonPostRequestBody(Person person);

    default PersonPostRequestBodyV2 toPersonPostRequestBodyV2(Person person) {
        return PersonPostRequestBodyV2.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .address(person.getAddress())
                .gender(person.getGender())
                // Defina o campo dataNascimento conforme necessário
                .birthday(new Date()) // Você pode definir um valor padrão ou deixar null, dependendo dos requisitos
                .build();
    }

    default Person toPerson(PersonPostRequestBodyV2 person) {
        return Person.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .address(person.getAddress())
                .gender(person.getGender())
                // Defina o campo dataNascimento conforme necessário
//                .birthday(new Date()) // Você pode definir um valor padrão ou deixar null, dependendo dos requisitos
                .build();
    }
}
