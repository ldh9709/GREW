package com.itwill.jpa.service.member_information;

public interface EmailService {
	/*
	 * address : 수신자의 메일 주소
	 * title : 메일 제목
	 * message : 메일 본문
	 */
	void sendMail(String address, String title, String message);
	
}
