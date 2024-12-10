package com.itwill.jpa.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.itwill.jpa.dto.member_information.MemberDto;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@RequiredArgsConstructor
public class CustomMailSender {
	
	//Spring Boot가 제공하는 JavaMailSender 구현체를 주입
	@Autowired
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
	public void mimeMailSend(Mail mailDto) {
		try {
			//MIME 형식의 이메일 메시지 객체를 생성
			MimeMessage message = javaMailSender.createMimeMessage();
			
			/*
			 * 발신자의 이메일 주소를 설정
			 * form 변수는 @Value로 주입 받은 발신자 이메일 주소
			 */
			//message.setFrom(new InternetAddress(form));
			
			message.setFrom(mailDto.getAddress());
			
			System.out.println(">>>>>>>>마임 메일 센드 검증 : " + mailDto.getAddress());
			
			message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailDto.getAddress()));
			
			message.setSubject(mailDto.getTitle());
			
			message.setText(mailDto.getMessage());
			
			javaMailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//임시비밀번호 메일 발송
	public void sendFindPasswordMail(MemberDto.findPassword memberDto, String tempPassword) {
		/***** 사용자의 닉네임과 임시 비밀번호를 포함한 HTML 형식의 메시지를 생성 *****/
		String htmlMessage = "<div>" + memberDto.getMemberId() + "님의 임시 비밀번호는 <span style='font-weight:bold; color:blue;'> " + tempPassword + "</span> 입니다." + "</div>";
		
		Mail mail = new Mail();
		
		mail.setAddress(memberDto.getEmail());
		System.out.println(">>>>>>>>이메일 검증 : " + memberDto.getEmail());
		mail.setTitle("[used book] 임시 비밀번호 안내 메일입니다.");
		mail.setMessage(htmlMessage);
		
		//메일 발송
		this.mimeMailSend(mail);
	}
	
	
}
