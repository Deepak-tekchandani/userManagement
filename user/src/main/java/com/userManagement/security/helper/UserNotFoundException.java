package com.userManagement.security.helper;

public class UserNotFoundException extends Exception{

    public UserNotFoundException() {
        super("User with this Id is not there in DB !! try again !!");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
