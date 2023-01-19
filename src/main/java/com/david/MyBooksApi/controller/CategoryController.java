package com.david.MyBooksApi.controller;

import com.david.MyBooksApi.dto.request.CategoryRequest;
import com.david.MyBooksApi.dto.response.Response;
import com.david.MyBooksApi.model.Category;
import com.david.MyBooksApi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")

public class CategoryController {


    private final CategoryService categoryService;


    @PostMapping("/categories")
    public ResponseEntity<Response<Category>> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<>(categoryService.getCreateCategory(categoryRequest), HttpStatus.CREATED);
    }



    @GetMapping("/categories")
    public ResponseEntity<Response<List<Category>>> getAllCategories(@RequestParam(required = false) String title) {
        return new ResponseEntity<>(categoryService.getAllCategories(title), HttpStatus.OK);
    }


    @GetMapping("/categories/{id}")
    public ResponseEntity<Response<Category>> getCategoryById(@PathVariable("id") long id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }



    @PutMapping("/categories/{id}")
    public ResponseEntity<Response<Category>> updateCategory(@PathVariable("id") long id, @RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<>(categoryService.UpdateCategory(id,categoryRequest), HttpStatus.OK);
    }


    @DeleteMapping("/categories/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") long id) {
        return categoryService.DeleteCategory(id);
    }


    @DeleteMapping("/categories")
    public ResponseEntity<HttpStatus> deleteAllCategories() {
        return categoryService.DeleteAllCategories();
    }


}
