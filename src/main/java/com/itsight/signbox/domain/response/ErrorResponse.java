package com.itsight.signbox.domain.response;

public class ErrorResponse {

    private String message;
    private String code;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

}
