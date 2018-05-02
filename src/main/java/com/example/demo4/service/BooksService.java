package com.example.demo4.service;

import com.example.demo4.model.Book;
import com.example.demo4.model.NotFoundException;
import com.example.demo4.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BooksService {


	private BookRepository repository;

	public BooksService(BookRepository repository) {
		this.repository = repository;
	}

	public List<Book> getBooks() {

		return repository.findAll();
	}

	public Book getBook(Integer id) throws NotFoundException {
		return repository.findById(id).orElseThrow(() -> new NotFoundException());
	}

	public Book createBook(Book book) {
		return repository.save(book);
	}
}

