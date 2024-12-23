package com.itwill.jpa.response;

public class ResponseStatusCode {
	
	/******************* MEMBER 메시지 ************************/
	 //성공 코드(2000 ~ 2049)
    public static final int CREATED_MEMBER_SUCCESS = 2000; // 회원 생성 성공
    public static final int READ_MEMBER_SUCCESS = 2001;    // 회원 조회 성공
    public static final int READ_MEMBER_LIST_SUCCESS = 2002; // 회원 목록 조회 성공
    public static final int UPDATE_MEMBER_SUCCESS = 2003;  // 회원 정보 수정 성공
    public static final int DELETE_MEMBER_SUCCESS = 2004;  // 회원 삭제 성공
    public static final int LOGIN_MEMBER_SUCCESS = 2005;   // 회원 로그인 성공
    public static final int LOGOUT_MEMBER_SUCCESS = 2006;  // 회원 로그아웃 성공
    public static final int ACTIVATE_MEMBER_SUCCESS = 2007; // 회원 활성화 성공
    public static final int DEACTIVATE_MEMBER_SUCCESS = 2008; // 회원 비활성화 성공
    public static final int PASSWORD_RESET_SUCCESS = 2009; // 비밀번호 재설정 성공
    public static final int EMAIL_SEND_SUCCESS = 2010;     // 이메일 발송 성공
    public static final int INPUTCODE_CONFIRM_SUCCESS = 2011;     // 이메일 발송 성공
    public static final int UPDATE_ROLE_SUCCESS = 2012;     // 회원 권한 수정 성공

    // 실패 코드 (2050번대 ~ )
    public static final int CREATED_MEMBER_FAIL = 2050;    // 회원 생성 실패
    public static final int INVALID_AUTH_CODE = 2051;      // 인증 코드 불일치
    public static final int READ_MEMBER_FAIL = 2052;       // 특정 회원 조회 실패
    public static final int READ_MEMBER_LIST_FAIL = 2053;  // 회원 목록 조회 실패
    public static final int UPDATE_MEMBER_FAIL = 2054;     // 회원 정보 수정 실패
    public static final int DELETE_MEMBER_FAIL = 2055;     // 회원 삭제 실패
    public static final int LOGIN_MEMBER_FAIL = 2056;      // 회원 로그인 실패
    public static final int PASSWORD_RESET_FAIL = 2057;    // 비밀번호 재설정 실패
    public static final int EMAIL_SEND_FAIL = 2058;        // 이메일 발송 실패
    public static final int INPUTCODE_CONFIRM_FAIL = 2059;        // 이메일 발송 실패
    public static final int MEMBER_IS_NOT_EMAIL = 2060;        // 이메일로 가입한 회원이 아님
    public static final int AUTHENTICATION_FAILED = 2061;        // 토큰과 정보가 일치하지 않음
    public static final int UPDATE_ROLE_FAIL = 2062;     // 회원 권한 수정 실패
	
	/******************* MEMBER 메시지 ************************/
	
	/******************* INTEREST 메시지 ************************/
	
	
	
	
	/******************* MENTOR BOARD 메시지 ************************/
	public static final int CREATED_MENTOR_BOARD_SUCCESS = 2300;
	public static final int READ_MENTOR_BOARD_SUCCESS = 2310;
	public static final int READ_MENTOR_BOARD_LIST_SUCCESS = 2320;
	public static final int UPDATE_MENTOR_BOARD_SUCCESS = 2330;
	public static final int DELETE_MENTOR_BOARD_SUCCESS = 2340;
	public static final int MENTOR_BOARD_NOT_FOUND = 2350;
	public static final int CREATED_MENTOR_BOARD_FAIL = 2301; // 멘토 보드 등록 실패
	public static final int UPDATE_MENTOR_BOARD_FAIL = 2331; // 멘토 보드 수정 실패
	public static final int DELETE_MENTOR_BOARD_FAIL = 2341; // 멘토 보드 삭제 실패
	public static final int IMAGE_UPLOAD_FAIL = 2360; // 이미지 업로드 실패
	public static final int MEMBER_NOT_FOUND = 2370; // 멤버를 찾을 수 없음 
	public static final int NOT_A_MENTOR = 2375; // 멘토가 아님
	/******************* MENTOR PROFILE 메시지 코드 ************************/
    public static final int CREATED_MENTOR_PROFILE_SUCCESS_CODE = 2350;
    public static final int READ_MENTOR_PROFILE_SUCCESS_CODE = 2355;
    public static final int READ_MENTOR_PROFILE_LIST_SUCCESS_CODE = 2360;
    public static final int UPDATE_MENTOR_PROFILE_SUCCESS_CODE = 2365;
    public static final int DELETE_MENTOR_PROFILE_SUCCESS_CODE = 2370;
    public static final int MENTOR_PROFILE_NOT_FOUND_CODE = 2375;
    public static final int READ_MENTOR_PROFILE_LIST_FAIL_CODE = 2380;
    public static final int MENTOR_PROFILE_STATUS_UPDATE_SUCCESS_CODE = 2385;
    public static final int MENTOR_PROFILE_STATUS_UPDATE_FAIL_CODE = 2390;
    public static final int CREATED_MENTOR_PROFILE_FAIL = 2395; // 멘토 프로필 생성 실패
    public static final int ALREADY_HAS_MENTOR_PROFILE = 2396; // 이미 멘토 프로필이 존재함
    public static final int MEMBER_MENTOR_NOT_FOUND = 2397; // 멤버를 찾을 수 없음
    public static final int CATEGORY_NOT_FOUND = 2398; // 카테고리를 찾을 수 없음
    public static final int IMAGE_MENTOR_UPLOAD_FAIL = 2399; // 이미지 업로드 실패
    public static final int UPDATE_MENTOR_PROFILE_FAIL_CODE = 2389; // 이미지 업로드 실패
    public static final int READ_MENTOR_PROFILE_FAIL = 2387; // 멘토 프로필 조회 실패
	/******************* FOLLOW 메시지 ************************/
	public static final int CREATE_FOLLOW_SUCCESS = 2400;
	public static final int READ_MENTORLIST_SUCCESS = 2410;
	public static final int READ_MENTEE_COUNT_SUCCESS = 2420;
	public static final int DELETE_FOLLOW_SUCCESS = 2430;
	
	public static final int CREATE_FOLLOW_FAIL = 2440;
	public static final int READ_MENTORLIST_FAIL= 2450;
	public static final int READ_MENTEE_COUNT_FAIL= 2460;
	public static final int DELETE_FOLLOW_FAIL= 2470;
	
	/******************* CATEGORY 메시지 ************************/
	public static final int CREATE_CATEGORY_SUCCESS = 2400;
	public static final int READ_CATEGORY_SUCCESS = 2410;
	public static final int READ_CATEGORYLIST_SUCCESS = 2420;
	public static final int UPDATE_CATEGORY_SUCCESS = 2430;
	public static final int DELETE_CATEGORY_SUCCESS = 2440;
	
	/*알림(3000), 신고(4000), 질문(5000), 답변(6000), 채팅(7000)*/
	/******************* Alarm    메세지 ************************/
	public static final int CREATED_ALARM_SUCCESS= 3000;
	public static final int READ_ALARM_SUCCESS= 3200;
	public static final int DELETE_ALARM_SUCCESS= 3300;
	public static final int IS_READ_ALARM_SUCCESS= 3400;
	/******************* REPORT   메시지 ************************/
	public static final int CREATED_REPORT_SUCCESS = 4000;
	public static final int READ_REPORT_SUCCESS = 4100;
	public static final int READ_REPORT_LIST_SUCCESS = 4200;
	public static final int UPDATE_REPORT_SUCCESS = 4300;
	
	public static final int CREATED_REPORT_FAIL = 4400;
	public static final int READ_REPORT_FAIL = 4500;
	public static final int READ_REPORT_LIST_FAIL= 4600;
	public static final int UPDATE_REPORT_FAIL = 4700;
	
	/**********************INQUIRY 메시지**************************/
	public static final int CREATED_INQUIRY_SUCCESS = 5000;
	public static final int CREATED_INQUIRY_FAIL = 5001;
	public static final int UPDATE_INQUIRY_SUCCESS = 5100;
	public static final int UPDATE_INQUIRY_FAIL = 5101;
	public static final int DELETE_INQUIRY_SUCCESS = 5200;
	public static final int DELETE_INQUIRY_FAIL = 5201;
	public static final int READ_INQUIRY_LIST_SUCCESS = 5300;
	public static final int READ_INQUIRY_LIST_FAIL = 5301;
	public static final int INCREASE_VIEW_INQUIRY_SUCCESS = 5400;
	public static final int INCREASE_VIEW_INQUIRY_FAIL = 5401;
	public static final int READ_INQUIRY_SUCCESS = 5500;
	public static final int READ_INQUIRY_FAIL = 5501;
	public static final int READ_IPCHECK_SUCCESS = 5600;
	public static final int READ_IPCHECK_FAIL = 5601;
	
	
	/**********************ANSWER 메시지**************************/
	public static final int CREATED_ANSWER_SUCCESS = 6000;
	public static final int CREATED_ANSWER_FAIL = 6001;
	public static final int UPDATE_ANSWER_SUCCESS = 6200;
	public static final int UPDATE_ANSWER_FAIL = 6201;
	public static final int DELETE_ANSWER_SUCCESS = 6300;
	public static final int DELETE_ANSWER_FAIL = 6301;
	public static final int ACCEPT_ANSWER_SUCCESS = 6400;
	public static final int ACCEPT_ANSWER_FAIL = 6401;
	public static final int READ_ANSWER_LIST_SUCCESS = 6500;
	public static final int READ_ANSWER_LIST_FAIL = 6600;
	public static final int READ_ANSWER_SUCCESS = 6700;
	public static final int READ_ANSWER_FAIL = 6701;
	
	
	/******************* CHATTING 메시지 ************************/
	public static final int PENDING_CHATTING = 7000; // (멘토의 수락/거절 대기 상태)
	public static final int ACTIVE_CHATTING = 7100; // (채팅방 활성화)
	public static final int COMPLETED_CHATTING = 7200; // (활동 종료)
	public static final int REJECTED_CHATTING = 7300; // (멘토가 요청을 수락하지 않음)
	public static final int CANCELED_CHATTING_FAIL = 7400; // (멘티가 요청을 철회함)
	public static final int FORCE_CLOSED_CHATTING = 7500; // (관리자가 비정상적인 요청을 종료함)
	public static final int STAY_CHATTING = 7600; // (채팅방을 나가지않음)
	public static final int LEAVE_CHATTING = 7700; // (채팅방을 나감)
	public static final int SEND_CHATTING_SUCCESS = 7800;
	public static final int SEND_CHATTING_FAIL = 7900;
	public static final int CHATTING_LIST_SUCCESS = 7010;
	public static final int CHATTING_NAME = 7020;
	public static final int CHATTING_NAME_CHANGE = 7030;
	public static final int CHATTING_MESSAGE = 7040;
	public static final int READ_MESSAGE = 7050;
	
	
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
	
	
	/******************* 일반적인오류 메시지 **********************/
	public static final int INTERNAL_SERVER_ERROR = 10000;
	
	
}