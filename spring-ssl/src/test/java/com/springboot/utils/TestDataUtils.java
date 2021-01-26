package com.springboot.utils;

import com.springboot.model.Book;

public class TestDataUtils {
    
    private static final Book[] books = new Book[] {
            new Book("1234", "Book1", "John Smith", "Joe Doe"),
            new Book("5678", "Book2", "Joe Doe")
    };
    
    public static Book getSampleRecord(int num) {
        return books[num];
    }
}
