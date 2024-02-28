package br.com.erudio.requests.v1.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonRequestBody {


    private String firstName;


    private String lastName;


    private String address;


    private String gender;
}
