package br.com.erudio.requests.v1.responses;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "firstName", "lastName", "address", "gender", "enabled"})
public class PersonResponseBody extends RepresentationModel<PersonResponseBody> {


    @JsonProperty("id")
    private Long key;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;

    private Boolean enabled;

}
