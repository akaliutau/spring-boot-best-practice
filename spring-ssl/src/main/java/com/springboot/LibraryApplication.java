package com.springboot;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.springboot.model.Book;
import com.springboot.service.BookService;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    public ApplicationRunner booksInitializer(BookService bookService) {
        return args -> {
            bookService.create(new Book("0195153448", "Classical Mythology", "Mark P. O. Morford"));
            bookService.create(new Book("0345402871", "Airframe", "Michael Crichton"));
            bookService.create(new Book("0345417623", "Timeline", "Michael Crichton"));
        };
    }

}
