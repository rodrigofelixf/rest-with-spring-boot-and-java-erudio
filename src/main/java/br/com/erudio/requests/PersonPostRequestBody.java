package br.com.erudio.requests;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonPostRequestBody {

    private String firstName;


    private String lastName;


    private String address;


    private String gender;
}
