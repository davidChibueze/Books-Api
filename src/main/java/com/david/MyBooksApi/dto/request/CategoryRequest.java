package com.david.MyBooksApi.dto.request;


import lombok.Data;

@Data
public class CategoryRequest {
    private String title;
    private String description;
}
