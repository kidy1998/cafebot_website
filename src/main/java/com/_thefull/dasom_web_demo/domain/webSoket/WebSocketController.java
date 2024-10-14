package com._thefull.dasom_web_demo.domain.webSoket;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

import com._thefull.dasom_web_demo.domain.promotion.menuPromotions.domain.dto.MenuPromotionRequestDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;

@RestController
@RequestMapping("/api/websocket")
public class WebSocketController {
	
	private volatile WebSocketSession globalSession;

    private final RobotWebSocketHandler webSocketHandler;

    public WebSocketController(RobotWebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    // 특정 클라이언트로 메시지 전송하는 REST API
    @PostMapping("/send")
    public void sendMessageToClient(@ModelAttribute MenuPromotionRequestDTO requestDTO,
    		  HttpServletRequest request) throws Exception {
    	
    	HttpSession session = request.getSession(false);
    	Long robotId = (Long)session.getAttribute("robotId");
    	Long storeId = (Long)session.getAttribute("storeId");
    	
    	System.out.println("로봇id : " + robotId);
    	
        webSocketHandler.sendMessageToClient(storeId.toString(), requestDTO.getMent());
        
    }
}

	
