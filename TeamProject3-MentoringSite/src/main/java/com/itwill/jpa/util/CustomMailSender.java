package com.itwill.jpa.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@RequiredArgsConstructor
public class CustomMailSender {
	
	private final JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String form;
	
	@Getter
	@Setter
	@NoArgsConstructor
	public static class Mail {
		//수신자의 메일 주소
		private String address;
		//메일 제목
		private String title;
		//메일 본문
		private String message;
	}
	
	//일반적인 이메일 전송을 처리하는 메소드
	private void mimeMailSend(Mail mailDto) {
		try {
			//MIME 형식의 이메일 메시지 객체를 생성
			MimeMessage message = javaMailSender.createMimeMessage();
			
			/*
			 * 발신자의 이메일 주소를 설정
			 * form 변수는 @Value로 주입 받은 발신자 이메일 주소
			 */
			message.setFrom(new InternetAddress(form));
			
			message.setSubject(mailDto.getTitle());
			
			message.setText(mailDto.getMessage());
			
			javaMailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
