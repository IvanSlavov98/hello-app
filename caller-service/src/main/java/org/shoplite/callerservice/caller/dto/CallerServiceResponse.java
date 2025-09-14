package org.shoplite.callerservice.caller.dto;

public record CallerServiceResponse(String message) {
    public CallerServiceResponse(String message) {
        this.message = message;
    }
}
