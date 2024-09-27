package com._thefull.dasom_web_demo.domain.Local.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page/main")
public class MainController {

    @GetMapping
    public String getMainPage(@RequestParam(value = "lang", required = false, defaultValue = "kor") String lang){
    	
    	 if ("eng".equals(lang)) {
             // 영어 페이지 반환
    		 return "main/main_eng"; 
         } else {
             // 기본값으로 한국어 페이지 반환
        	 return "main/main"; 
         }

    }
}
