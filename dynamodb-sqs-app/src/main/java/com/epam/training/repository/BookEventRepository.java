package com.epam.training.repository;

import com.epam.training.model.BookEvent;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface BookEventRepository {

    void save(BookEvent event) throws JsonProcessingException;
}
