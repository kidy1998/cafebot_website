package com._thefull.dasom_web_demo.domain.webSoket;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.dto.MenuPromotionRequestDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;

@RestController
@RequestMapping("/api/websocket")
public class WebSocketController {
	
 private final RobotWebSocketHandler webSocketHandler;

    public WebSocketController(RobotWebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessageToUser(HttpServletRequest request, @ModelAttribute MenuPromotionRequestDTO requestDTO
    		) {
        HttpSession httpSession = request.getSession(false);
        
        System.out.println("WebSocketController 에서의 httpSession : " + httpSession);
        
        if (httpSession != null) {
            String robotId =  httpSession.getAttribute("robotId").toString();
            if (robotId != null) {
                try {
                    webSocketHandler.sendMessageToUser(robotId, requestDTO.getMent());
                    return ResponseEntity.ok("메시지 전송 성공");
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("메시지 전송 실패");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용자 세션이 유효하지 않습니다.");
    }
    
}

	
