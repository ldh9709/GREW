package com.itwill.jpa.service.member_information;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.member_information.InterestDto;
import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Interest;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.MemberRepository;
import com.itwill.jpa.util.CustomMailSender;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
	
	private final CustomMailSender customMailSender;
	
	@Override
	public void sendMail(String address, String title, String message) {
		CustomMailSender.Mail mail = new CustomMailSender.Mail();
		
		mail.setAddress(address);
		
		mail.setTitle(title);
		
		mail.setMessage(message);
		
		customMailSender.mimeMailSend(mail);
	}
	

}
