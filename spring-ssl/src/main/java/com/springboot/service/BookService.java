package com.springboot.service;

import java.util.Optional;

import com.springboot.model.Book;

public interface BookService {

    Iterable<Book> findAll();

    Book create(Book book);

    Optional<Book> find(String isbn);
}
