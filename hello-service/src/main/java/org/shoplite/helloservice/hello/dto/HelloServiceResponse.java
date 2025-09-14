package org.shoplite.helloservice.hello.dto;

public record HelloServiceResponse(String message){
    public HelloServiceResponse(String message){
        this.message = message;
    }
}
