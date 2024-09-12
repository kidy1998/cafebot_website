package com._thefull.dasom_web_demo.domain.Local.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page/main")
public class MainController {

    @GetMapping
    public String getMainPage(){
        return "main/main";
    }
}
