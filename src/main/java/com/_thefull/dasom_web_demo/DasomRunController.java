package com._thefull.dasom_web_demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DasomRunController {

    @GetMapping
    public String redirectToMainPage() {
        return "redirect:page/main";  // /page/main 경로로 리다이렉트
    }
}