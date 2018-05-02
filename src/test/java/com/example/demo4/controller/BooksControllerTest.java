package com.example.demo4.controller;

import com.example.demo4.model.Book;
import com.example.demo4.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BooksControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private BookRepository bookRepository;

	@Test
	public void getAllReturnsAll() throws Exception {
		when(bookRepository.findAll()).thenReturn(Arrays.asList(new Book[]{
				new Book(1, "Title")
		}));

		mvc.perform(get("/books"))
				.andExpect(status().isOk())
				.andExpect(content().json("[{\"Id\":1,\"Title\":\"Title\"}]"));
	}

	@Test
	public void getReturnsExistingBook() throws Exception {
		int id = 1;
		Book book = new Book(id, "Title");
		when(bookRepository.findById(id)).thenReturn(Optional.of(book));

		mvc.perform(get("/books/1"))
				.andExpect(status().isOk())
				.andExpect(content().json("{\"Id\":1,\"Title\":\"Title\"}"));
	}

	@Test
	public void getWhenBookDoesNotExistReturns404() throws Exception {
		when(bookRepository.findById(1)).thenReturn(Optional.empty());


		mvc.perform(get("/books/1"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void createAddsNewBook() throws Exception {
		Book book = new Book(1, "Title");
		when(bookRepository.save(any(Book.class))).thenReturn(book);

		mvc.perform(post("/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"Id\":1,\"Title\":\"Title\"}"))
				.andExpect(status().isCreated())
				.andExpect(header().string("location", containsString("books/1")));
	}
}