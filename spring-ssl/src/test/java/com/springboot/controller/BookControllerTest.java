package com.springboot.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.springboot.controller.BookController;
import com.springboot.model.Book;
import com.springboot.service.BookService;
import com.springboot.utils.TestDataUtils;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void shouldReturnListOfBooks() throws Exception {
        
        Book book1 = TestDataUtils.getSampleRecord(0);
        Book book2 = TestDataUtils.getSampleRecord(1);

        when(bookService.findAll()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/books"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[*].isbn", Matchers.containsInAnyOrder("1234", "5678")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].title", Matchers.containsInAnyOrder("Book1", "Book2")));
    }

    @Test
    public void shouldReturn404WhenBookNotFound() throws Exception {

        when(bookService.find(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(get("/books/123")).andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnBookWhenFound() throws Exception {
        
        Book book1 = TestDataUtils.getSampleRecord(0);

        when(bookService.find(anyString())).thenReturn(Optional.of(book1));

        mockMvc.perform(get("/books/1234"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.isbn", Matchers.equalTo("1234")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("Book1")));
    }

    @Test
    public void shouldAddBook() throws Exception {
        
        when(bookService.create(any(Book.class))).thenReturn(new Book("123456789", "Test Book Stored", "Author"));

        mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
                .content("{ \"isbn\" : \"123456789\", \"title\" : \"Test Book\", \"authors\" : [\"Author\"]}"))
                .andExpect(status().isCreated()).andExpect(header().string("Location", Matchers.allOf(Matchers.containsString("/books/123456789"))));
    }

}
