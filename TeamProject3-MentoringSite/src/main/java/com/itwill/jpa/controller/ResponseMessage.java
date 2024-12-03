package com.itwill.jpa.controller;


public class ResponseMessage {
	
	/******************* MEMBER 메시지 ************************/

	public static final String CREATED_MEMBER_SUCCESS = "회원 가입 성공";
	public static final String READ_MEMBER_SUCCESS = "회원 정보 조회 성공";
	public static final String READ_MEMBER_LIST_SUCCESS = "회원 리스트 정보 조회 성공";
	public static final String UPDATE_MEMBER_SUCCESS = "회원 정보 수정 성공";
	public static final String DELETE_MEMBER_SUCCESS = "회원 탈퇴 성공";
    public static final String LOGIN_MEMBER_SUCCESS = "회원 로그인 성공";
    public static final String LOGOUT_MEMBER_SUCCESS = "회원 로그 아웃";
    
	/******************* REPORT 메시지 ************************/
	public static final String CREATED_REPORT_SUCCESS = "신고 등록 성공";
	public static final String READ_REPORT_SUCCESS = "신고 조회 성공";
	public static final String READ_REPORT_LIST_SUCCESS = "신고 리스트 성공";
	public static final String UPDATE_REPORT_SUCCESS = "신고 상태변경 성공";
	public static final String REPORT_FAIL = "신고 실패";
	
    
    public static final String LOGIN_FAIL_NOT_FOUND_MEMBER = "회원을 찾을 수 없습니다.";
    public static final String LOGIN_FAIL_PASSWORD_MISMATCH_MEMBER = "회원 패스워드 불일치";
    public static final String CREATE_FAIL_EXISTED_MEMBER = "회원 아이디 중복";
    public static final String UNAUTHORIZED_MEMBER = "인증받지않은 요청입니다.";
    
    /**********************INQUIRY 메시지**************************/
    public static final String CREATED_INQUIRY_SUCCESS = "질문 등록 성공";
    public static final String CREATED_INQUIRY_FAIL = "질문 등록 실패";
    public static final String UPDATE_INQUIRY_SUCCESS = "질문 수정 성공";
    public static final String UPDATE_INQUIRY_FAIL = "질문 수정 실패";
    public static final String DELETE_INQUIRY_SUCCESS = "질문 삭제(수정) 성공";
    public static final String DELETE_INQUIRY_FAIL = "질문 삭제(수정) 실패";
    public static final String READ_INQUIRY_LIST_SUCCESS = "질문 리스트 불러오기 성공";
    public static final String READ_INQUIRY_LIST_FAIL = "질문 리스트 불러오기 실패";
    
    /**********************ANSWER 메시지**************************/
	public static final String CREATED_ANSWER_SUCCESS = "답변 등록 성공";
	public static final String CREATED_ANSWER_FAIL = "답변 등록 실패";
	public static final String UPDATE_ANSWER_SUCCESS = "답변 수정 성공";
	public static final String UPDATE_ANSWER_FAIL = "답변 수정 실패";
	public static final String DELETE_ANSWER_SUCCESS = "답변 삭제(수정) 성공";
	public static final String DELETE_ANSWER_FAIL = "답변 삭제(수정) 실패";
	public static final String READ_ANSWER_LIST_SUCCESS = "답변 리스트 불러오기 성공";
	public static final String READ_ANSWER_LIST_FAIL = "답변 리스트 불러오기 실패";
    /*알림, 신고, 질문, 답변, 채팅*/
    
}