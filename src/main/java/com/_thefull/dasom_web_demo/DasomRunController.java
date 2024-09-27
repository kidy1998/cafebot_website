package com._thefull.dasom_web_demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class DasomRunController {

    @GetMapping
    public String redirectToMainPage(@RequestParam(value = "lang", required = false, defaultValue = "kor") String lang) {
    	
    	 if ("eng".equals(lang)) {
             // 영어 페이지 반환
    		 return "main/main_eng"; 
         } else {
             // 기본값으로 한국어 페이지 반환
        	 return "main/main"; 
         }
    	 
        
    }
}