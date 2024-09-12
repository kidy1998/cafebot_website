package com._thefull.dasom_web_demo.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class AppException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;
}
