package com.itwill.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
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
	                "http://localhost:3000"
	            )
        		.withSockJS();  		 // SockJS를 사용하여 /chat 엔드포인트 설정
    }
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        // WebSocketTransport에서 메시지 크기 제한을 증가시킴
        registration.setMessageSizeLimit(1048576); // 메시지 크기 한도 설정 (1MB)
        registration.setSendTimeLimit(20 * 1000);  // 메시지 전송 시간 제한 (20초)
        registration.setSendBufferSizeLimit(1048576); // 메시지 버퍼 크기 한도 (1MB)
    }
    @Bean
    public ServletServerContainerFactoryBean servletContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        // 텍스트 메시지의 최대 크기 설정 (단위: 바이트)
        container.setMaxTextMessageBufferSize(1485760);   // 1.4MB
        // 바이너리 메시지의 최대 크기 설정 (단위: 바이트)
        container.setMaxBinaryMessageBufferSize(1485760); // 1.4MB
        return container;
    }
    
}
