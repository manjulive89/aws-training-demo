package com.epam.training.service;

import com.epam.training.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void createBook(Book book);

    List<Book> getAllBooks();

    Optional<Book> getBookByIsbn(String isbn);

    void updateBook(Book book);

    void deleteBookByIsbn(String isbn);
}
