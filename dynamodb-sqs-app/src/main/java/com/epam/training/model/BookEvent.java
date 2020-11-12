package com.epam.training.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookEvent {

    private BookEventType type;
    private LocalDateTime timestamp;
    private String isbn;
    private Book book;
}
