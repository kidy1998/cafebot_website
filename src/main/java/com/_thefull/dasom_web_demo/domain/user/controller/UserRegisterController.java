package com._thefull.dasom_web_demo.domain.user.controller;

import com._thefull.dasom_web_demo.domain.user.service.UserRegisterService;
import com._thefull.dasom_web_demo.domain.user.domain.dto.UserJoinRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserRegisterController {

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


        userRegisterService.registerUser(requestDto);

        redirectAttributes.addFlashAttribute("name",requestDto.getName());

        return "redirect:/page/user/registersuccess";
    }


}
