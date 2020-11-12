package com.epam.training.controller;

import com.epam.training.model.Book;
import com.epam.training.model.BookDto;
import com.epam.training.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public void createBook(@RequestBody Book book) {
        bookService.createBook(book);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> readAllBooks() {
        List<BookDto> foundBooks = bookService.getAllBooks().stream()
                .map(this::transformToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(foundBooks);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDto> readBookByIsbn(@PathVariable String isbn) {
        Optional<Book> foundBook = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(foundBook.map(this::transformToDto).orElse(null));
    }

    @PutMapping
    public void updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
    }

    @DeleteMapping("/{isbn}")
    public void deleteBookByIsbn(@PathVariable String isbn) {
        bookService.deleteBookByIsbn(isbn);
    }

    private BookDto transformToDto(Book book) {
        return new BookDto()
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setDescription(book.getDescription());
    }
}
