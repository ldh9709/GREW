package com.itwill.jpa.controller.chatting_review;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.chatting_review.ChatMessageDto;
import com.itwill.jpa.dto.chatting_review.ChatMessageImageDto;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.chatting_review.ChatMessageImageService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/chatmessageimage")
public class ChatMessageImageRestController {
	
	@Autowired
	private ChatMessageImageService chatMessageImageService;
	
	@Operation(summary="이미지 등록")
	@PostMapping
	public ResponseEntity<Response> createMessageImage(@RequestBody ChatMessageImageDto chatMessageImageDto){
		Response response = new Response();
		
		ChatMessageImageDto chatMessageImage =  chatMessageImageService.createImage(chatMessageImageDto);
		response.setStatus(ResponseStatusCode.SEND_CHATTING_SUCCESS);
		response.setMessage(ResponseMessage.SEND_CHATTING_SUCCESS);
		response.setData(chatMessageImage);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	
	
}
