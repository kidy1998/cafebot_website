package com._thefull.dasom_web_demo.domain.user.controller;

import com._thefull.dasom_web_demo.domain.user.service.UserRegisterService;
import com._thefull.dasom_web_demo.domain.user.domain.dto.UserJoinRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserRegisterController {

	private final PasswordEncoder passwordEncoder;
    private final UserRegisterService userRegisterService;

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserJoinRequestDto requestDto,
                                BindingResult bindingResult,
                                /*@RequestParam("code") String code,
                                 @RequestParam("name") String name,
                                 @RequestParam("phoneNum") String phoneNum,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password,*/
                                 RedirectAttributes redirectAttributes){
 
      
//      // 비밀번호 암호화
//      PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//      String encodedPassword = passwordEncoder.encode(rawPassword);      
//      System.out.println("암호화된 비밀번호: " + encodedPassword);
    	
    	
    	requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
    	
        userRegisterService.registerUser(requestDto);

        redirectAttributes.addFlashAttribute("name",requestDto.getName());

        return "redirect:/page/user/registersuccess";
    }


}
