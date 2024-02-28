package br.com.erudio.requests.v1.responses;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;


@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "firstName", "lastName", "address", "gender"})
public class BookResponseBody extends RepresentationModel<BookResponseBody> {

    @JsonProperty("id")
    private Long key;

    private String author;

    private Date launchDate;

    private Double price;

    private String title;
}
