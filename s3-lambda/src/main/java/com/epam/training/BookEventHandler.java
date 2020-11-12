package com.epam.training;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.epam.training.BookEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.util.UUID;

public class BookEventHandler implements RequestHandler<SQSEvent, Void> {

    private static final String BOOK_EVENT_BUCKET = "book-bucket-demo";

    private final S3Client s3Client = S3Client.builder()
            .credentialsProvider(DefaultCredentialsProvider.create())
            .region(Region.US_EAST_2)
            .build();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public Void handleRequest(SQSEvent event, Context context) {
        for (SQSEvent.SQSMessage msg : event.getRecords()) {
            try {
                context.getLogger().log("Event: " + msg.getBody());
                s3Client.putObject(PutObjectRequest.builder()
                                .bucket(BOOK_EVENT_BUCKET)
                                .key(formFileName(msg.getBody()))
                                .build(),
                        RequestBody.fromString(msg.getBody()));
            } catch (S3Exception | JsonProcessingException e) {
                context.getLogger().log(event + " event has not been saved to S3: " + e.getMessage());
                throw e;
            }
        }
        return null;
    }

    private String formFileName(String eventAsString) throws JsonProcessingException {
        BookEvent event = objectMapper.readValue(eventAsString, BookEvent.class);
        return event.getIsbn() + "/" + event.getTimestamp() + "-" + UUID.randomUUID().toString() + ".json";
    }
}
