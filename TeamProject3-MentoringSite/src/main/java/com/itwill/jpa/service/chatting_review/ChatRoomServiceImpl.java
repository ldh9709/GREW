package com.itwill.jpa.service.chatting_review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.chatting_review.ChatRoomDto;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.repository.chatting_review.MentoringRequestRepository;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
	@Autowired
	private MentoringRequestRepository mentoringRequestRepository;
	
	/*활동 요청(기본 상태 요청 중)*/
	@Override
	public ChatRoom saveMentoringRequest(ChatRoomDto mentoringRequestDto) {
		ChatRoom mentoringRequest = ChatRoom.toEntity(mentoringRequestDto);
		return mentoringRequestRepository.save(mentoringRequest);
	}
	/*활동 상태 확인*/
	@Override
	public ChatRoomDto getMentoringRequest(Long requestNo) {
		ChatRoom mentoringRequest = mentoringRequestRepository.findById(requestNo).get();
		ChatRoomDto mentoringRequestDto = ChatRoomDto.toDto(mentoringRequest);
		return mentoringRequestDto;
	}
	/*활동 진행중*/
	@Override
	public ChatRoom updateActive(Long requestNo) throws Exception {
		if (mentoringRequestRepository.findById(requestNo).isPresent()) {
			ChatRoom mentoringRequest = mentoringRequestRepository.findById(requestNo).get();
			mentoringRequest.setChatRoomStatus(7100);
			return mentoringRequestRepository.save(mentoringRequest);
		}
		return new ChatRoom();
	}
	/*활동 완료*/
	@Override
	public ChatRoom updateCompleted(Long requestNo) throws Exception {
		if (mentoringRequestRepository.findById(requestNo).isPresent()) {
			ChatRoom mentoringRequest = mentoringRequestRepository.findById(requestNo).get();
			mentoringRequest.setChatRoomStatus(7200);
			mentoringRequest.setChatRoomEndDate(LocalDateTime.now());
			return mentoringRequestRepository.save(mentoringRequest);
		}
		return new ChatRoom();
	}
	/*요청 거절*/
	@Override
	public ChatRoom updateRejected(Long requestNo) throws Exception {
		if (mentoringRequestRepository.findById(requestNo).isPresent()) {
			ChatRoom mentoringRequest = mentoringRequestRepository.findById(requestNo).get();
			mentoringRequest.setChatRoomStatus(7300);
			mentoringRequest.setChatRoomEndDate(LocalDateTime.now());
			return mentoringRequestRepository.save(mentoringRequest);
		}
		return new ChatRoom();
	}
	/*요청 취소*/
	@Override
	public ChatRoom updateCanceled(Long requestNo) throws Exception {
		if (mentoringRequestRepository.findById(requestNo).isPresent()) {
			ChatRoom mentoringRequest = mentoringRequestRepository.findById(requestNo).get();
			mentoringRequest.setChatRoomStatus(7400);
			mentoringRequest.setChatRoomEndDate(LocalDateTime.now());
			return mentoringRequestRepository.save(mentoringRequest);
		}
		return new ChatRoom();
	}
	/*강제 종료*/
	@Override
	public ChatRoom updateForceClosed(Long requestNo) throws Exception {
		if (mentoringRequestRepository.findById(requestNo).isPresent()) {
			ChatRoom mentoringRequest = mentoringRequestRepository.findById(requestNo).get();
			mentoringRequest.setChatRoomStatus(7500);
			mentoringRequest.setChatRoomEndDate(LocalDateTime.now());
			return mentoringRequestRepository.save(mentoringRequest);
		}
		return new ChatRoom();
	}
	/*본인 활동 리스트 출력*/
	@Override
	public List<ChatRoomDto> selectMentoringRequestAll() {
		List<ChatRoom> mentoringRequests = mentoringRequestRepository.findAll();
		List<ChatRoomDto> mentoringRequestDtos = new ArrayList<ChatRoomDto>();
		for (int i = 0; i <mentoringRequests.size(); i++) {
			mentoringRequestDtos.add(ChatRoomDto.toDto(mentoringRequests.get(i)));
		}
		return mentoringRequestDtos;
	}
}
