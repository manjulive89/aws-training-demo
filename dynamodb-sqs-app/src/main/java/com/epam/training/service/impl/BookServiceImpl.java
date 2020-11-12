package com.epam.training.service.impl;

import com.epam.training.model.Book;
import com.epam.training.repository.BookRepository;
import com.epam.training.service.BookEventService;
import com.epam.training.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookEventService bookEventService;

    @Override
    public void createBook(Book book) {
        bookRepository.upsert(book);
        bookEventService.createSaveEvent(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.upsert(book);
        bookEventService.createUpdateEvent(book);
    }

    @Override
    public void deleteBookByIsbn(String isbn) {
        bookRepository.deleteByIsbn(isbn);
        bookEventService.createDeleteEvent(isbn);
    }
}
