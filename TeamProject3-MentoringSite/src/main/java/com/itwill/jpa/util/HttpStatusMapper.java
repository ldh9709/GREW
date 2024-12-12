package com.itwill.jpa.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.itwill.jpa.response.ResponseStatusCode;

public class HttpStatusMapper {

    private static final Map<Integer, HttpStatus> statusCodeMap = new HashMap<>();

    static {
        // MEMBER 관련 상태 코드 매핑

        // MENTOR BOARD 관련 상태 코드 매핑
    	statusCodeMap.put(ResponseStatusCode.CREATED_MENTOR_BOARD_FAIL, HttpStatus.BAD_REQUEST);
    	statusCodeMap.put(ResponseStatusCode.UPDATE_MENTOR_BOARD_FAIL, HttpStatus.BAD_REQUEST);
    	statusCodeMap.put(ResponseStatusCode.DELETE_MENTOR_BOARD_FAIL, HttpStatus.BAD_REQUEST);
    	statusCodeMap.put(ResponseStatusCode.MENTOR_BOARD_NOT_FOUND, HttpStatus.NOT_FOUND);
    	statusCodeMap.put(ResponseStatusCode.IMAGE_UPLOAD_FAIL, HttpStatus.BAD_REQUEST);
        // FOLLOW 관련 상태 코드 매핑

        // CATEGORY 관련 상태 코드 매핑

        // ALARM 관련 상태 코드 매핑

        // REPORT 관련 상태 코드 매핑

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
