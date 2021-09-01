package com.gslab.pepper.model;

public interface KafkaMessage {
    String getHeaders();

    void setHeaders(String headers);

    Object getPayload();

    @Override
    String toString();
}
