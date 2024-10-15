//package com._thefull.dasom_web_demo.domain.webSoket;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import jakarta.servlet.http.HttpSession;
//import lombok.Data;
//
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.stream.Collectors;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.HashSet;
//
//@Component
//public class RobotWebSocketHandler extends TextWebSocketHandler {
//
//    // 각 사용자별로 웹소켓 세션을 관리할 맵 (예: 사용자 ID -> WebSocketSession)
//    private Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//    	
//    	System.out.println("웹소켓 세션 : " + session);
//    	
//        // HttpSession을 통해 로그인된 사용자 ID를 가져옵니다 (로그인 세션과 연계)
//        HttpSession httpSession = (HttpSession) session.getAttributes().get("HTTP_SESSION");
//        System.out.println("조회한 로그인 세션 : " + httpSession);
//        
//        if (httpSession != null) {
//            String robotId = (String) httpSession.getAttribute("robotId");  // 로봇 id
//            System.out.println("웹소켓에서 robotId : " + robotId);
//            
//            if (robotId != null) {
//                // 사용자의 웹소켓 세션을 저장
//                userSessions.put(robotId, session);
//                System.out.println("사용자 " + robotId + "의 웹소켓 세션이 연결되었습니다.");
//            }
//        }
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//    	
//    	System.out.println("session 해제 : " + session);
//        // 세션이 종료될 때 userSessions에서 해당 세션을 제거
//        HttpSession httpSession = (HttpSession) session.getAttributes().get("HTTP_SESSION");
//        if (httpSession != null) {
//            String robotId = (String) httpSession.getAttribute("robotId");
//            if (robotId != null) {
//                userSessions.remove(robotId);
//                System.out.println("사용자 " + robotId + "의 웹소켓 세션이 종료되었습니다.");
//            }
//        }
//    }
//    
//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        // 클라이언트로부터 받은 메시지 출력
//        String payload = message.getPayload();
//        System.out.println("수신된 메시지: " + payload);
//
//        // WebSocketSession의 attributes에서 HttpSession을 가져옴
//        HttpSession httpSession = (HttpSession) session.getAttributes().get("HTTP_SESSION");
//        
//        if (httpSession != null) {
//            // HttpSession에서 robotId를 가져옴
//            String robotId = (String) httpSession.getAttribute("robotId");
//            if (robotId != null) {
//                System.out.println("사용자 " + robotId + "으로부터 메시지: " + payload);
//            }
//        }
//
//        // 받은 메시지에 응답 메시지 전송 (예: 에코 메시지)
//        session.sendMessage(new TextMessage("서버로부터의 응답: " + payload));
//    }
//
//    
//    
//
//    // 특정 사용자에게 메시지 전송
//    public void sendMessageToUser(String robotId, String message) throws Exception {
//        WebSocketSession session = userSessions.get(robotId);
//        
//        System.out.println("메시지 전송 시 session : " + session);
//        if (session != null && session.isOpen()) {
//            session.sendMessage(new TextMessage(message));
//        } else {
//            System.out.println("사용자 " + robotId + "의 웹소켓 세션이 존재하지 않거나 종료되었습니다.");
//        }
//    }
//}
