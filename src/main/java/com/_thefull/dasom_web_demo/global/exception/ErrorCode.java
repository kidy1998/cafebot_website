package com._thefull.dasom_web_demo.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter @AllArgsConstructor
public enum ErrorCode {
    INVALID_STATE(HttpStatus.NOT_FOUND),
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED),
    UNMATCHED_TIME_STATUS(HttpStatus.CONFLICT),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND),
    NOT_FOUND_PASSWORD(HttpStatus.NOT_FOUND),
    NOT_FOUND_ROBOT(HttpStatus.NOT_FOUND),
    ALREADY_SET(HttpStatus.ALREADY_REPORTED),
    NO_CATEGORY(HttpStatus.NOT_FOUND),
    NOT_FOUND_DASOM_LOCATION(HttpStatus.NOT_FOUND),
    NO_DISC_TYPE(HttpStatus.NOT_FOUND),
    REQUEST_FORBIDDEN(HttpStatus.FORBIDDEN),
    NO_DATE_TYPE(HttpStatus.NOT_FOUND),
    INVALID_DAY(HttpStatus.CONFLICT),
    NOT_FOUND_MENU(HttpStatus.NOT_FOUND),
    NOT_FOUND_MENU_PROMOTION(HttpStatus.NOT_FOUND),
    NO_ROBOT_IN_STORE(HttpStatus.NOT_FOUND),
    NOT_FOUND_STORE(HttpStatus.NOT_FOUND);


    private HttpStatus httpStatus;
}
