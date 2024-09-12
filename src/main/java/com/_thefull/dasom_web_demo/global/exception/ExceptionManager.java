package com._thefull.dasom_web_demo.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionManager {

    @ExceptionHandler(AppException.class)
    public String handleAppException(AppException e, Model model){
        model.addAttribute("httpStatus",e.getErrorCode().getHttpStatus());
        model.addAttribute("errorMessage",e.getMessage());
        model.addAttribute("errorCode", e.getErrorCode().name());

        return "/pages/error";
    }
}
