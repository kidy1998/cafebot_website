package com._thefull.dasom_web_demo.domain.user.controller;

import com._thefull.dasom_web_demo.domain.dasomLocation.domain.DasomLocation;
import com._thefull.dasom_web_demo.domain.dasomLocation.service.DasomLocationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page/user")
public class UserRegisterPageController {
    private final DasomLocationService dasomLocationService;

    @GetMapping("/register")
    public String getRegisterPage(@RequestParam(value = "lang", required = false, defaultValue = "kor") String lang){
        
        if ("eng".equals(lang)) {
            // 영어 페이지 반환
        	return "/user/register_eng";
        } else {
            // 기본값으로 한국어 페이지 반환
        	return "/user/register";
        }
    }

    @GetMapping("/registersuccess")
    public String getRegisterSuccessPage(@RequestParam(value = "lang", required = false, defaultValue = "kor") String lang,
    		Model model){
    	
    	 if ("eng".equals(lang)) {
    		 model.addAttribute("eng", lang);
    		 return "/user/registersuccess_eng";
         } else {
        	 model.addAttribute("kor", lang);
        	 return "/user/registersuccess";
         }
    }

    @GetMapping("/index")
    public String indexpage(){
        return "../static/index";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "lang", required = false, defaultValue = "kor") String lang){
    	
    	 if ("eng".equals(lang)) {
             // 영어 페이지 반환
    		 return "/user/login_eng";
         } else {
             // 기본값으로 한국어 페이지 반환
        	 return "/user/login";
         }
    	 
    }

    @GetMapping("/promotion")
    public String productPage(@RequestParam(value = "lang", required = false, defaultValue = "kor") String lang){
    	
    	 if ("eng".equals(lang)) {
             // 영어 페이지 반환
    		 return "/promotion/main_eng";
         } else {
             // 기본값으로 한국어 페이지 반환
        	 return "/promotion/main";
         }
    	 
    }

    @GetMapping("/resetpassword")
    public String resetpasswordPage(@RequestParam(value = "lang", required = false, defaultValue = "kor") String lang){
    	
    	 if ("eng".equals(lang)) {
             // 영어 페이지 반환
    		 return "/user/resetpassword_eng";
         } else {
             // 기본값으로 한국어 페이지 반환
        	 return "/user/resetpassword";
         }
    	 
    }



}
