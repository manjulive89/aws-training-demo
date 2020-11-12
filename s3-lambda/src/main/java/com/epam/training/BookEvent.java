package com.epam.training;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookEvent {

    private BookEventType type;
    private String timestamp;
    private String isbn;
    private Book book;
}
