package com.david.MyBooksApi.util;

import com.david.MyBooksApi.dto.request.BookRequest;
import com.david.MyBooksApi.model.Book;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static LocalDateTime dateFormatter(){
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a z");
        return LocalDateTime.parse(FORMATTER.format(LocalDateTime.now()));
    }
    public static Book setBook(BookRequest bookRequest){
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublishedOn(bookRequest.getPublishedOn());
        book.setCreatedDate(LocalDateTime.now());
        book.setUpdatedDate(LocalDateTime.now());
        return book;
    }

}
