package com.example.demo4.repository;

import com.example.demo4.model.Book;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, Integer> {

}
