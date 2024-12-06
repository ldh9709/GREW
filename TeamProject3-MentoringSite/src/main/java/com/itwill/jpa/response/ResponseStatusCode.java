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
	
	/******************* MENTOR BOARD 메시지 ************************/
	public static final int CREATED_MENTOR_BOARD_SUCCESS = 2300;
	public static final int READ_MENTOR_BOARD_SUCCESS = 2310;
	public static final int READ_MENTOR_BOARD_LIST_SUCCESS = 2320;
	public static final int UPDATE_MENTOR_BOARD_SUCCESS = 2330;
	public static final int DELETE_MENTOR_BOARD_SUCCESS = 2340;
	public static final int MENTOR_BOARD_NOT_FOUND = 2350;
	
	/******************* FOLLOW 메시지 ************************/
	public static final int CREATE_FOLLOW_SUCCESS = 2400;
	public static final int READ_MENTORLIST_SUCCESS = 2410;
	public static final int READ_MENTEE_COUNT_SUCCESS = 2420;
	public static final int DELETE_FOLLOW_SUCCESS = 2430;
	
	/*알림(3000), 신고(4000), 질문(5000), 답변(6000), 채팅(7000)*/
	/******************* Alarm    메세지 ************************/
	
	/******************* REPORT   메시지 ************************/
	public static final int CREATED_REPORT_SUCCESS = 4000;
	public static final int READ_REPORT_SUCCESS = 4100;
	public static final int READ_REPORT_LIST_SUCCESS = 4200;
	public static final int UPDATE_REPORT_SUCCESS = 4300;
	public static final int REPORT_FAIL = 4400;
	/**********************INQUIRY 메시지**************************/
	public static final int CREATED_INQUIRY_SUCCESS = 5000;
	public static final int CREATED_INQUIRY_FAIL = 5100;
	public static final int UPDATE_INQUIRY_SUCCESS = 5200;
	public static final int UPDATE_INQUIRY_FAIL = 5300;
	public static final int DELETE_INQUIRY_SUCCESS = 5400;
	public static final int DELETE_INQUIRY_FAIL = 5500;
	public static final int READ_INQUIRY_LIST_SUCCESS = 5600;
	public static final int READ_INQUIRY_LIST_FAIL = 5700;
	public static final int INCREASE_VIEW_INQUIRY_SUCCESS = 5800;
	public static final int VIEW_INQUIRY_SUCCESS = 5900;
	
	/**********************ANSWER 메시지**************************/
	public static final int CREATED_ANSWER_SUCCESS = 6000;
	public static final int CREATED_ANSWER_FAIL = 6100;
	public static final int UPDATE_ANSWER_SUCCESS = 6200;
	public static final int UPDATE_ANSWER_FAIL = 6300;
	public static final int DELETE_ANSWER_SUCCESS = 6400;
	public static final int DELETE_ANSWER_FAIL = 6500;
	public static final int READ_ANSWER_LIST_SUCCESS = 6600;
	public static final int READ_ANSWER_LIST_FAIL = 6700;
	public static final int READ_ANSWER_SUCCESS = 6800;
	
	/******************* CHATTING 메시지 ************************/
	public static final int PENDING_CHATTING = 7000; // (멘토의 수락/거절 대기 상태)
	public static final int ACTIVE_CHATTING = 7100; // (채팅방 활성화)
	public static final int COMPLETED_CHATTING = 7200; // (활동 종료)
	public static final int REJECTED_CHATTING = 7300; // (멘토가 요청을 수락하지 않음)
	public static final int CANCELED_CHATTING_FAIL = 7400; // (멘티가 요청을 철회함)
	public static final int FORCE_CLOSED_CHATTING = 7500; // (관리자가 비정상적인 요청을 종료함)
	public static final int STAY_CHATTING = 7600; // (채팅방을 나가지않음)
	public static final int LEAVE_CHATTING = 7700; // (채팅방을 나감)
	public static final int SEND_CHATTING_SUCCESS=7800;
	public static final int SEND_CHATTING_FAIL=7900;
	
	
	/******************* REVIEW 메시지 **********************/
	public static final int CREATED_REVIEW_SUCCESS = 9000;
	public static final int CREATED_REVIEW_FAIL = 9100;
	public static final int UPDATE_REVIEW_SUCCESS = 9200;
	public static final int UPDATE_REVIEW_FAIL = 9300;
	public static final int DELETE_REVIEW_SUCCESS = 9400;
	public static final int DELETE_REVIEW_FAIL = 9500;
	public static final int VIEW_REVIEW_SUCCESS = 9600;
	public static final int VIEW_REVIEW_FAIL = 9700;
	public static final int READ_REVIEW_LIST_SUCCESS = 9800;
	public static final int READ_REVIEW_LIST_FAIL = 9900;
	
	
	public static final int LOGIN_FAIL_NOT_FOUND_MEMBER = 8000;
	public static final int LOGIN_FAIL_PASSWORD_MISMATCH_MEMBER = 8100;
	public static final int CREATE_FAIL_EXISTED_MEMBER = 8200;
	public static final int UNAUTHORIZED_MEMBER = 8300;
}