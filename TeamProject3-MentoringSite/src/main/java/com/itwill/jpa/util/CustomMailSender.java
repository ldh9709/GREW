package com.itwill.jpa.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
			
			message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailDto.getAddress()));
			
			message.setSubject(mailDto.getTitle());
			
			message.setText(mailDto.getMessage());
			
			javaMailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//가입 시 인증번호 발송
	public void sendJoinMaill(MemberDto.JoinFormDto joinFormDto, Integer tempNo) {
		/***** 임시 인증번호를 포함한 HTML 형식의 메시지를 생성 *****/
		String htmlMessage = "<div style='font-family: 'NanumSquare'; line-height: 1.6; color: #000; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 8px;'>"
                + "<h2 style='color: #333;'>[Grew]에서 인증번호 안내드립니다.</h2>"
                + "<p>요청하신 인증번호는 아래와 같습니다:</p>"
                + "<div style='margin: 20px 0; padding: 15px; background-color: #f9f9f9; border: 1px solid #eee; border-radius: 5px;'>"
                + "<span style='font-weight: bold; font-size: 18px; color: #007BFF;'>" + tempNo + "</span>"
                + "</div>"
                + "<p>감사합니다. [Grew] 팀 드림</p>"
                + "<hr style='border: 0; height: 1px; background: #ddd; margin: 20px 0;'>"
                + "<footer style='text-align: center; font-size: 12px; color: #999;'>"
                + "<p>&copy; 2024 Grew. All rights reserved.</p>"
                + "</footer>"
                + "</div>";
		
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			helper.setTo(joinFormDto.getEmail());
			helper.setSubject("[Grew] 인증번호 안내 메일입니다.");
			helper.setText(htmlMessage, true); // true는 HTML 형식
			
			javaMailSender.send(mimeMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//아이디 찾기 시 인증번호 발송
	public void sendFindIdMail(MemberDto.findId memberDto, Integer tempNo) {
		/***** 임시 인증번호를 포함한 HTML 형식의 메시지를 생성 *****/
		String htmlMessage = "<div style='font-family: 'NanumSquare'; line-height: 1.6; color: #000; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 8px;'>"
                + "<h2 style='color: #333;'>[Grew]에서 인증번호 안내드립니다.</h2>"
                + "<p>요청하신 인증번호는 아래와 같습니다:</p>"
                + "<div style='margin: 20px 0; padding: 15px; background-color: #f9f9f9; border: 1px solid #eee; border-radius: 5px;'>"
                + "<span style='font-weight: bold; font-size: 18px; color: #007BFF;'>" + tempNo + "</span>"
                + "</div>"
                + "<p>감사합니다. [Grew] 팀 드림</p>"
                + "<hr style='border: 0; height: 1px; background: #ddd; margin: 20px 0;'>"
                + "<footer style='text-align: center; font-size: 12px; color: #999;'>"
                + "<p>&copy; 2024 Grew. All rights reserved.</p>"
                + "</footer>"
                + "</div>";
		
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			helper.setTo(memberDto.getEmail());
			helper.setSubject("[Grew] 인증번호 안내 메일입니다.");
			helper.setText(htmlMessage, true); // true는 HTML 형식
			
			javaMailSender.send(mimeMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	//임시비밀번호 메일 발송
	public void sendFindPasswordMail(MemberDto.findPassword memberDto, String tempPassword) {
		/***** 사용자의 닉네임과 임시 비밀번호를 포함한 HTML 형식의 메시지를 생성 *****/
		 // HTML 메시지 생성
        String htmlMessage = "<div style='font-family: 'NanumSquare'; line-height: 1.6; color: #000; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 8px;'>"
                + "<h2 style='color: #333;'>[Grew]에서 임시 비밀번호 안내드립니다.</h2>"
                + "<p>요청하신 임시 비밀번호는 아래와 같습니다:</p>"
                + "<div style='margin: 20px 0; padding: 15px; background-color: #f9f9f9; border: 1px solid #eee; border-radius: 5px;'>"
                + "<span style='font-weight: bold; font-size: 18px; color: #007BFF;'>" + tempPassword + "</span>"
                + "</div>"
                + "<p style='margin: 0;'>보안을 위해 로그인 후 반드시 비밀번호를 변경해 주세요.</p>"
                + "<p>감사합니다. [Grew] 팀 드림</p>"
                + "<hr style='border: 0; height: 1px; background: #ddd; margin: 20px 0;'>"
                + "<footer style='text-align: center; font-size: 12px; color: #999;'>"
                + "<p>&copy; 2024 Grew. All rights reserved.</p>"
                + "</footer>"
                + "</div>";
		try {
			
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			
			helper.setTo(memberDto.getEmail());
            helper.setSubject("[Grew] 임시 비밀번호 안내 메일입니다.");
            helper.setText(htmlMessage, true); // true는 HTML 형식
//			mail.setAddress(memberDto.getEmail());
//			System.out.println(">>>>>>>>이메일 검증 : " + memberDto.getEmail());
//			mail.setTitle("[Grew] 임시 비밀번호 안내 메일입니다.");
//			mail.setMessage(htmlMessage);
            //메일 발송
            javaMailSender.send(mimeMessage);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
