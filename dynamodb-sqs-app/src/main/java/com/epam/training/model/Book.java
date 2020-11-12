package com.epam.training.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.io.Serializable;

@DynamoDbBean
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book implements Serializable {

    public static final String BOOK_TABLE = "book";
    private static final long serialVersionUID = -7353913496589403982L;

    private String title;
    private String description;
    private String isbn;

    @DynamoDbPartitionKey
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
