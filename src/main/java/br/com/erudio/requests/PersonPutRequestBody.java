package br.com.erudio.requests;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonPutRequestBody {

    private Long id;


    private String firstName;


    private String lastName;


    private String address;


    private String gender;
}
