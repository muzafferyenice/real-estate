package com.realestate.exception.message;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

//bu class i neden yazdik : olusan exception lari throw etigimiz zaman clint a 
//gonderilecek message icin custom bir error classyazdik
public class ApiResponseError {
    
    private HttpStatus status;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy hh:mm:ss" )
    private LocalDateTime timestamp;
    private String message;
    private String requestURI;
    
    private ApiResponseError() {//private cons bu
        timestamp=LocalDateTime.now();
    } 
    
    
    public ApiResponseError(HttpStatus status) {
        this();//18 deki cons u cagirir
        this.status=status;
    }
    
    public ApiResponseError(HttpStatus status,String message, String requestURI) {
        this(status);//23 deki cons u cagirir
        this.message=message;
        this.requestURI=requestURI;
    }
    public HttpStatus getStatus() {
        return status;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getRequestURI() {
        return requestURI;
    }
    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    
    
}