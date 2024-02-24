package br.com.erudio.requests.v2;


import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class PersonGetRequestBodyV2 {

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;

    private Date birthday;
}
