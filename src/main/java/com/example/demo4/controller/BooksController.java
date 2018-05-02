package com.example.demo4.controller;

import com.example.demo4.model.Book;
import com.example.demo4.model.NotFoundException;
import com.example.demo4.service.BooksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController("/books")
public class BooksController {

	private final BooksService booksService;

	public BooksController(BooksService booksService) {
		this.booksService = booksService;
	}

	@GetMapping
	public List<Book> getAll() {

		return this.booksService.getBooks();
	}

	@GetMapping("/books/{id}")
	public Book get(@PathVariable String id) throws NotFoundException {
		return this.booksService.getBook(Integer.parseInt(id));
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Book book, UriComponentsBuilder builder) {
		Book createdBook = booksService.createBook(book);

		URI uri = builder.path("/books/{id}").buildAndExpand(createdBook.Id).toUri();

		return ResponseEntity.created(uri).build();
	}


}
