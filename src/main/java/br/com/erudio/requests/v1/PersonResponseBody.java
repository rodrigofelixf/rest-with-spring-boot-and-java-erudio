package br.com.erudio.requests.v1;


import lombok.Builder;
import lombok.Data;



@Builder
@Data
public class PersonResponseBody {
    private Long id;

    private String firstName;


    private String lastName;


    private String address;


    private String gender;
}
