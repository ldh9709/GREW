package com.itwill.jpa.controller.chatting_review;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.chatting_review.ChatMessageDto;
import com.itwill.jpa.dto.chatting_review.ChatMessageImageDto;
import com.itwill.jpa.entity.chatting_review.ChatMessage;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.chatting_review.ChatMessageImageService;
import com.itwill.jpa.service.chatting_review.ChatMessageService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/chatmessage")
public class ChatMessageRestController {

	@Autowired
	private ChatMessageService chatMessageService;
	@Autowired
	private ChatMessageImageService chatMessageImageService;
	
	@Operation(summary="메시지 등록")
	@PostMapping
	public ResponseEntity<Response> saveMessage(@RequestBody ChatMessageDto chatMessageDto){
		Response response = new Response();
		ChatMessageDto chatMessage =  ChatMessageDto.toDto(chatMessageService.createChatMessage(chatMessageDto));
		response.setStatus(ResponseStatusCode.SEND_CHATTING_SUCCESS);
		response.setMessage(ResponseMessage.SEND_CHATTING_SUCCESS);
		response.setData(chatMessage);
		chatMessage.setChatRoomNo(chatMessageDto.getChatRoomNo());
		chatMessage.setMemberNo(chatMessageDto.getMemberNo());
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	@Operation(summary="읽음 상태 변경")
	@PutMapping("/update/{chatmessageNo}")
	public ResponseEntity<Response> updateChatMessageCheck(@PathVariable(name="chatmessageNo") Long chatmessageNo){
		Response response = new Response();
		ChatMessageDto chatMessage =  ChatMessageDto.toDto(chatMessageService.updateChatMessageCheck(chatmessageNo));
		response.setStatus(ResponseStatusCode.READ_MESSAGE);
		response.setMessage(ResponseMessage.READ_MESSAGE);
		response.setData(chatMessage);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	@Operation(summary="특정 메시지 선택")
	@PutMapping("/{chatmessageNo}")
	public ResponseEntity<Response> getChatMessageByChatMessageNo(@PathVariable(name="chatmessageNo") Long chatmessageNo){
		Response response = new Response();
		ChatMessageDto chatMessage =  ChatMessageDto.toDto(chatMessageService.getChatMessageByNo(chatmessageNo));
		response.setStatus(ResponseStatusCode.CHATTING_LIST_SUCCESS);
		response.setMessage(ResponseMessage.CHATTING_LIST_SUCCESS);
		
		response.setData(chatMessage);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);
		
		return responseEntity;
		
	}
	@GetMapping("count/message")
	@Operation(summary = "안읽은메시지갯수(채팅방)")
	public ResponseEntity<Response> getMessageCount(@RequestParam(name = "chatRoomNo")Long chatRoomNo){
		Response response = new Response();
		int count = chatMessageService.countChatMessageIsRead(chatRoomNo);
		response.setStatus(ResponseStatusCode.CHATTING_LIST_SUCCESS);
		response.setMessage(ResponseMessage.CHATTING_LIST_SUCCESS);
		response.setData(count);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);
		
		return responseEntity;
	}
	
}
