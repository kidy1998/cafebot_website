package com._thefull.dasom_web_demo.domain.user.controller;

import com._thefull.dasom_web_demo.domain.robot.domain.Robot;
import com._thefull.dasom_web_demo.domain.store.domain.Store;
import com._thefull.dasom_web_demo.domain.user.domain.dto.LoginRequestDto;
import com._thefull.dasom_web_demo.domain.user.domain.User;
import com._thefull.dasom_web_demo.domain.user.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDto requestDTO,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes){

        User user = loginService.login(requestDTO);

        Long storeId = user.getStoreList().get(0).getId();
        Long robotId = user.getStoreList().get(0).getRobotList().get(0).getId();


        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user);
            session.setAttribute("storeId", storeId);
            session.setAttribute("robotId", robotId);
            session.setAttribute("userName", user.getName());
            
            return "redirect:/page/main";
        }
        else{
            redirectAttributes.addFlashAttribute("error", "전화번호 또는 비밀번호가 일치하지 않습니다.");
            return "redirect:/page/user/login";
        }
    }
}
