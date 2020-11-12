package com.epam.training.repository.impl;

import com.epam.training.model.BookEvent;
import com.epam.training.repository.BookEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.UUID;

@Repository
@AllArgsConstructor
public class BookEventRepositoryImpl implements BookEventRepository {

    private static final String BOOK_EVENT_QUEUE = "book-event.fifo";

    private final SqsClient client;
    private final ObjectMapper objectMapper;

    @Override
    public void save(BookEvent event) throws JsonProcessingException {
        SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                .queueUrl(getQueueUrl())
                .messageGroupId(event.getIsbn())
                .messageDeduplicationId(UUID.randomUUID().toString())
                .messageBody(objectMapper.writeValueAsString(event))
                .build();
        client.sendMessage(sendMsgRequest);
    }

    private String getQueueUrl() {
        GetQueueUrlRequest getQueueRequest = GetQueueUrlRequest.builder()
                .queueName(BOOK_EVENT_QUEUE)
                .build();
        return client.getQueueUrl(getQueueRequest).queueUrl();
    }
}
