package com.myccnice.practice.manual.common.exception;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -6948028648634556323L;

    public CustomException() {

    }

    public CustomException(String string) {
        super(string);
    }

    public CustomException(Throwable arg0) {
        super(arg0);
    }

    public CustomException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
}
