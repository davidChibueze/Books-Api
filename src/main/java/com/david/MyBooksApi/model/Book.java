package com.david.MyBooksApi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;



@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title",nullable = false)
    //    @NotBlank(message = "name cannot be empty")
    private String title;

    @Column(name = "author",nullable = false)
    private String author;

    @Column(name = "publishedOn",nullable = false)
    private String publishedOn;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "createdDate",nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updatedBy")
    private String updatedBy;

    @Column(name = "updatedDate", nullable = false)
    private LocalDateTime updatedDate;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "books")
    @JsonIgnore
    private Set<Category> categories = new HashSet<>();



}

