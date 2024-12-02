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
	
	
	public static final int LOGIN_FAIL_NOT_FOUND_MEMBER = 8000;
	public static final int LOGIN_FAIL_PASSWORD_MISMATCH_MEMBER = 8100;
	public static final int CREATE_FAIL_EXISTED_MEMBER = 8200;
	public static final int UNAUTHORIZED_MEMBER = 8300;
	
	
}