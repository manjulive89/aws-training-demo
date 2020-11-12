package com.epam.training.service.impl;

import com.epam.training.model.Book;
import com.epam.training.model.BookEvent;
import com.epam.training.repository.BookEventRepository;
import com.epam.training.service.BookEventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static com.epam.training.model.BookEventType.*;

@Service
@AllArgsConstructor
public class BookEventServiceImpl implements BookEventService {

    private static final Logger LOGGER = Logger.getLogger(BookEventServiceImpl.class);

    private final BookEventRepository repository;

    @Override
    public void createSaveEvent(Book book) {
        try {
            repository.save(new BookEvent(CREATE, getTimestamp(), book.getIsbn(), book));
        } catch (JsonProcessingException e) {
            LOGGER.warn("CREATE BookEvent has not been added to an Amazon SQS queue !", e);
        }
    }

    @Override
    public void createUpdateEvent(Book book) {
        try {
            repository.save(new BookEvent(UPDATE, getTimestamp(), book.getIsbn(), book));
        } catch (JsonProcessingException e) {
            LOGGER.warn("UPDATE BookEvent has not been added to an Amazon SQS queue !", e);
        }
    }

    @Override
    public void createDeleteEvent(String isbn) {
        try {
            repository.save(new BookEvent(DELETE, getTimestamp(), isbn, null));
        } catch (JsonProcessingException e) {
            LOGGER.warn("DELETE BookEvent has not been added to an Amazon SQS queue !", e);
        }
    }

    private LocalDateTime getTimestamp() {
        return ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime();
    }
}
