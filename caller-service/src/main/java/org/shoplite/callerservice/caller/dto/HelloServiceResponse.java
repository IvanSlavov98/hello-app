package org.shoplite.callerservice.caller.dto;

public class HelloServiceResponse {
    private String message;

    public HelloServiceResponse() {}

    public HelloServiceResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
