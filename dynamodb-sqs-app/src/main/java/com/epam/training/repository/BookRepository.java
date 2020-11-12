package com.epam.training.repository;

import com.epam.training.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    void upsert(Book book);

    List<Book> findAll();

    Optional<Book> findByIsbn(String isbn);

    void deleteByIsbn(String isbn);
}
