package com.deepanshu.dairydriveapi.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName, String fieldName, String value) {
        super(String.format("No %s found with %s = %s.", resourceName, fieldName, value));
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
