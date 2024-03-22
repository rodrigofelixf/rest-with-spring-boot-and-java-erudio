package br.com.erudio.requests.v1.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookRequestBody {


    private String author;


    private Date launchDate;


    private Double price;


    private String title;
}
