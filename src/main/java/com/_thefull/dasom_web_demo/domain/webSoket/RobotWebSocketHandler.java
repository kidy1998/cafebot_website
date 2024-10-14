package com._thefull.dasom_web_demo.domain.webSoket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class RobotWebSocketHandler extends TextWebSocketHandler {

    // JSON 변환을 위한 ObjectMapper
    private ObjectMapper objectMapper = new ObjectMapper();

    // 전역 변수로 웹소켓 세션을 저장
    private volatile WebSocketSession globalSession;

    // 클라이언트 세션을 저장할 Map: robotId -> WebSocketSession
    private Map<String, WebSocketSession> clients = new ConcurrentHashMap<>();

    // storeId로 그룹별 클라이언트를 관리할 Map: storeId -> robotId List
    private Map<String, Set<String>> storeGroups = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 쿼리 파라미터에서 robotId와 storeId를 추출
        String query = session.getUri().getQuery();  // 예시: "robotId=1&storeId=1"
        Map<String, String> params = Arrays.stream(query.split("&"))
                                           .map(param -> param.split("="))
                                           .collect(Collectors.toMap(pair -> pair[0], pair -> pair[1]));

        System.out.println("params: " + params.toString());  // 쿼리 문자열 확인

        String robotId = params.get("robotId");
        String storeId = params.get("storeId");

        // 세션을 전역 변수에 저장
        globalSession = session;

        // 세션을 관리 Map에 저장
        clients.put(robotId, session);

        // 바로 조회해서 확인
        WebSocketSession testSession = clients.get(robotId);
        System.out.println("저장 직후 조회한 세션: " + testSession);

        System.out.println("저장 시 robotId 타입 : " + robotId.getClass().getName());  // 저장된 robotId 출력

        // storeId에 해당하는 로봇 그룹에 추가
        storeGroups.computeIfAbsent(storeId, k -> new HashSet<>()).add(robotId);

        System.out.println("Connected: robotId=" + robotId + ", storeId=" + storeId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션이 닫히면 전역 변수도 초기화
        globalSession = null;

        // 쿼리 파라미터에서 robotId와 storeId 추출
        String query = session.getUri().getQuery();
        Map<String, String> params = Arrays.stream(query.split("&"))
                                           .map(param -> param.split("="))
                                           .collect(Collectors.toMap(pair -> pair[0], pair -> pair[1]));

        String robotId = params.get("robotId");
        String storeId = params.get("storeId");

        // 세션이 닫히기 전, 세션이 있는지 확인
        System.out.println("세션 종료 전 clients 내용: " + clients.toString());
        System.out.println("제거 전 robotId " + robotId + "의 세션: " + clients.get(robotId));

        if (session != null) {
            System.out.println("제거 전 세션 상태: " + (session.isOpen() ? "열림" : "닫힘"));
        }

        // 세션이 닫히면 클라이언트와 그룹에서 제거
        clients.remove(robotId);
        if (storeGroups.containsKey(storeId)) {
            storeGroups.get(storeId).remove(robotId);
        }

        // 세션이 닫힌 후, 세션이 제거되었는지 확인
        System.out.println("세션 종료 후 clients 내용: " + clients.toString());
        System.out.println("제거 후 robotId " + robotId + "의 세션: " + clients.get(robotId));

        System.out.println("Disconnected: robotId=" + robotId + ", storeId=" + storeId);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        System.out.println("세션 : " + session.toString());
        // 메시지를 수신하면 처리 (예: 에코 메시지)
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);
        session.sendMessage(new TextMessage("Echo: " + payload));
    }

    // 전역 세션을 통해 메시지를 보내는 메서드
	 public void sendMessageToClient(String storeId, String ment) throws Exception {
	    
		 System.out.println("글로벌 세션 : " + globalSession);
		 if (globalSession != null && globalSession.isOpen()) {
	            // JSON 형태로 보낼 데이터를 객체로 생성
			  	MessageData messageData = new MessageData(storeId, ment);
	
	            // 객체를 JSON 문자열로 변환
	            String jsonMessage = objectMapper.writeValueAsString(messageData);
	
	            // 변환된 JSON 문자열을 클라이언트에게 전송
	            globalSession.sendMessage(new TextMessage(jsonMessage));
	        } else {
	            System.out.println("전역 세션이 없거나 세션이 닫혀 있습니다.");
	        } 
	}

    // 메시지 데이터 클래스를 정의
    @Data
    static class MessageData {
        private String id;
        private String ment;

        public MessageData(String id, String ment) {
            this.id = id;
            this.ment = ment;
        }
    }
}
