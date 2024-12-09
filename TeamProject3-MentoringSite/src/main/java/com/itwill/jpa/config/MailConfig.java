package com.itwill.jpa.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;



@Configuration//Bean을 등록하기 위한 클래스임을 표시
public class MailConfig {
	
    @Bean
    public JavaMailSender javaMailService() {
    	//JavaMailSenderImpl : 메일 전송을 위한 서버 및 인증 정보 설정
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        
        //이메일을 전송할 SMTP주소 설정
        javaMailSender.setHost("smtp.gmail.com");
        
        //SMTP에 로그인하기 위한 계정 설정
        javaMailSender.setUsername("wnsgud77773@gmail.com");
        
        //SMTP 서버 비밀번호 설정
        javaMailSender.setPassword("123456789)QWER");
        
        //SMTP 서버의 포트를 설정. 네이버, 구글은 SSL을 사용하는 포트 465를 사용
        javaMailSender.setPort(465);
        
        //추가적인 SMTP 속성을 설정하기 위해 getMailProperties() 메서드의 반환값을 사용
        javaMailSender.setJavaMailProperties(getMailProperties());
        
        return javaMailSender;
    }
    
    //프로퍼티 설정
    private Properties getMailProperties() {
    	
        Properties properties = new Properties();
        
        //이메일 전송 프로토콜을 SMTP로 설정
        properties.setProperty("mail.transport.protocol", "smtp");
        //SMTP 서버에 인증(계정, 비밀번호)를 요구하도록 설정
        properties.setProperty("mail.smtp.auth", "true");
        //TLS(전송 계층 보안)를 사용하여 암호화된 연결을 활성화
        properties.setProperty("mail.smtp.starttls.enable", "true");
        //메일 전송과 관련된 디버깅 로그를 출력
        properties.setProperty("mail.debug", "true");
        //SSL(보안 소켓 계층)을 사용할 때 신뢰할 SMTP 서버를 설정
        properties.setProperty("mail.smtp.ssl.trust","smtp.naver.com");
        //SSL을 활성화하여 SMTP 서버와의 연결을 암호화.
        properties.setProperty("mail.smtp.ssl.enable","true");
        
        return properties;
    }
}