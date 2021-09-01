package com.gslab.pepper.model;

public class PlaintextMessage implements KafkaMessage {
    String headers;
    Object payload;

    public PlaintextMessage(String headers, Object payload) {
        this.headers = headers;
        this.payload = payload;
    }

    @Override
    public String getHeaders() {
        return headers;
    }

    @Override
    public void setHeaders(String headers) {
        this.headers = headers;
    }

    @Override
    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "PlaintextMessage{" +
                "headers='" + headers + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }
}
