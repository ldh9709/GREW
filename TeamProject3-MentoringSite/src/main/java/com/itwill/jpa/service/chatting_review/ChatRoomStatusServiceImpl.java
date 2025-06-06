package com.itwill.jpa.service.chatting_review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.chatting_review.ChatRoomDto;
import com.itwill.jpa.dto.chatting_review.ChatRoomStatusDto;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.chatting_review.ChatRoomStatus;
import com.itwill.jpa.repository.chatting_review.ChatRoomRepository;
import com.itwill.jpa.repository.chatting_review.ChatRoomStatusRepository;
import com.itwill.jpa.service.member_information.MentorProfileService;

@Service
public class ChatRoomStatusServiceImpl implements ChatRoomStatusService{
	@Autowired
	private ChatRoomStatusRepository chatRoomStatusRepository;
	@Autowired
	private ChatRoomRepository chatRoomRepository;
	@Autowired
	private MentorProfileService mentorProfileService;
	
	@Override
	public ChatRoomStatusDto getChatRoomStatus(Long chatRoomStatusNo) {
		ChatRoomStatus chatRoomStatus = chatRoomStatusRepository.findById(chatRoomStatusNo).get();
		ChatRoomStatusDto chatRoomStatusDto = ChatRoomStatusDto.toDto(chatRoomStatus);
		return chatRoomStatusDto;
	}
	@Override
	public ChatRoomStatusDto getChatRoomStatus(Long chatRoomNo, Long memberNo) {
		ChatRoomStatus chatRoomStatus = chatRoomStatusRepository.findByChatRoom_ChatRoomNoAndMember_MemberNo(chatRoomNo, memberNo);
		ChatRoomStatusDto chatRoomStatusDto = new ChatRoomStatusDto();
		if (chatRoomStatus == null) {
			return null;
		}else {
			chatRoomStatusDto = ChatRoomStatusDto.toDto(chatRoomStatus);
			return chatRoomStatusDto;
		}
	}
	@Override
	public void saveFirstChatRoomStatus(ChatRoomStatus mentorChatRoomStatus, ChatRoomStatus menteeChatRoomStatus) {
			chatRoomStatusRepository.save(mentorChatRoomStatus);
			chatRoomStatusRepository.save(menteeChatRoomStatus);	
	}
	@Override
	public void ResetChatRoomStatus(ChatRoom chatRoom) {
		for (int i = 0; i < chatRoom.getChatRoomStatusList().size(); i++) {
			ChatRoomStatus chatRoomStatus = chatRoom.getChatRoomStatusList().get(i);
			chatRoomStatus.setChatRoomStatus(7500);
			if(chatRoom.getMentee().getMemberNo() == chatRoomStatus.getMember().getMemberNo()) {
				chatRoomStatus.setChatRoomName(chatRoom.getMentor().getMemberName()+"님에게 채팅방을 신청했습니다.");
			}else {
				chatRoomStatus.setChatRoomName(chatRoom.getMentee().getMemberName()+"님에게서 채팅방 신청이 도착했습니다.");
			}
			ChatRoomStatus chatRoomStatus1 = chatRoomStatusRepository.save(chatRoomStatus);
		}
	}
	@Override
	public void updateChatRoomStatus(Long chatRoomNo) {
		ChatRoom chatRoom = chatRoomRepository.findById(chatRoomNo).get();
		if (chatRoom.getChatRoomStatus() == 7000) {
			for (int i = 0; i < chatRoom.getChatRoomStatusList().size(); i++) {
				ChatRoomStatus chatRoomStatus = chatRoom.getChatRoomStatusList().get(i);
				chatRoomStatus.setChatRoomStatus(7600);
				chatRoomStatus.setChatRoomName(chatRoom.getMentor().getMemberName()+"님과 "+chatRoom.getMentee().getMemberName()+"님의 채팅방");
				ChatRoomStatus chatRoomStatus1 = chatRoomStatusRepository.save(chatRoomStatus);
			}
		}
	}
	@Override
	public ChatRoomStatusDto updateChatRoomStatus(Long chatRoomNo, Long memberNo) {
		ChatRoom chatRoom = chatRoomRepository.findById(chatRoomNo).get();
		for (int i = 0; i < chatRoom.getChatRoomStatusList().size(); i++) {
			ChatRoomStatus chatRoomStatus = chatRoom.getChatRoomStatusList().get(i);
			if (chatRoom.getChatRoomStatusList().get(i).getMember().getMemberNo() == memberNo) {
				chatRoomStatus.setChatRoomStatus(7700);
				ChatRoomStatus chatRoomStatus1 = chatRoomStatusRepository.save(chatRoomStatus);
				return ChatRoomStatusDto.toDto(chatRoomStatus1);
			}
		}
		return new ChatRoomStatusDto();
	}
	@Override
	public ChatRoomStatusDto updateChatRoomName(Long chatRoomNo, Long memberNo, String chatRoomName) {
		ChatRoom chatRoom = chatRoomRepository.findById(chatRoomNo).get();
		for (int i = 0; i < chatRoom.getChatRoomStatusList().size(); i++) {
			ChatRoomStatus chatRoomStatus = chatRoom.getChatRoomStatusList().get(i);
			if (chatRoom.getChatRoomStatusList().get(i).getMember().getMemberNo() == memberNo) {
				chatRoomStatus.setChatRoomName(chatRoomName);
				ChatRoomStatus chatRoomStatus1 = chatRoomStatusRepository.save(chatRoomStatus);
				return ChatRoomStatusDto.toDto(chatRoomStatus1);
			}
		}
		return new ChatRoomStatusDto();
	}
}
