//package com._thefull.dasom_web_demo.domain.webSoket;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
//
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//
//    private final RobotWebSocketHandler webSocketHandler;
//
//    public WebSocketConfig(RobotWebSocketHandler webSocketHandler) {
//        this.webSocketHandler = webSocketHandler;
//    }
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(webSocketHandler, "/webSoket")
//                .addInterceptors(new HttpSessionHandshakeInterceptor())  // HTTP 세션 연계
//                .setAllowedOrigins("*");
//    }
//}
