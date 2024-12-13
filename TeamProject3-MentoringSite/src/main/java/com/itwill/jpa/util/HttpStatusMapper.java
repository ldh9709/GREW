package com.itwill.jpa.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.itwill.jpa.response.ResponseStatusCode;

public class HttpStatusMapper {

    private static final Map<Integer, HttpStatus> statusCodeMap = new HashMap<>();

    static {
        // MEMBER 관련 상태 코드 매핑



        // FOLLOW 관련 상태 코드 매핑
    	statusCodeMap.put(ResponseStatusCode.CREATE_FOLLOW_FAIL, HttpStatus.BAD_REQUEST);
    	statusCodeMap.put(ResponseStatusCode.READ_MENTORLIST_FAIL, HttpStatus.NOT_FOUND);
    	statusCodeMap.put(ResponseStatusCode.READ_MENTEE_COUNT_FAIL, HttpStatus.NOT_FOUND);
    	statusCodeMap.put(ResponseStatusCode.DELETE_FOLLOW_FAIL, HttpStatus.BAD_REQUEST);

    	// MENTOR BOARD 관련 상태 코드 매핑
    	statusCodeMap.put(ResponseStatusCode.CREATED_MENTOR_BOARD_FAIL, HttpStatus.BAD_REQUEST);
    	statusCodeMap.put(ResponseStatusCode.UPDATE_MENTOR_BOARD_FAIL, HttpStatus.BAD_REQUEST);
    	statusCodeMap.put(ResponseStatusCode.DELETE_MENTOR_BOARD_FAIL, HttpStatus.BAD_REQUEST);
    	statusCodeMap.put(ResponseStatusCode.MENTOR_BOARD_NOT_FOUND, HttpStatus.NOT_FOUND);
    	statusCodeMap.put(ResponseStatusCode.IMAGE_UPLOAD_FAIL, HttpStatus.BAD_REQUEST);
       //MENTOR PROFILE 관련 상태 코드 매핑
    	statusCodeMap.put(ResponseStatusCode.CREATED_MENTOR_PROFILE_FAIL, HttpStatus.BAD_REQUEST); // 멘토 프로필 생성 실패
    	statusCodeMap.put(ResponseStatusCode.MENTOR_PROFILE_NOT_FOUND_CODE, HttpStatus.NOT_FOUND); // 멘토 프로필을 찾을 수 없음
    	statusCodeMap.put(ResponseStatusCode.UPDATE_MENTOR_PROFILE_FAIL_CODE, HttpStatus.BAD_REQUEST); // 멘토 프로필 수정 실패
    	statusCodeMap.put(ResponseStatusCode.MENTOR_PROFILE_STATUS_UPDATE_FAIL_CODE, HttpStatus.BAD_REQUEST); // 멘토 프로필 상태 업데이트 실패
    	statusCodeMap.put(ResponseStatusCode.IMAGE_UPLOAD_FAIL, HttpStatus.BAD_REQUEST); // 이미지 업로드 실패
    	

        // CATEGORY 관련 상태 코드 매핑

        // ALARM 관련 상태 코드 매핑

        // REPORT 관련 상태 코드 매핑
    	statusCodeMap.put(ResponseStatusCode.CREATED_REPORT_FAIL, HttpStatus.BAD_REQUEST);
    	statusCodeMap.put(ResponseStatusCode.READ_REPORT_FAIL, HttpStatus.NOT_FOUND);
    	statusCodeMap.put(ResponseStatusCode.READ_REPORT_LIST_FAIL, HttpStatus.NOT_FOUND);
    	statusCodeMap.put(ResponseStatusCode.UPDATE_REPORT_FAIL, HttpStatus.BAD_REQUEST);

        // INQUIRY 관련 상태 코드 매핑
        statusCodeMap.put(ResponseStatusCode.CREATED_INQUIRY_FAIL, HttpStatus.BAD_REQUEST);
        statusCodeMap.put(ResponseStatusCode.UPDATE_INQUIRY_FAIL, HttpStatus.BAD_REQUEST);
        statusCodeMap.put(ResponseStatusCode.DELETE_INQUIRY_FAIL, HttpStatus.BAD_REQUEST);
        statusCodeMap.put(ResponseStatusCode.READ_INQUIRY_LIST_FAIL, HttpStatus.NOT_FOUND);
        statusCodeMap.put(ResponseStatusCode.READ_INQUIRY_FAIL, HttpStatus.NOT_FOUND);

        // ANSWER 관련 상태 코드 매핑
        statusCodeMap.put(ResponseStatusCode.CREATED_ANSWER_FAIL, HttpStatus.BAD_REQUEST);
        statusCodeMap.put(ResponseStatusCode.UPDATE_ANSWER_FAIL, HttpStatus.BAD_REQUEST);
        statusCodeMap.put(ResponseStatusCode.DELETE_ANSWER_FAIL, HttpStatus.BAD_REQUEST);
        statusCodeMap.put(ResponseStatusCode.ACCEPT_ANSWER_FAIL, HttpStatus.BAD_REQUEST);
        statusCodeMap.put(ResponseStatusCode.READ_ANSWER_LIST_FAIL, HttpStatus.NOT_FOUND);
        statusCodeMap.put(ResponseStatusCode.READ_ANSWER_FAIL, HttpStatus.NOT_FOUND);
    }

    public static HttpStatus getHttpStatus(int statusCode) {
        return statusCodeMap.getOrDefault(statusCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
