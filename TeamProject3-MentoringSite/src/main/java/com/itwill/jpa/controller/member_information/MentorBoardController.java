package com.itwill.jpa.controller.member_information;

import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.repository.member_information.MemberRepository;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.member_information.MemtorBoardService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("/mentorboard")
public class MentorBoardController {
	@Autowired
    private MemtorBoardService mentorBoardService;
	
	private MemberRepository memberRepository;
   

    /* 멘토 보드 등록 */
    @Operation(summary = "멘토 보드 등록")
    @PostMapping
    public ResponseEntity<Response> createMentorBoard(@RequestBody MentorBoardDto mentorBoardDto) {
        MentorBoardDto createdBoard = mentorBoardService.savememtorboard(mentorBoardDto);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.CREATED_REPORT_SUCCESS);
        response.setMessage(ResponseMessage.CREATED_REPORT_SUCCESS);
        response.setData(createdBoard);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    /* 멘토 보드 수정 */
    @Operation(summary = "멘토 보드 수정")
    @PutMapping("/{mentorBoardNo}")
    public ResponseEntity<Response> updateMentorBoard(
            @PathVariable Long mentorBoardNo,
            @RequestBody MentorBoardDto mentorBoardDto) throws Exception {
        mentorBoardDto.setMentorBoardNo(mentorBoardNo);
        MentorBoardDto updatedBoard = mentorBoardService.updatememtorboard(mentorBoardDto);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.UPDATE_REPORT_SUCCESS);
        response.setMessage(ResponseMessage.UPDATE_REPORT_SUCCESS);
        response.setData(updatedBoard);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    /* 멘토 보드 삭제 */
    @Operation(summary = "멘토 보드 삭제")
    @DeleteMapping("/{mentorBoardNo}")
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
        response.setStatus(ResponseStatusCode.READ_REPORT_SUCCESS);
        response.setMessage(ResponseMessage.READ_REPORT_SUCCESS);
        response.setData(mentorBoard);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    /* 특정 멤버의 모든 멘토 보드 조회 */
    @Operation(summary = "특정 멤버의 모든 멘토 보드 조회")
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
