package com.epam.training.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.sqs.model.SqsException;

@ControllerAdvice
public class ExceptionController {

    private static final Logger LOGGER = Logger.getLogger(ExceptionController.class);

    @ExceptionHandler(value = DynamoDbException.class)
    public ResponseEntity<Object> dynamoDbException(DynamoDbException exception) {
        LOGGER.warn(exception);
        return new ResponseEntity<>("DynamoDb issue has been occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = SqsException.class)
    public ResponseEntity<Object> sqsException(SqsException exception) {
        LOGGER.warn(exception);
        return new ResponseEntity<>("SQS issue has been occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
