package com.david.MyBooksApi.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private String responseCode;
    private String responseMessage;
    private T data;


}
