package com.david.MyBooksApi.dto.request;


import lombok.*;

@Data
public class BookRequest {

    private Long id;
    private String title;
    private String author;
    private String publishedOn;

}
