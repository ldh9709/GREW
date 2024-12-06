package com.itwill.jpa.service.chatting_review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.chatting_review.ChatRoomDto;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.chatting_review.ChatRoomStatus;
import com.itwill.jpa.repository.chatting_review.ChatRoomRepository;
import com.itwill.jpa.repository.chatting_review.ChatRoomStatusRepository;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
	@Autowired
	private ChatRoomRepository chatRoomRepository;
	@Autowired
	private ChatRoomStatusService chatRoomStatusService;
	
	/*활동 상태 확인*/
	@Override
	public ChatRoomDto getChatRoom(Long chatRoomNo) {
		ChatRoom mentoringRequest = chatRoomRepository.findById(chatRoomNo).get();
		ChatRoomDto mentoringRequestDto = ChatRoomDto.toDto(mentoringRequest);
		return mentoringRequestDto;
	}
	/*활동 요청(기본 상태 요청 중)*/
	@Override
	public void saveChatRoom(ChatRoomDto mentoringRequestDto) {
		ChatRoom mentoringRequest = ChatRoom.toEntity(mentoringRequestDto);
		chatRoomRepository.save(mentoringRequest);
	}
	/*활동 진행중*/
	@Override
	public ChatRoom updateActive(Long chatRoomNo) throws Exception {
		if (chatRoomRepository.findById(chatRoomNo).isPresent()) {
			ChatRoom mentoringRequest = chatRoomRepository.findById(chatRoomNo).get();
			mentoringRequest.setChatRoomStatus(7100);
			return chatRoomRepository.save(mentoringRequest);
		}
		return new ChatRoom();
	}
	/*활동 완료*/
	@Override
	public ChatRoom updateCompleted(Long chatRoomNo) throws Exception {
		if (chatRoomRepository.findById(chatRoomNo).isPresent()) {
			ChatRoom chatRoom = chatRoomRepository.findById(chatRoomNo).get();
			chatRoom.setChatRoomStatus(7200);
			chatRoom.setChatRoomEndDate(LocalDateTime.now());
			ChatRoomStatus mentorChatRoomStatus = ChatRoomStatus.builder()
															    .chatRoom(chatRoom)
															    .member(chatRoom.getMentor())
															    .build();
			ChatRoomStatus menteeChatRoomStatus = ChatRoomStatus.builder()
																.chatRoom(chatRoom)
															    .member(chatRoom.getMentor())
															    .build();
			chatRoomStatusService.saveChatRoomStatus(mentorChatRoomStatus, menteeChatRoomStatus);
			return chatRoomRepository.save(chatRoom);
		}
		return new ChatRoom();
	}
	/*요청 거절*/
	@Override
	public ChatRoom updateRejected(Long chatRoomNo) throws Exception {
		if (chatRoomRepository.findById(chatRoomNo).isPresent()) {
			ChatRoom mentoringRequest = chatRoomRepository.findById(chatRoomNo).get();
			mentoringRequest.setChatRoomStatus(7300);
			mentoringRequest.setChatRoomEndDate(LocalDateTime.now());
			return chatRoomRepository.save(mentoringRequest);
		}
		return new ChatRoom();
	}
	/*요청 취소*/
	@Override
	public ChatRoom updateCanceled(Long chatRoomNo) throws Exception {
		if (chatRoomRepository.findById(chatRoomNo).isPresent()) {
			ChatRoom mentoringRequest = chatRoomRepository.findById(chatRoomNo).get();
			mentoringRequest.setChatRoomStatus(7400);
			mentoringRequest.setChatRoomEndDate(LocalDateTime.now());
			return chatRoomRepository.save(mentoringRequest);
		}
		return new ChatRoom();
	}
	/*강제 종료*/
	@Override
	public ChatRoom updateForceClosed(Long chatRoomNo) throws Exception {
		if (chatRoomRepository.findById(chatRoomNo).isPresent()) {
			ChatRoom mentoringRequest = chatRoomRepository.findById(chatRoomNo).get();
			mentoringRequest.setChatRoomStatus(7500);
			mentoringRequest.setChatRoomEndDate(LocalDateTime.now());
			return chatRoomRepository.save(mentoringRequest);
		}
		return new ChatRoom();
	}
	/*본인 활동 리스트 출력*/
	@Override
	public List<ChatRoomDto> selectChatRoomAll() {
		List<ChatRoom> mentoringRequests = chatRoomRepository.findAll();
		List<ChatRoomDto> mentoringRequestDtos = new ArrayList<ChatRoomDto>();
		for (int i = 0; i <mentoringRequests.size(); i++) {
			mentoringRequestDtos.add(ChatRoomDto.toDto(mentoringRequests.get(i)));
		}
		return mentoringRequestDtos;
	}
}
