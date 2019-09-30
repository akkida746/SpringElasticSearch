package com.example.springes.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * @author Akash Deep
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
@Data
public class Book {
    private String id;
    private String title;
    private String author;
    private float price;
}
