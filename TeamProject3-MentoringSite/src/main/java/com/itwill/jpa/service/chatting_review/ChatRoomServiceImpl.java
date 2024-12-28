package com.itwill.jpa.service.chatting_review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.chatting_review.ChatMessageDto;
import com.itwill.jpa.dto.chatting_review.ChatRoomDto;
import com.itwill.jpa.entity.chatting_review.ChatMessage;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.chatting_review.ChatRoomStatus;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.exception.CustomException;
import com.itwill.jpa.repository.chatting_review.ChatRoomRepository;
import com.itwill.jpa.repository.chatting_review.ChatRoomStatusRepository;
import com.itwill.jpa.repository.member_information.MemberRepository;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.member_information.MentorProfileService;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
	@Autowired
	private ChatRoomRepository chatRoomRepository;
	@Autowired
	private ChatRoomStatusService chatRoomStatusService;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private MentorProfileService mentorProfileService;
	
	/*활동 상태 확인*/
	@Override
	public ChatRoomDto getChatRoom(Long chatRoomNo) {
		try {
			ChatRoom mentoringRequest = chatRoomRepository.findById(chatRoomNo).get();
			ChatRoomDto mentoringRequestDto = ChatRoomDto.toDto(mentoringRequest);
			return mentoringRequestDto;
		}catch (Exception e) {
			throw new CustomException(ResponseStatusCode.SEND_CHATTING_FAIL, ResponseMessage.SEND_CHATTING_FAIL, e);
		}
	}
	/*활동 요청(기본 상태 요청 중)*/
	@Override
	public ChatRoomDto saveChatRoom(Long menteeNo, Long mentorNo) {
		try {
			ChatRoom chatRoom1 = chatRoomRepository.findByMenteeAndMentor(menteeNo, mentorNo);
			if(chatRoom1 == null) {
				System.out.println("---------------------------------------------------------------존재하지않음---------------------------------------------------------------");
				ChatRoomDto chatRoomDto = ChatRoomDto.builder()
													 .chatRoomStatus(7000)
													 .chatRoomStartDate(LocalDateTime.now())
													 .chatRoomEndDate(null)
													 .menteeNo(menteeNo)
													 .mentorNo(mentorNo)
													 .build();
				ChatRoom mentoringRequest = ChatRoom.toEntity(chatRoomDto);
				ChatRoom chatRoom = chatRoomRepository.save(mentoringRequest);
				Member mentor = memberRepository.findById(mentorNo).orElse(null);
				Member mentee = memberRepository.findById(menteeNo).orElse(null);
				chatRoom.setMentor(mentor);
				chatRoom.setMentee(mentee);
				ChatRoomStatus mentorChatRoomStatus = ChatRoomStatus.builder()
						.chatRoomName(mentee.getMemberName()+"님에게서 채팅방 신청이 도착했습니다.")
						.chatRoomStatus(7500)
						.chatRoom(chatRoom)
						.member(mentor)
						.build();
				ChatRoomStatus menteeChatRoomStatus = ChatRoomStatus.builder()
						.chatRoomName(mentor.getMemberName()+"님에게 채팅방을 신청했습니다.")
						.chatRoomStatus(7500)
						.chatRoom(chatRoom)
						.member(mentee)
						.build();
				chatRoomStatusService.saveFirstChatRoomStatus(mentorChatRoomStatus, menteeChatRoomStatus);
				return ChatRoomDto.toDto(chatRoom);
			} else {
				System.out.println("---------------------------------------------------------------존재함---------------------------------------------------------------");
				if (chatRoom1.getChatRoomStatus() == 7500) {
					return ChatRoomDto.toDto(chatRoom1);
				}else {
					chatRoom1.setChatRoomStatus(7000);
					chatRoom1.setChatRoomEndDate(null);
					ChatRoom chatRoom2 = chatRoomRepository.save(chatRoom1);
					chatRoomStatusService.ResetChatRoomStatus(chatRoom2);
					return ChatRoomDto.toDto(chatRoom2);
				}
				
			}
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.PENDING_CHATTING_FAIE, ResponseMessage.PENDING_CHATTING_FAIE, e);
		}
	}
	/*활동 진행중*/
	@Override
	public ChatRoomDto updateActive(Long chatRoomNo) throws Exception {
		try {
			if (chatRoomRepository.findById(chatRoomNo).isPresent() && chatRoomRepository.findById(chatRoomNo).get().getChatRoomStatus() == 7000) {
				ChatRoom chatRoom = chatRoomRepository.findById(chatRoomNo).get();
				chatRoomStatusService.updateChatRoomStatus(chatRoomNo);
				chatRoom.setChatRoomStatus(7100);
				ChatRoom ChatRoom = chatRoomRepository.save(chatRoom);
				Member mentor = chatRoom.getMentor();
				/* 멘토 멘토링 수 업데이트 */
				mentorProfileService.updateMentoringCount(mentor.getMemberNo());
				return ChatRoomDto.toDto(ChatRoom);
			}
			return new ChatRoomDto();
		}catch (Exception e) {
			throw new CustomException(ResponseStatusCode.ACTIVE_CHATTING_FAIE, ResponseMessage.ACTIVE_CHATTING_FAIE, e);
		}
		
	}
	/*활동 완료*/
	@Override
	public ChatRoomDto updateCompleted(Long chatRoomNo) throws Exception {
		try {
			if (chatRoomRepository.findById(chatRoomNo).isPresent()) {
				ChatRoom chatRoom = chatRoomRepository.findById(chatRoomNo).get();
				chatRoom.setChatRoomStatus(7200);
				chatRoom.setChatRoomEndDate(LocalDateTime.now());
				ChatRoom ChatRoom = chatRoomRepository.save(chatRoom);
				/* 멘토 멘토링 완료 수 업데이트 */
				mentorProfileService.updateAcitityCount(chatRoom.getMentor().getMemberNo());
				return ChatRoomDto.toDto(ChatRoom);
			}
			return new ChatRoomDto();
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.CHATTING_STATUS_CHANGE_FAIE, ResponseMessage.CHATTING_STATUS_CHANGE_FAIE, e);
		}
		
	}
	/*요청 거절*/
	@Override
	public ChatRoomDto updateRejected(Long chatRoomNo) throws Exception {
		try {
			if (chatRoomRepository.findById(chatRoomNo).isPresent()) {
				ChatRoom mentoringRequest = chatRoomRepository.findById(chatRoomNo).get();
				mentoringRequest.setChatRoomStatus(7300);
				mentoringRequest.setChatRoomEndDate(LocalDateTime.now());
				ChatRoom ChatRoom = chatRoomRepository.save(mentoringRequest);
				return ChatRoomDto.toDto(ChatRoom);
			}
			return new ChatRoomDto();
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.CHATTING_STATUS_CHANGE_FAIE, ResponseMessage.CHATTING_STATUS_CHANGE_FAIE, e);
		}
	}
	/*요청 취소*/
	@Override
	public ChatRoomDto updateCanceled(Long chatRoomNo) throws Exception {
		try {
			if (chatRoomRepository.findById(chatRoomNo).isPresent()) {
				ChatRoom mentoringRequest = chatRoomRepository.findById(chatRoomNo).get();
				mentoringRequest.setChatRoomStatus(7400);
				mentoringRequest.setChatRoomEndDate(LocalDateTime.now());
				ChatRoom ChatRoom = chatRoomRepository.save(mentoringRequest);
				return ChatRoomDto.toDto(ChatRoom);
			}	
			return new ChatRoomDto();
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.CHATTING_STATUS_CHANGE_FAIE, ResponseMessage.CHATTING_STATUS_CHANGE_FAIE, e);
		}
	}
	/*강제 종료*/
	@Override
	public ChatRoomDto updateForceClosed(Long chatRoomNo) throws Exception {
		try {
			if (chatRoomRepository.findById(chatRoomNo).isPresent()) {
				ChatRoom mentoringRequest = chatRoomRepository.findById(chatRoomNo).get();
				mentoringRequest.setChatRoomStatus(7500);
				mentoringRequest.setChatRoomEndDate(LocalDateTime.now());
				ChatRoom ChatRoom = chatRoomRepository.save(mentoringRequest);
				return ChatRoomDto.toDto(ChatRoom);
			}
			return new ChatRoomDto();
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.CHATTING_STATUS_CHANGE_FAIE, ResponseMessage.CHATTING_STATUS_CHANGE_FAIE, e);
		}
		
	}
	/*본인 활동 리스트 출력(멘티)*/
	@Override
	public Page<ChatRoomDto> selectChatRoomByMenteeNo(Long memberNo, int pageNumber, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<ChatRoom> chatRooms = chatRoomRepository.findByMenteeNo(memberNo,pageable);
			List<ChatRoomDto> menteeChatRoomDtos = new ArrayList<>();
			
			for (ChatRoom chatRoom : chatRooms) {
				if(chatRoom.getMentee().getMemberNo().equals(memberNo)) {
					menteeChatRoomDtos.add(ChatRoomDto.toDto(chatRoom));
				}
			}
			return new PageImpl<>(menteeChatRoomDtos, pageable, chatRooms.getTotalElements());
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.CHATTING_LIST_FAIE, ResponseMessage.CHATTING_LIST_FAIE, e);
		}
	}
		
	/*본인 활동 리스트 출력(멘토)*/
	@Override
	public Page<ChatRoomDto> selectChatRoomByMentorNo(Long memberNo, int pageNumber, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<ChatRoom> chatRooms = chatRoomRepository.findByMentorNo(memberNo,pageable);
			List<ChatRoomDto> mentorChatRoomDtos = new ArrayList<>();
			
			for (ChatRoom chatRoom : chatRooms) {
				if(chatRoom.getMentor().getMemberNo().equals(memberNo)) {
					mentorChatRoomDtos.add(ChatRoomDto.toDto(chatRoom));
				}
			}
			return new PageImpl<>(mentorChatRoomDtos, pageable, chatRooms.getTotalElements());
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.CHATTING_LIST_FAIE, ResponseMessage.CHATTING_LIST_FAIE, e);
		}
		
	}
	/*본인 활동 리스트 출력 */
	public Page<ChatRoomDto> activeChatRoomAll(Long memberNo, int pageNumber, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<ChatRoom> chatRooms = chatRoomRepository.findByMemberNoAndStatus1(memberNo,pageable);
			List<ChatRoomDto> chatRoomDtos = new ArrayList<>();
			for (ChatRoom chatRoom : chatRooms) {
				Long chatRoomNo = chatRoom.getChatRoomNo();
				String chatRoomName = null;
				int chatRoomLeaveStatus = 0;
				if (chatRoomStatusService.getChatRoomStatus(chatRoomNo, memberNo) != null) {
					chatRoomName = chatRoomStatusService.getChatRoomStatus(chatRoomNo, memberNo).getChatRoomName();
					chatRoomLeaveStatus = chatRoomStatusService.getChatRoomStatus(chatRoomNo, memberNo).getChatRoomStatus();
				}
				ChatRoomDto chatRoomDto = ChatRoomDto.toDto(chatRoom);
				chatRoomDto.setChatRoomName(chatRoomName);
				chatRoomDto.setChatRoomLeaveStatus(chatRoomLeaveStatus);
				chatRoomDtos.add(chatRoomDto);
				
			}
			
			return new PageImpl<>(chatRoomDtos, pageable, chatRooms.getTotalElements());
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.CHATTING_LIST_FAIE, ResponseMessage.CHATTING_LIST_FAIE, e);
		}
	}
	/*본인 활동 리스트 출력 */
	public Page<ChatRoomDto> waitChatRoomAll(Long memberNo, int pageNumber, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<ChatRoom> chatRooms = chatRoomRepository.findByMemberNoAndStatus2(memberNo,pageable);
			List<ChatRoomDto> chatRoomDtos = new ArrayList<>();
			for (ChatRoom chatRoom : chatRooms) {
				Long chatRoomNo = chatRoom.getChatRoomNo();
				String chatRoomName = null;
				int chatRoomLeaveStatus = 0;
				if (chatRoomStatusService.getChatRoomStatus(chatRoomNo, memberNo) != null) {
					chatRoomName = chatRoomStatusService.getChatRoomStatus(chatRoomNo, memberNo).getChatRoomName();
					chatRoomLeaveStatus = chatRoomStatusService.getChatRoomStatus(chatRoomNo, memberNo).getChatRoomStatus();
				}
				ChatRoomDto chatRoomDto = ChatRoomDto.toDto(chatRoom);
				chatRoomDto.setChatRoomName(chatRoomName);
				chatRoomDto.setChatRoomLeaveStatus(chatRoomLeaveStatus);
				chatRoomDtos.add(chatRoomDto);
				
			}
			
			return new PageImpl<>(chatRoomDtos, pageable, chatRooms.getTotalElements());
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.CHATTING_LIST_FAIE, ResponseMessage.CHATTING_LIST_FAIE, e);
		}
	}
	/*본인 활동 리스트 출력 
	@Override
	public Page<ChatRoomDto> selectChatRoomAll(Long MemberNo, int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<ChatRoom> chatRooms = chatRoomRepository.findAll();
		List<ChatRoomDto> chatRoomDtos = new ArrayList<>();
		for (int i = 0; i <chatRooms.size(); i++) {
			if (chatRooms.get(i).getMentee().getMemberNo() == MemberNo || chatRooms.get(i).getMentor().getMemberNo() == MemberNo) {
				Long chatRoomNo = chatRooms.get(i).getChatRoomNo();
				String chatRoomName = null;
				int chatRoomLeaveStatus = 0;
				if (chatRoomStatusService.getChatRoomStatus(chatRoomNo, MemberNo) != null) {
					chatRoomName = chatRoomStatusService.getChatRoomStatus(chatRoomNo, MemberNo).getChatRoomName();
					chatRoomLeaveStatus = chatRoomStatusService.getChatRoomStatus(chatRoomNo, MemberNo).getChatRoomStatus();
				}
				ChatRoomDto chatRoomDto = ChatRoomDto.toDto(chatRooms.get(i));
				chatRoomDto.setChatRoomName(chatRoomName);
				chatRoomDto.setChatRoomLeaveStatus(chatRoomLeaveStatus);
				chatRoomDtos.add(chatRoomDto);
				if (chatRoomDtos.size() > pageSize-1) {
					return new PageImpl<>(chatRoomDtos, pageable, chatRooms.size());
				}
			}
		}
		return new PageImpl<>(chatRoomDtos, pageable, chatRooms.size());
	}
	*/
	@Override
	public List<ChatMessageDto> selectChatMessages(Long chatRoomNo) {
		try {
			ChatRoom chatRoom = chatRoomRepository.findById(chatRoomNo).get();
			List<ChatMessage> chatMessages = chatRoom.getChatMessages();
			List<ChatMessageDto> chatMessageDtos = new ArrayList<>();
			for (int i = 0; i < chatMessages.size(); i++) {
				chatMessageDtos.add(ChatMessageDto.toDto(chatMessages.get(i)));
			}
			return chatMessageDtos;
		}catch (Exception e) {
			throw new CustomException(ResponseStatusCode.CHATTING_MESSAGE_FAIE, ResponseMessage.CHATTING_MESSAGE_FAIE, e);
		}
	}
	
}
