package com.example.cdwebbe.payload;

import org.springframework.http.HttpStatus;

public class Response {
    private HttpStatus statusCode;
    private String message;
    private Object data;

    /**
     * getter/setter
     * @return
     */
    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
