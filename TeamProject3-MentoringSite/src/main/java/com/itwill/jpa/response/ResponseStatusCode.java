package com.itwill.jpa.response;

public class ResponseStatusCode {
	
	/******************* MEMBER 메시지 ************************/
	
	public static final int CREATED_MEMBER_SUCCESS = 2000;
	public static final int READ_MEMBER_SUCCESS = 2100;
	public static final int READ_MEMBER_LIST_SUCCESS = 2200;
	public static final int UPDATE_MEMBER_SUCCESS = 2300;
	public static final int DELETE_MEMBER_SUCCESS = 2400;
	public static final int LOGIN_MEMBER_SUCCESS = 2500;
	public static final int LOGOUT_MEMBER_SUCCESS = 2600;
	
	/*알림(3000), 신고(4000), 질문(5000), 답변(6000), 채팅(7000)*/
	/******************* Alarm    메세지 ************************/
	
	/******************* REPORT   메시지 ************************/
	public static final int CREATED_REPORT_SUCCESS = 4000;
	public static final int READ_REPORT_SUCCESS = 4100;
	public static final int READ_REPORT_LIST_SUCCESS = 4200;
	public static final int UPDATE_REPORT_SUCCESS = 4300;
	public static final int REPORT_FAIL = 4400;
	/******************* INQUIRY  메시지 ************************/
	
	/******************* ANSWER   메시지 ************************/
	
	/******************* CHATTING 메시지 ************************/
	public static final int PENDING_CHATTING = 7000; // (멘토의 수락/거절 대기 상태)
	public static final int ACTIVE_CHATTING = 7100; // (채팅방 활성화)
	public static final int COMPLETED_CHATTING = 7200; // (활동 종료)
	public static final int REJECTED_CHATTING = 7300; // (멘토가 요청을 수락하지 않음)
	public static final int CANCELED_CHATTING_FAIL = 7400; // (멘티가 요청을 철회함)
	public static final int FORCE_CLOSED_CHATTING = 7500; // (관리자가 비정상적인 요청을 종료함)
	
	public static final int LOGIN_FAIL_NOT_FOUND_MEMBER = 8000;
	public static final int LOGIN_FAIL_PASSWORD_MISMATCH_MEMBER = 8100;
	public static final int CREATE_FAIL_EXISTED_MEMBER = 8200;
	public static final int UNAUTHORIZED_MEMBER = 8300;
}