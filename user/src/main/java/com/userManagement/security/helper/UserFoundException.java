package com.userManagement.security.helper;

public class UserFoundException extends Exception{

    public UserFoundException() {
        super("User with this username is already there in DB !! try with another Username !!");
    }

    public UserFoundException(String message) {
        super(message);
    }
}
