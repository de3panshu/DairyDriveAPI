package com.deepanshu.dairydriveapi.exceptions;

public class LoginAttemptFailedException extends RuntimeException{
    public LoginAttemptFailedException(){
        super("Invalid Credentials");
    }
    public LoginAttemptFailedException(String message){
        super(message);
    }
}
