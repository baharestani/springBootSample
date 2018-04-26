package com.example.demo4.controller;

import com.example.demo4.model.Book;
import com.example.demo4.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping(path = "/books")
    public Iterable<Book> get() {

        return this.booksService.getBooks();
    }

}
