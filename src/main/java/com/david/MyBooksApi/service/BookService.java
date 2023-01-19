package com.david.MyBooksApi.service;


import com.david.MyBooksApi.dto.request.BookRequest;
import com.david.MyBooksApi.dto.response.Response;
import com.david.MyBooksApi.exception.ResourceNotFoundException;
import com.david.MyBooksApi.model.Book;
import com.david.MyBooksApi.model.Category;
import com.david.MyBooksApi.repository.BookRepository;
import com.david.MyBooksApi.repository.CategoryRepository;
import com.david.MyBooksApi.util.Util;
import com.david.MyBooksApi.util.ResponseFormatter;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.david.MyBooksApi.util.ResponseCode.*;


@RequiredArgsConstructor
@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;



    public Response<List<Book>> getAllBooks(){

        List<Book> books = bookRepository.findAll();
        log.info("When the books object is {}",books);
        if (books.isEmpty()) {
            return ResponseFormatter.getResponse(new ArrayList<Book>(),NO_CONTENT);
        }
        return ResponseFormatter.getResponse(books,COMPLETED_SUCCESSFULLY);

    }

    public Response<List<Book>> getAllBooksByCategoryId(Long categoryId){
        if (!categoryRepository.existsById(categoryId)) {
            log.info("Exeption found",new ResourceNotFoundException("Not found Category with id = " + categoryId));
            return ResponseFormatter.getResponse(new ArrayList<Book>(),NO_CONTENT);
//            throw new ResourceNotFoundException("Not found Category with id = " + categoryId);
        }
        return ResponseFormatter.getResponse(bookRepository.findBooksByCategoriesId(categoryId), COMPLETED_SUCCESSFULLY);
    }

    public Response<Book> getBooksById(Long id) {
//        Book book = bookRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Not found Book with id = " + id));
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (!bookOptional.isPresent()){
            log.info("bookId not found {} ", id);
            return ResponseFormatter.getResponse(new Book(),NO_CONTENT);
        }
        log.info("book that was found {}", bookOptional.get());
        return ResponseFormatter.getResponse(bookOptional.get(), COMPLETED_SUCCESSFULLY);
    }


    public Response<Book> getCreateBook(BookRequest bookRequest) {
        Book book = Util.setBook(bookRequest);
        return ResponseFormatter.getResponse(bookRepository.save(book),COMPLETED_SUCCESSFULLY);

    }


    public Response<List<Category>> getAllCategoriesByBookId(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new ResourceNotFoundException("Not found Book  with id = " + bookId);
        }
        List<Category> categories = categoryRepository.findCategoriesByBooksId(bookId);
        return ResponseFormatter.getResponse(categories,COMPLETED_SUCCESSFULLY);
    }

    public Response<Book> addBook(Long categoryId, BookRequest bookRequest) {

        "".toUpperCase();
        List<String> alpha = Arrays.asList("a", "b", "c", "d");
        List<String> collect = alpha.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> collect2 = alpha.stream().map(s -> { return s.toUpperCase(); }).collect(Collectors.toList());


        Book book = categoryRepository.findById(categoryId).map(category -> {
            Long bookId = bookRequest.getId();

            if (Objects.nonNull(bookRequest.getId())) {
                Book _book = bookRepository.findById(bookId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Book with id = " + bookId));
                category.addBook(_book);
                categoryRepository.save(category);
                return _book;
            }

            // add and create new Book
            Book newBook = createBookFromRequest(bookRequest);
            category.addBook(newBook);
            categoryRepository.save(category);
            return bookRepository.save(newBook);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Category with id = " + categoryId));

        return ResponseFormatter.getResponse(book,COMPLETED_SUCCESSFULLY);
    }

    public Book createBookFromRequest(BookRequest bookRequest) {
        return Util.setBook(bookRequest);
    }

    public Response<Book> updateBook(long id, BookRequest bookRequest) {
        Optional<Book> bookOptional = bookRepository.findById(id);
//                .orElseThrow(() -> new ResourceNotFoundException("BookId " + id + "not found"));
        if(!bookOptional.isPresent()){
            return ResponseFormatter.getResponse(new Book(),NO_CONTENT);
        }
        Book book = bookOptional.get();
        book.setTitle(bookRequest.getTitle());

        return ResponseFormatter.getResponse(bookRepository.save(book),COMPLETED_SUCCESSFULLY);
    }


    public ResponseEntity<HttpStatus> deleteBookFromCategory(Long categoryId, Long bookId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Category with id = " + categoryId));

        category.removeBook(bookId);
        categoryRepository.save(category);

        log.info("The book with the id = {} has being deleted ", bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<HttpStatus> deleteBook(long id) {
        bookRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
