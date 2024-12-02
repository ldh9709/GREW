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
    
    public static final String LOGIN_FAIL_NOT_FOUND_MEMBER = "회원을 찾을 수 없습니다.";
    public static final String LOGIN_FAIL_PASSWORD_MISMATCH_MEMBER = "회원 패스워드 불일치";
    public static final String CREATE_FAIL_EXISTED_MEMBER = "회원 아이디 중복";
    public static final String UNAUTHORIZED_MEMBER = "인증받지않은 요청입니다.";
    
}