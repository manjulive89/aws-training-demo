package com.epam.training.service;

import com.epam.training.model.Book;

public interface BookEventService {

    void createSaveEvent(Book book);

    void createUpdateEvent(Book book);

    void createDeleteEvent(String isbn);
}
