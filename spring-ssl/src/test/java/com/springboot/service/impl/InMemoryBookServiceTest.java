package com.springboot.service.impl;

import org.junit.Before;
import org.junit.Test;

import com.springboot.model.Book;
import com.springboot.service.impl.InMemoryBookService;
import com.springboot.utils.TestDataUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryBookServiceTest {

	private InMemoryBookService service;

	@Before
	public void setup() {
		this.service = new InMemoryBookService();
	}

	@Test
	public void shouldReturnEmptyOptionalWhenNotFound() {
		assertThat(service.find("1234")).isEmpty();
	}

	@Test
	public void shouldFindAfterCreation() {
		assertThat(service.findAll()).isEmpty();

		Book book = new Book("1234", "Book1", "John Smith", "Joe Doe");
		assertThat(service.create(book)).isEqualTo(book);
		assertThat(service.find(book.getIsbn())).contains(book);

	}

	@Test
	public void shouldReturnAllBooksAfterCreation() {
		assertThat(service.findAll()).isEmpty();

        Book book1 = TestDataUtils.getSampleRecord(0);
        Book book2 = TestDataUtils.getSampleRecord(1);
		service.create(book1);
		service.create(book2);

		assertThat(service.findAll()).containsExactly(book1, book2);

	}

}
