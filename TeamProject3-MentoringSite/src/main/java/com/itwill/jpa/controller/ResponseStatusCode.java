package com.itwill.jpa.controller;

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
	/******************* REPORT 메시지 ************************/
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
	
	
	
	public static final int LOGIN_FAIL_NOT_FOUND_MEMBER = 8000;
	public static final int LOGIN_FAIL_PASSWORD_MISMATCH_MEMBER = 8100;
	public static final int CREATE_FAIL_EXISTED_MEMBER = 8200;
	public static final int UNAUTHORIZED_MEMBER = 8300;
}