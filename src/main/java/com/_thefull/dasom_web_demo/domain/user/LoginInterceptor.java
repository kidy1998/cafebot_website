package com._thefull.dasom_web_demo.domain.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("userId") == null) {
            // 로그인 페이지로 리다이렉트
            response.sendRedirect("/page/user/login?message=login_required");
            return false; // 요청을 처리하지 않고 여기서 종료
        }
        
        return true; // 요청 계속 진행
    }
}
