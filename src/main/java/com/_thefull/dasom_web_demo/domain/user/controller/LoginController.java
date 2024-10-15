package com._thefull.dasom_web_demo.domain.user.controller;

import com._thefull.dasom_web_demo.domain.robot.domain.Robot;
import com._thefull.dasom_web_demo.domain.store.domain.Store;
import com._thefull.dasom_web_demo.domain.user.domain.dto.LoginRequestDto;
import com._thefull.dasom_web_demo.domain.user.domain.User;
import com._thefull.dasom_web_demo.domain.user.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class LoginController {

    private final LoginService loginService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDto requestDTO,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        RedirectAttributes redirectAttributes,
                        @RequestParam(value = "lang", required = false, defaultValue = "kor") String lang
                        ) throws IOException{

        // 로그인 서비스 호출 (비밀번호 검증 포함)
        User user = loginService.login(requestDTO);

        if (user != null) {
            // 비밀번호 비교
            if (passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
                // 로그인 성공
                Long storeId = user.getStoreList().get(0).getId();
                Long robotId = user.getStoreList().get(0).getRobotList().get(0).getId();

                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("storeId", storeId);
                session.setAttribute("robotId", robotId);
                System.out.println("Httpsession : " + session);
                session.setAttribute("userName", user.getName());

                if ("eng".equals(lang)) {
                    // 영어 페이지 반환
                	 return "redirect:/page/main?lang=" + lang;
                } else {
                    // 기본값으로 한국어 페이지 반환
                	 return "redirect:/page/main";
                }
               
            } else {
            	
                // 비밀번호 불일치
                return "redirect:/page/user/login?lang=" + lang + "&error=password_incorrect";
            }
        } else {
            // 사용자 없음
        	return "redirect:/page/user/login?lang=" + lang + "&error=password_incorrect";
        }
    }
    

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
    		@RequestParam(value = "lang", required = false, defaultValue = "kor") String lang) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return "redirect:/page/main?lang="+lang;
    }
    
    public HttpSession getHttpSession() {
    	
		return null;
    }

}
