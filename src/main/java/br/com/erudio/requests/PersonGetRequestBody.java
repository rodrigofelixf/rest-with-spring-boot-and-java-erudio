package br.com.erudio.requests;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class PersonGetRequestBody {

    private Long id;


    private String firstName;


    private String lastName;


    private String address;


    private String gender;
}
