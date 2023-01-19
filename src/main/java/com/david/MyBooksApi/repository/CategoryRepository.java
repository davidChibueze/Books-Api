package com.david.MyBooksApi.repository;

import com.david.MyBooksApi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findCategoriesByBooksId(Long bookId);

    List<Category> findByTitleContaining(String title);



}
