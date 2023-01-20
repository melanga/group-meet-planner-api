package com.example.group_meet_planner.util;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
    private Object body;
    private String errorMessage;

    private ResponseEntityBuilder() {
    }

    public static ResponseEntityBuilder builder() {
        return new ResponseEntityBuilder();
    }

    public ResponseEntityBuilder errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public ResponseEntityBuilder body(Object body) {
        this.body = body;
        return this;
    }

    public ResponseEntity<Object> build() {
        return body != null ? ResponseEntity.ok(body) : ResponseEntity.badRequest().body(errorMessage);
    }
}
