package com.david.MyBooksApi.repository;

import com.david.MyBooksApi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByCategoriesId(Long categoryId);

}
