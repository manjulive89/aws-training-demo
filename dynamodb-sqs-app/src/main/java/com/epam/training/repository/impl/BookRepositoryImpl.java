package com.epam.training.repository.impl;

import com.epam.training.model.Book;
import com.epam.training.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.epam.training.model.Book.BOOK_TABLE;

@Repository
@AllArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final DynamoDbEnhancedClient client;

    @Override
    public void upsert(Book book) {
        getTable().putItem(book);
    }

    @Override
    public List<Book> findAll() {
        return getTable().scan().items()
                .stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(getTable().getItem(getKey(isbn)));
    }

    @Override
    public void deleteByIsbn(String isbn) {
        getTable().deleteItem(getKey(isbn));
    }

    private DynamoDbTable<Book> getTable() {
        return client.table(BOOK_TABLE, TableSchema.fromBean(Book.class));
    }

    private Key getKey(String isbn) {
        return Key.builder().partitionValue(isbn).build();
    }
}
