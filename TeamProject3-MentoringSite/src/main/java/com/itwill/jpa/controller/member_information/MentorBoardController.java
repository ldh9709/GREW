package com.itwill.jpa.controller.member_information;

import com.itwill.jpa.auth.PrincipalDetails;
import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.entity.member_information.Follow;
import com.itwill.jpa.entity.member_information.MentorBoard;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.alarm.AlarmService;
import com.itwill.jpa.service.member_information.MentorBoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.net.Authenticator;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/mentor-board")
public class MentorBoardController {

    @Autowired
    private MentorBoardService mentorBoardService;
    @Autowired
    private AlarmService alarmService;
    
    
    
    
    @Operation(summary = "멘토 보드 리스트")
    @GetMapping("/sorted/{status}")
    public ResponseEntity<Response> getMentorBoardList(
            @PathVariable(name = "status") int status, // 경로 변수로 변경
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Page<MentorBoardDto> mentorBoards = mentorBoardService.getMentorBoardsSortedByDate(status, page, size);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MEMBER_LIST_SUCCESS);
        response.setMessage(ResponseMessage.READ_MEMBER_LIST_SUCCESS);
        response.setData(mentorBoards);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return ResponseEntity.ok().headers(headers).body(response);
    }

    
    
    
    /* 멘토 보드 등록 */
    @Operation(summary = "멘토 보드 등록")
    @PostMapping
    public ResponseEntity<Response> createMentorBoard(@RequestBody MentorBoardDto mentorBoardDto) {
    	MentorBoardDto savedBoard = mentorBoardService.saveMemtorBoard(mentorBoardDto);
    	List<AlarmDto> saveAlarms = alarmService.createAlarmsByMentorBoard(savedBoard);
        Response response = new Response();
        response.setStatus(ResponseStatusCode.CREATED_MENTOR_BOARD_SUCCESS);
        response.setMessage(ResponseMessage.CREATED_MENTOR_BOARD_SUCCESS);
        response.setData(savedBoard);
        response.setAddData(saveAlarms);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    /* 멘토 보드 수정 */
    @Operation(summary = "멘토 보드 수정")
    @PutMapping("/{mentorBoardNo}")
    public ResponseEntity<Response> updateMentorBoard(
            @PathVariable(name = "mentorBoardNo") Long mentorBoardId, 
            @RequestBody MentorBoardDto mentorBoardDto
        ) throws Exception {

        // mentorBoardId를 DTO에 설정 (필요한 경우)
        mentorBoardDto.setMentorBoardNo(mentorBoardId); 

        // 기존 멘토 보드 수정
        MentorBoardDto updatedBoard = mentorBoardService.updateMemtorBoard(mentorBoardDto);

        // 응답 생성
        Response response = new Response();
        response.setStatus(ResponseStatusCode.UPDATE_MENTOR_BOARD_SUCCESS);
        response.setMessage(ResponseMessage.UPDATE_MENTOR_BOARD_SUCCESS);
        response.setData(updatedBoard);
        
        // 응답 헤더 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
        
        ResponseEntity<Response> responseEntity = new ResponseEntity<>(response, httpHeaders, HttpStatus.CREATED);
        return responseEntity;
    }

    /* 멘토 보드 삭제(상태 변경, PUT 방식) */
    @Operation(summary = "멘토 보드 삭제(상태 변경, PUT 방식)")
    @PutMapping("/{mentorBoardNo}/status")
    public ResponseEntity<Response> deleteMentorBoard(@PathVariable(name= "mentorBoardNo")Long mentorBoardNo) throws Exception {
        MentorBoardDto deletedBoard = mentorBoardService.deleteMemtorBoard(
                MentorBoardDto.builder().mentorBoardNo(mentorBoardNo).build()
        );

        Response response = new Response();
        response.setStatus(ResponseStatusCode.DELETE_MENTOR_BOARD_SUCCESS);
        response.setMessage(ResponseMessage.DELETE_MENTOR_BOARD_SUCCESS);
        response.setData(deletedBoard);

        HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.OK);
		
		
		return responseEntity;
    }
    /* 멘토 보드 상세 조회 */
    @Operation(summary = "멘토 보드 상세 조회")
    @GetMapping("/{mentorBoardNo}")
    public ResponseEntity<Response> getMentorBoard(@PathVariable(name= "mentorBoardNo") Long mentorBoardNo) {
        MentorBoardDto mentorBoard = mentorBoardService.getMemtorBoard(mentorBoardNo);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MENTOR_BOARD_SUCCESS);
        response.setMessage(ResponseMessage.READ_MENTOR_BOARD_SUCCESS);
        response.setData(mentorBoard);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        
        return  responseEntity ;
    }

    
    /* 멘토 보드 조회수 증가 */
    @Operation(summary = "멘토 보드 조회수 증가")
    @PutMapping("/{mentorBoardNo}/views")
    public ResponseEntity<Response> increaseViewMentorBoard(@PathVariable(name = "mentorBoardNo") Long mentorBoardNo) throws Exception {
        MentorBoardDto updatedBoard = mentorBoardService.increaseViewMentorBoard(
                MentorBoardDto.builder().mentorBoardNo(mentorBoardNo).build()
        );

        Response response = new Response();
        response.setStatus(ResponseStatusCode.UPDATE_MEMBER_SUCCESS);
        response.setMessage(ResponseMessage.UPDATE_MEMBER_SUCCESS);
        response.setData(updatedBoard);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response, headers, HttpStatus.OK);
        
        return  responseEntity ;
    }

    
    
    /* 조회수 기준 정렬 페이징 */
    @Operation(summary = "멘토 보드 조회수 기준 페이징")
    @GetMapping("/sorted/views")
    public ResponseEntity<Response> getMentorBoardsSortedByViews(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Page<MentorBoardDto> sortedBoards = mentorBoardService.findByMentorBoardOrderByView(page, size);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MEMBER_LIST_SUCCESS);
        response.setMessage(ResponseMessage.READ_MEMBER_LIST_SUCCESS);
        response.setData(sortedBoards);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    
    
    /* 검색 기능 페이징 */
    @Operation(summary = "멘토 보드 검색 페이징")
    @GetMapping("/search")
    public ResponseEntity<Response> searchMentorBoards(
            @RequestParam(name = "query") String query,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Page<MentorBoardDto> searchedBoards = mentorBoardService.findMentorBoardBySearch(query, page, size);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MEMBER_LIST_SUCCESS);
        response.setMessage(ResponseMessage.READ_MEMBER_LIST_SUCCESS);
        response.setData(searchedBoards);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    
    
    /* 날짜 기준 정렬 페이징 */
    @Operation(summary = "멘토 보드 날짜 기준 페이징")
    @GetMapping("/sorted/date/other")
    public ResponseEntity<Response> getMentorBoardsSortedByDate(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Page<MentorBoardDto> sortedBoards = mentorBoardService.getMentorBoardsSortedByDate(page, size);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MEMBER_LIST_SUCCESS);
        response.setMessage(ResponseMessage.READ_MEMBER_LIST_SUCCESS);
        response.setData(sortedBoards);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    
    
    /* 특정 사용자와 관련된 게시글 조회 페이징 */
    @Operation(summary = "특정 사용자와 관련된 게시글 조회 페이징")
    @SecurityRequirement(name = "BearerAuth")//API 엔드포인트가 인증을 요구한다는 것을 문서화(Swagger에서 JWT인증을 명시
	@PreAuthorize("hasRole('MENTEE') or hasRole('MENTOR')")//ROLE이 MENTEE인 사람만 접근 가능
    @GetMapping("/list/member")
    public ResponseEntity<Response> getMentorBoardsByMember(
    		Authentication authentication,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
    	
    	PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
    	Long memberNo = principalDetails.getMemberNo();
    	
        Page<MentorBoardDto> mentorBoards = mentorBoardService.findByMember(memberNo, page, size);

        Response response = new Response();
        response.setStatus(ResponseStatusCode.READ_MEMBER_LIST_SUCCESS);
        response.setMessage(ResponseMessage.READ_MEMBER_LIST_SUCCESS);
        response.setData(mentorBoards);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
    
    
    
    
 // **이미지 업로드 엔드포인트**
    @PostMapping("/{mentorBoardNo}/upload-image")
    public ResponseEntity<Response> uploadImage(
        @PathVariable("mentorBoardNo") Long mentorBoardNo, 
        @RequestParam("file") MultipartFile file) {
        try {
            // 📢 서비스 호출 후, 이미지 URL 받기
            String imageUrl = mentorBoardService.uploadImage(mentorBoardNo, file);
            
            // 📢 클라이언트에 반환할 응답 생성
            Response response = new Response();
            response.setStatus(ResponseStatusCode.IMAGE_UPLOAD_SUCCESS);
            response.setMessage(ResponseMessage.IMAGE_UPLOAD_SUCCESS);
            response.setData(imageUrl); // **업로드된 이미지 URL 반환**
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            Response response = new Response();
            response.setStatus(ResponseStatusCode.IMAGE_UPLOAD_FAIL);
            response.setMessage("이미지 업로드 실패: " + e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // **이미지 URL 가져오기 엔드포인트**
    @GetMapping("/{mentorBoardNo}/image-url")
    public ResponseEntity<String> getImageUrl(@PathVariable("mentorBoardNo") Long mentorBoardNo) {
        try {
            String imageUrl = mentorBoardService.getImageUrl(mentorBoardNo);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("이미지 URL 조회 실패: " + e.getMessage());
        }
    }
}
    
    

    
    
    
   