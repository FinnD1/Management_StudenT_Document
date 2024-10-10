package com.spring.psring_postman.exception;

public enum ErrorCode {
    USER_EXIST(1001,"User already exists"),

    USERNAME_INVALID(1002,"Username is invalid"),
    PASSWORD_INVALID(1003,"Password is invalid"),
    USER_NOT_EXIST(1004,"User not exists"),
    UNAUTHENTICATED(1005,"Unauthenticated"),
    ;
     ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
