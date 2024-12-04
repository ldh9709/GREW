package com.itwill.jpa.controller.member_information;

import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.member_information.MemtorBoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("/mentor-board")
public class MentorBoardController {

    @Autowired
    private MemtorBoardService mentorBoardService;

    /* 멘토 보드 등록 */
    @Operation(summary = "멘토 보드 등록")
    @PostMapping
    public ResponseEntity<Response> saveMentorBoard(@RequestBody MentorBoardDto mentorBoardDto) {
        
    	MentorBoardDto savedBoard = mentorBoardService.savememtorboard(mentorBoardDto);
    	
        Response response = new Response();
        response.setStatus(ResponseStatusCode.CREATED_MEMBER_SUCCESS);
        response.setMessage(ResponseMessage.CREATED_MEMBER_SUCCESS);
        response.setData(savedBoard);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    /* 멘토 보드 수정 */
    @Operation(summary = "멘토 보드 수정")
    @PutMapping
    public ResponseEntity<Response> updateMentorBoard(@RequestBody MentorBoardDto mentorBoardDto) throws Exception {
        MentorBoardDto updatedBoard = mentorBoardService.updatememtorboard(mentorBoardDto);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.UPDATE_MEMBER_SUCCESS);
        response.setMessage(ResponseMessage.UPDATE_MEMBER_SUCCESS);
        response.setData(updatedBoard);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    /* 멘토 보드 삭제(상태 변경, PUT 방식) */
    @Operation(summary = "멘토 보드 삭제(상태 변경, PUT 방식)")
    @PutMapping("/{mentorBoardNo}/delete")
    public ResponseEntity<Response> deleteMentorBoard(@PathVariable Long mentorBoardNo) throws Exception {
        MentorBoardDto deletedBoard = mentorBoardService.deletememtorboard(
                MentorBoardDto.builder().mentorBoardNo(mentorBoardNo).build()
        );

        Response response = new Response();
        response.setStatus(ResponseStatusCode.DELETE_MEMBER_SUCCESS);
        response.setMessage(ResponseMessage.DELETE_MEMBER_SUCCESS);
        response.setData(deletedBoard);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
    /* 멘토 보드 상세 조회 */
    @Operation(summary = "멘토 보드 상세 조회")
    @GetMapping("/{mentorBoardNo}")
    public ResponseEntity<Response> getMentorBoard(@PathVariable Long mentorBoardNo) {
        MentorBoardDto mentorBoard = mentorBoardService.getmemtorboard(mentorBoardNo);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MEMBER_SUCCESS);
        response.setMessage(ResponseMessage.READ_MEMBER_SUCCESS);
        response.setData(mentorBoard);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    /* 특정 멘토의 모든 멘토 보드 조회 */
    @Operation(summary = "특정 멘토의 모든 멘토 보드 조회")
    @GetMapping("/member/{memberNo}")
    public ResponseEntity<Response> getMentorBoardsByMember(@PathVariable Long memberNo) {
        List<MentorBoardDto> mentorBoards = mentorBoardService.getMentorBoardsByMemberNo(memberNo);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MEMBER_LIST_SUCCESS);
        response.setMessage(ResponseMessage.READ_MEMBER_LIST_SUCCESS);
        response.setData(mentorBoards);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
