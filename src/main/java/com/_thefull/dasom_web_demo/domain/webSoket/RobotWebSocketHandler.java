package com._thefull.dasom_web_demo.domain.webSoket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RobotWebSocketHandler extends TextWebSocketHandler {
	
	private final ObjectMapper objectMapper = new ObjectMapper();  // Jackson ObjectMapper 인스턴스 생성
	
    // 각 사용자별로 웹소켓 세션을 관리할 맵 (예: 사용자 ID -> WebSocketSession)
    private Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("웹소켓 세션 : " + session);

        // URI에서 쿼리 파라미터 추출
        String query = session.getUri().getQuery();
        Map<String, String> queryParams = new HashMap<>();
        
        // 쿼리 파라미터 파싱
        if (query != null) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    queryParams.put(keyValue[0], keyValue[1]);
                }
            }
        }

        // robotId를 쿼리 파라미터에서 추출
        String robotId = queryParams.get("robotId");

        // robotId가 존재할 경우 세션을 저장
        if (robotId != null) {
        	session.getAttributes().put("robotId", robotId);  // robotId를 WebSocketSession에 저장
            userSessions.put(robotId, session);  // WebSocket 세션 저장
            System.out.println("사용자 " + robotId + "의 웹소켓 세션이 연결되었습니다.");
            System.out.println("userSession : " + userSessions.toString());
            
        } else {
            System.out.println("robotId가 제공되지 않았습니다.");
            session.close();  // robotId가 없을 경우 세션을 닫을 수도 있습니다.
        }
    }

      
    

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    	
    	System.out.println("session 해제 : " + session);
         
        // WebSocketSession에서 robotId 가져오기
        String robotId = (String) session.getAttributes().get("robotId");
        
        if (robotId != null) {
            userSessions.remove(robotId);  // userSessions에서 세션 제거
            System.out.println("사용자 " + robotId + "의 웹소켓 세션이 종료되었습니다.");
        } else {
            System.out.println("robotId가 세션에서 조회되지 않았습니다.");
        }
    }
    
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트로부터 받은 메시지 출력
        String payload = message.getPayload();
        System.out.println("수신된 메시지: " + payload);

        // WebSocketSession의 attributes에서 HttpSession을 가져옴
        HttpSession httpSession = (HttpSession) session.getAttributes().get("HTTP_SESSION");
        
        if (httpSession != null) {
            // HttpSession에서 robotId를 가져옴
            String robotId = (String) httpSession.getAttribute("robotId");
            if (robotId != null) {
                System.out.println("사용자 " + robotId + "으로부터 메시지: " + payload);
            }
        }

        // 받은 메시지에 응답 메시지 전송 (예: 에코 메시지)
        session.sendMessage(new TextMessage("서버로부터의 응답: " + payload));
    }

    
    

    // 특정 사용자에게 메시지 전송
    public void sendMessageToUser(String robotId, String message) throws Exception {
        WebSocketSession session = userSessions.get(robotId);
        System.out.println("메시지 전송 시 session : " + session.toString());

        if (session != null && session.isOpen()) {
            // 메시지를 JSON 형식으로 변환
            String jsonMessage = createJsonMessage("promotionMent", message);

            // JSON 형식의 메시지를 WebSocketSession으로 전송
            session.sendMessage(new TextMessage(jsonMessage));
        } else {
            System.out.println("사용자 " + robotId + "의 웹소켓 세션이 존재하지 않거나 종료되었습니다.");
        }
    }
    
    
    // JSON 형식으로 메시지를 생성하는 메서드
    private String createJsonMessage(String key, String value) throws Exception {
        // key-value 형식으로 Map을 만들어서 JSON으로 변환
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put(key, value);

        // ObjectMapper를 사용해 Map을 JSON 문자열로 변환
        return objectMapper.writeValueAsString(jsonMap);
    }
    
    
    
    
    
    
}
