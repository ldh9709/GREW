package com.itwill.jpa.controller.chatting_review;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.auth.PrincipalDetails;
import com.itwill.jpa.dto.chatting_review.ChatMessageDto;
import com.itwill.jpa.dto.chatting_review.ChatRoomDto;
import com.itwill.jpa.dto.chatting_review.ChatRoomStatusDto;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.chatting_review.ChatRoomService;
import com.itwill.jpa.service.chatting_review.ChatRoomStatusService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/chatroom/")
public class ChatRoomRestController {
	@Autowired
	private ChatRoomService chatRoomService;
	@Autowired
	private ChatRoomStatusService chatRoomStatusService;
	
	@Operation(summary = "채팅방 신청")
	@PostMapping
	public ResponseEntity<Response> createInitialChatRoom(@RequestBody ChatRoomDto chatRoomDto){
		chatRoomService.saveChatRoom(chatRoomDto);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.SEND_CHATTING_SUCCESS);
		response.setMessage(ResponseMessage.SEND_CHATTING_SUCCESS);
		response.setData(chatRoomDto);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	@Operation(summary = "채팅방 활성화")
	@PutMapping("{chat_room_no}/active")
	public ResponseEntity<Response> updateChatRoomStatusACTIVE(@PathVariable (value = "chat_room_no") Long chatRoomNo) throws Exception{
		ChatRoomDto chatRoomDto = chatRoomService.updateActive(chatRoomNo);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.ACTIVE_CHATTING);
		response.setMessage(ResponseMessage.ACTIVE_CHATTING);
		response.setData(chatRoomDto);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	@Operation(summary = "활동 종료")
	@PutMapping("{chat_room_no}/completed")
	public ResponseEntity<Response> updateChatRoomStatusCOMPLETED(@PathVariable (value = "chat_room_no") Long chatRoomNo) throws Exception{
		ChatRoomDto chatRoomDto = chatRoomService.updateCompleted(chatRoomNo);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.COMPLETED_CHATTING);
		response.setMessage(ResponseMessage.COMPLETED_CHATTING);
		response.setData(chatRoomDto);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	@Operation(summary = "멘토가 요청을 수락하지 않음")
	@PutMapping("{chat_room_no}/rejected")
	public ResponseEntity<Response> updateChatRoomStatusREJECTED(@PathVariable (value = "chat_room_no") Long chatRoomNo) throws Exception{
		ChatRoomDto chatRoomDto = chatRoomService.updateRejected(chatRoomNo);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.REJECTED_CHATTING);
		response.setMessage(ResponseMessage.REJECTED_CHATTING);
		response.setData(chatRoomDto);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	@Operation(summary = "멘티가 요청을 철회함")
	@PutMapping("{chat_room_no}/canceled")
	public ResponseEntity<Response> updateChatRoomStatusCANCELED(@PathVariable (value = "chat_room_no") Long chatRoomNo) throws Exception{
		ChatRoomDto chatRoomDto = chatRoomService.updateCanceled(chatRoomNo);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CANCELED_CHATTING_FAIL);
		response.setMessage(ResponseMessage.CANCELED_CHATTING_FAIL);
		response.setData(chatRoomDto);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	@Operation(summary = "관리자가 비정상적인 요청을 종료함")
	@PutMapping("{chat_room_no}/closed")
	public ResponseEntity<Response> updateChatRoomStatusCLOSED(@PathVariable (value = "chat_room_no") Long chatRoomNo) throws Exception{
		ChatRoomDto chatRoomDto = chatRoomService.updateForceClosed(chatRoomNo);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.FORCE_CLOSED_CHATTING);
		response.setMessage(ResponseMessage.FORCE_CLOSED_CHATTING);
		response.setData(chatRoomDto);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	@Operation(summary = "채팅방을 나감")
	@PutMapping("{chat_room_no}/leave/{member_no}")
	public ResponseEntity<Response> updateChatRoomStatusLEAVE(@PathVariable (value = "chat_room_no") Long chatRoomNo, @PathVariable (value = "member_no") Long memberNo) throws Exception{
		ChatRoomStatusDto chatRoomStatusDto = chatRoomStatusService.updateChatRoomStatus(chatRoomNo, memberNo);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.LEAVE_CHATTING);
		response.setMessage(ResponseMessage.LEAVE_CHATTING);
		response.setData(chatRoomStatusDto);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	/*
	@Operation(summary = "채팅방 리스트")
	@GetMapping("/{member_no}")
	public ResponseEntity<Response> selectChatRoomList(@PathVariable (value = "member_no") Long memberNo){
		List<ChatRoomDto> chatRoomDtos = chatRoomService.selectChatRoomAll(memberNo);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CHATTING_LIST_SUCCESS);
		response.setMessage(ResponseMessage.CHATTING_LIST_SUCCESS);
		response.setData(chatRoomDtos);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	*/
	@Operation(summary = "채팅방 리스트(토큰)")
	@SecurityRequirement(name = "BearerAuth")//API 엔드포인트가 인증을 요구한다는 것을 문서화(Swagger에서 JWT인증을 명시
	@PreAuthorize("hasRole('MENTEE') or hasRole('MENTOR')")//ROLE이 MENTEE인 사람만 접근 가능
	@GetMapping("/list")
	public ResponseEntity<Response> selectChatRoomList(Authentication authentication){
		//PrincipalDetails에서 memberNo를 가져옴
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		Long memberNo = principalDetails.getMemberNo();
		
		List<ChatRoomDto> chatRoomDtos = chatRoomService.selectChatRoomAll(memberNo);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CHATTING_LIST_SUCCESS);
		response.setMessage(ResponseMessage.CHATTING_LIST_SUCCESS);
		response.setData(chatRoomDtos);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	@Operation(summary = "채팅방 대화 목록")
	@GetMapping("/messages/{chat_room_no}")
	public ResponseEntity<Response> selectChatMessagesList(@PathVariable (value = "chat_room_no") Long chatRoomNo){
		List<ChatMessageDto> chatMessageDtos = chatRoomService.selectChatMessages(chatRoomNo);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CHATTING_MESSAGE);
		response.setMessage(ResponseMessage.CHATTING_MESSAGE);
		response.setData(chatMessageDtos);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	@Operation(summary = "채팅방 제목 변경")
	@PutMapping("/name/{chat_room_no}, {member_no}, {chat_room_name}")
	public ResponseEntity<Response> updateChatRoomName(@PathVariable (value = "chat_room_no") Long chatRoomNo,@PathVariable (value = "member_no") Long memberNo,@PathVariable (value = "chat_room_name") String chatRoomName){
		ChatRoomStatusDto chatRoomStatusDto2 = chatRoomStatusService.updateChatRoomName(chatRoomNo, memberNo, chatRoomName);
		
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CHATTING_NAME_CHANGE);
		response.setMessage(ResponseMessage.CHATTING_NAME_CHANGE);
		response.setData(chatRoomStatusDto2);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = 
				new ResponseEntity<Response>(response,httpHeaders, HttpStatus.OK);
		
		return responseEntity;
	}
	
	
}
