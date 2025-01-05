package com.itwill.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); 				 // /topic/messages/{roomId}로 구독을 받기 위해 설정
        config.setApplicationDestinationPrefixes("/app"); 	 // 클라이언트에서 메시지 전송을 받을 prefix 설정
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")
		        .setAllowedOrigins(
	                "http://localhost:3000",
	                "https://8db0-175-123-27-55.ngrok-free.app"
	            )
        		.withSockJS();  		 // SockJS를 사용하여 /chat 엔드포인트 설정
    }
    
}
