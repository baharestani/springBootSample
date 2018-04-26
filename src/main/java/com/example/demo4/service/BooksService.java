package com.example.demo4.service;

import com.example.demo4.model.Book;
import com.example.demo4.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

    @Autowired
    private BookRepository repository;

    public Iterable<Book> getBooks(){

        return  repository.findAll();
    }
}

