package com.itwill.jpa.response;

public class ResponseMessage {
	
	/******************* MEMBER 메시지 ************************/

	public static final String CREATED_MEMBER_SUCCESS = "회원 가입 성공";
	public static final String READ_MEMBER_SUCCESS = "회원 정보 조회 성공";
	public static final String READ_MEMBER_LIST_SUCCESS = "회원 리스트 정보 조회 성공";
	public static final String UPDATE_MEMBER_SUCCESS = "회원 정보 수정 성공";
	public static final String DELETE_MEMBER_SUCCESS = "회원 탈퇴 성공";
    public static final String LOGIN_MEMBER_SUCCESS = "회원 로그인 성공";
    public static final String LOGOUT_MEMBER_SUCCESS = "회원 로그 아웃";
    
    /*알림(3000), 신고(4000), 질문(5000), 답변(6000), 채팅(7000)*/
    /******************* Alarm    메세지 ************************/
	
	/******************* REPORT   메시지 ************************/
	public static final String CREATED_REPORT_SUCCESS = "신고 등록 성공";
	public static final String READ_REPORT_SUCCESS = "신고 조회 성공";
	public static final String READ_REPORT_LIST_SUCCESS = "신고 리스트 성공";
	public static final String UPDATE_REPORT_SUCCESS = "신고 상태변경 성공";
	public static final String REPORT_FAIL = "신고 실패";
	/**********************INQUIRY 메시지**************************/
    public static final String CREATED_INQUIRY_SUCCESS = "질문 등록 성공";
    public static final String CREATED_INQUIRY_FAIL = "질문 등록 실패";
    public static final String UPDATE_INQUIRY_SUCCESS = "질문 수정 성공";
    public static final String UPDATE_INQUIRY_FAIL = "질문 수정 실패";
    public static final String DELETE_INQUIRY_SUCCESS = "질문 삭제(수정) 성공";
    public static final String DELETE_INQUIRY_FAIL = "질문 삭제(수정) 실패";
    public static final String READ_INQUIRY_LIST_SUCCESS = "질문 리스트 불러오기 성공";
    public static final String READ_INQUIRY_LIST_FAIL = "질문 리스트 불러오기 실패";
    public static final String INCREASE_VIEW_INQUIRY_SUCCESS = "조회수 증가 성공";
    public static final String VIEW_INQUIRY_SUCCESS = "선택 질문 불러오기 성공";
    
    /**********************ANSWER 메시지**************************/
	public static final String CREATED_ANSWER_SUCCESS = "답변 등록 성공";
	public static final String CREATED_ANSWER_FAIL = "답변 등록 실패";
	public static final String UPDATE_ANSWER_SUCCESS = "답변 수정 성공";
	public static final String UPDATE_ANSWER_FAIL = "답변 수정 실패";
	public static final String DELETE_ANSWER_SUCCESS = "답변 삭제(수정) 성공";
	public static final String DELETE_ANSWER_FAIL = "답변 삭제(수정) 실패";
	public static final String READ_ANSWER_LIST_SUCCESS = "답변 리스트 불러오기 성공";
	public static final String READ_ANSWER_LIST_FAIL = "답변 리스트 불러오기 실패";
	
	/******************* CHATTING 메시지 ************************/
	public static final String PENDING_CHATTING = "요청 대기 중"; // (멘토의 수락/거절 대기 상태)
	public static final String ACTIVE_CHATTING = "멘토링 진행 중"; // (채팅방 활성화)
	public static final String COMPLETED_CHATTING = "멘토링 완료"; // (활동 종료)
	public static final String REJECTED_CHATTING = "요청 거절"; // (멘토가 요청을 수락하지 않음)
	public static final String CANCELED_CHATTING_FAIL = "요청 취소"; // (멘티가 요청을 철회함)
	public static final String FORCE_CLOSED_CHATTING = "강제 종료"; // (관리자가 비정상적인 요청을 종료함)
	
    public static final String LOGIN_FAIL_NOT_FOUND_MEMBER = "회원을 찾을 수 없습니다.";
    public static final String LOGIN_FAIL_PASSWORD_MISMATCH_MEMBER = "회원 패스워드 불일치";
    public static final String CREATE_FAIL_EXISTED_MEMBER = "회원 아이디 중복";
    public static final String UNAUTHORIZED_MEMBER = "인증받지않은 요청입니다.";    
}