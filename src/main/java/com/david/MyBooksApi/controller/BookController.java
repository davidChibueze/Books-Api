package com.david.MyBooksApi.controller;

import com.david.MyBooksApi.dto.request.BookRequest;

import com.david.MyBooksApi.model.Book;

import com.david.MyBooksApi.dto.response.Response;

import com.david.MyBooksApi.model.Category;
import com.david.MyBooksApi.service.BookService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
public class BookController {


    private final BookService bookService;


    @GetMapping("/books")
    public ResponseEntity<Response<List<Book>>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}/books")
    public ResponseEntity<Response<List<Book>>> getAllBooksByCategoryId(@PathVariable(value = "categoryId") Long categoryId) {
        return new ResponseEntity<>(bookService.getAllBooksByCategoryId(categoryId), HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Response<Book>> createBook(@Valid @RequestBody BookRequest bookRequest) {
        return new ResponseEntity<>(bookService.getCreateBook(bookRequest), HttpStatus.CREATED);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Response<Book>> getBooksById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(bookService.getBooksById(id), HttpStatus.OK);
    }


    @GetMapping("/books/{bookId}/categories")
    public ResponseEntity<Response<List<Category>>> getAllCategoriesByBookId(@PathVariable(value = "bookId") Long bookId) {
        return new ResponseEntity<>(bookService.getAllCategoriesByBookId(bookId), HttpStatus.OK);
    }



    @PostMapping("/categories/{categoryId}/books")
    public ResponseEntity<Response<Book>> addBook(@PathVariable(value = "categoryId") Long categoryId, @RequestBody BookRequest bookRequest) {
        return new ResponseEntity<>(bookService.addBook(categoryId,bookRequest), HttpStatus.CREATED);
    }


    @PutMapping("/books/{id}")
    public ResponseEntity<Response<Book>> updateBook(@PathVariable("id") long id, @RequestBody BookRequest bookRequest) {
        return new ResponseEntity<>(bookService.updateBook(id,bookRequest), HttpStatus.OK);
    }

    @DeleteMapping("/categories/{categoryId}/books/{bookId}")
    public ResponseEntity<HttpStatus> deleteBookFromCategory(@PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "bookId") Long bookId) {
        return bookService.deleteBookFromCategory(categoryId, bookId);
    }


    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
        return bookService.deleteBook(id);
    }
}