package com._thefull.dasom_web_demo.domain.webSoket;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // "/robot" 엔드포인트에서 웹소켓을 처리하도록 핸들러를 등록
        registry.addHandler(new RobotWebSocketHandler(), "/robot")
                .setAllowedOrigins("*");  // 모든 도메인 허용 (실제 운영 환경에서는 제한 권장)
    }
}
