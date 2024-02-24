package br.com.erudio.requests.v2;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class PersonPutRequestBodyV2 {

    private Long id;


    private String firstName;


    private String lastName;


    private String address;


    private String gender;

    private Date birthday;
}
