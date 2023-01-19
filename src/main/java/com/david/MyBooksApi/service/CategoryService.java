package com.david.MyBooksApi.service;


import com.david.MyBooksApi.dto.request.CategoryRequest;
import com.david.MyBooksApi.dto.response.Response;
import com.david.MyBooksApi.exception.ResourceNotFoundException;
import com.david.MyBooksApi.model.Category;
import com.david.MyBooksApi.repository.CategoryRepository;
import com.david.MyBooksApi.util.ResponseFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.david.MyBooksApi.util.ResponseCode.COMPLETED_SUCCESSFULLY;
import static com.david.MyBooksApi.util.ResponseCode.NO_CONTENT;


@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Response<Category> getCreateCategory(CategoryRequest categoryRequest) {
        Category _category = categoryRepository.save(new Category(categoryRequest.getTitle(), categoryRequest.getDescription()));
        return ResponseFormatter.getResponse(_category, COMPLETED_SUCCESSFULLY);
    }


    public Response<List<Category>> getAllCategories(String title) {
        List<Category> categories = new ArrayList<Category>();


        if (title == null) {
            categories.addAll(categoryRepository.findAll());

        }else {
            categories.addAll(categoryRepository.findByTitleContaining(title));

        }
        if (categories.isEmpty()) {
            return  ResponseFormatter.getResponse(categories, NO_CONTENT);
        }

        return ResponseFormatter.getResponse(categories, COMPLETED_SUCCESSFULLY);
    }


    public Response<Category> getCategoryById(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Category with id = " + id));

        return ResponseFormatter.getResponse(category, COMPLETED_SUCCESSFULLY);
    }


    public Response<Category> UpdateCategory(long id, CategoryRequest categoryRequest) {
        Category _category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Category with id = " + id));

        _category.setTitle(categoryRequest.getTitle());
        _category.setDescription(categoryRequest.getDescription());

        return ResponseFormatter.getResponse(categoryRepository.save(_category), COMPLETED_SUCCESSFULLY);
    }


    public ResponseEntity<HttpStatus> DeleteCategory(long id) {
        categoryRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<HttpStatus> DeleteAllCategories() {
        categoryRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

