package com.itwill.jpa.controller.chatting_review;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.itwill.jpa.dto.chatting_review.ChatMessageDto;
import com.itwill.jpa.dto.chatting_review.ChatMessageImageDto;
import com.itwill.jpa.dto.chatting_review.ChatRoomDto;
import com.itwill.jpa.entity.chatting_review.ChatMessage;
import com.itwill.jpa.entity.chatting_review.ChatMessageImage;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.chatting_review.ChatMessageImageRepository;
import com.itwill.jpa.service.chatting_review.ChatMessageImageService;
import com.itwill.jpa.service.chatting_review.ChatMessageService;
import com.itwill.jpa.service.chatting_review.ChatRoomService;
import com.itwill.jpa.service.member_information.MemberService;



@Controller
public class ChatController {
	@Autowired
	private ChatMessageService chatMessageService;
	@Autowired
    private ChatMessageImageService chatMessageImageService;
    
	@Autowired
    private MemberService memberService;

    @Autowired
    private ChatMessageImageRepository chatMessageImageRepository;
    
    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/chat/member/{memberNo}") 	
    @SendTo("/topic/messages/member/{memberNo}")
    public ChatRoomDto sendMessageMember(@DestinationVariable("memberNo") Long memberNo, @Payload ChatRoomDto chatRoom) {
    	if (chatRoom.getChatRoomNo()!=null) {
    		
    		System.out.println(chatRoom);
    		System.out.println(chatRoom.getCountIsRead());
    		System.out.println(chatRoom.getLastedMessage());
    	}
    	return chatRoom;
    }
	@MessageMapping("/chat/{roomId}") 	// html에서 정보를 받음
	@SendTo("/topic/messages/{roomId}")	// roomid로 정보를 다시 보냄
	public ChatMessageDto sendMessage(@DestinationVariable("roomId") String roomId, @Payload ChatMessageDto message) {
	    if (message.getMemberName() != null) {
	        // 실제로 메시지가 들어왔을 때 확인
	    	System.out.println(message);
	        System.out.println("memberName : " + message.getMemberName());
	        System.out.println("ChatContent : " + message.getChatMessageContent());
	        Long ChatMessageNo = chatMessageService.createChatMessage(message).getChatMessageNo();
	        message.setChatMessageNo(ChatMessageNo);
	        
	        
	    }
	    return message;
	}
	// 이미지 메시지 처리 (WebSocket 방식)
	@MessageMapping("/chat/sendImage/{roomId}")
	@SendTo("/topic/images/{roomId}")
	public ChatMessageImageDto sendImage(@DestinationVariable("roomId") Long roomId,@Payload Map<String, String> request) {
	    ChatRoom chatRoom = ChatRoom.toEntity(chatRoomService.getChatRoom(roomId));
		// 클라이언트에서 받은 이미지 Blob (Base64 형식)
	    String imageBlob = request.get("imageBlob");
	    String chatRoomNo = request.get("chatRoomNo");  // roomId 받기
	    String memberNo = request.get("memberNo");     // memberNo 받기

	    Member member = memberService.getMember(Long.parseLong(memberNo));
	    System.out.println("받은 imageBlob: " + imageBlob);  // 디버그용 로그
	    System.out.println("Received chatRoomNo: " + chatRoomNo);
	    System.out.println("Received memberNo: " + memberNo);

	    // 이미지 Blob이 비어있는지 확인
	    if (imageBlob == null || imageBlob.isEmpty()) {
	        throw new IllegalArgumentException("이미지 데이터가 비어있습니다.");
	    }

	    // Base64 형식에서 'data:image/*;base64,' 부분을 제거하고 실제 Base64 데이터만 추출
	    String base64Image = imageBlob;

	    // 이미지 형식 체크 후, Base64에서 앞부분 제거
	 // 이미지 형식 체크 후, Base64에서 앞부분 제거
	    if (base64Image.startsWith("data:image/png;base64,")) {
	        base64Image = base64Image.split(",")[1];
	    } else if (base64Image.startsWith("data:image/jpeg;base64,")) {
	        base64Image = base64Image.split(",")[1];
	    } else if (base64Image.startsWith("data:image/gif;base64,")) {
	        base64Image = base64Image.split(",")[1];
	    } else if (base64Image.startsWith("iVBORw0KGgo")) {
	        // PNG 이미지의 Base64 시작 부분 확인 (이미 Base64로 인코딩된 데이터이므로, 시작 부분만 체크)
	        base64Image = base64Image;  // Base64가 이미 포함된 경우 그대로 사용
	    } else if (base64Image.startsWith("/9j/")) {
	        // JPEG 이미지의 Base64 시작 부분을 확인 (이미 Base64로 인코딩된 데이터이므로, 시작 부분만 체크)
	        base64Image = base64Image;  // JPEG 형식인 경우 그대로 사용
	    } else {
	        throw new IllegalArgumentException("지원하지 않는 이미지 형식입니다.");
	    }


	    // Base64 디코딩하여 byte[]로 변환
	    byte[] imageBytes = Base64.getDecoder().decode(base64Image);

	    // 채팅 메시지 생성
	    ChatMessageDto chatMessage = new ChatMessageDto();
	    chatMessage.setChatRoomNo(Integer.parseInt(chatRoomNo));  // 받은 chatRoomNo 설정
	    chatMessage.setMemberNo(member.getMemberNo());     // 받은 memberNo 설정
	    chatMessage.setMemberName(member.getMemberName());
	    chatMessage.setBase64Image(base64Image);

	    
	    // 메시지 저장
	    ChatMessage chatmessage = chatMessageService.createChatMessage(chatMessage);  // 메시지 저장 후 ID를 받음
	    chatmessage.setChatRoom(chatRoom);
	    System.out.println("생성된 메시지 : "+chatmessage);
	    
	 // ChatMessage 객체를 생성할 때 chatRoom이 null이 아닌지 확인
	    System.out.println("chatMessage.getChatRoom(): " + chatmessage.getChatRoom());

	    
	    // 이미지 정보 저장
	    ChatMessageImageDto savedChatMessageImage = new ChatMessageImageDto();
	    savedChatMessageImage.setImageName("이미지");  // Base64 데이터를 그대로 저장하는 경우
	    savedChatMessageImage.setChatMessageNo(chatmessage.getChatMessageNo()); // 채팅 메시지 ID로 이미지 연결
	    savedChatMessageImage.setChatRoomNo(chatRoom.getChatRoomNo());
	    savedChatMessageImage.setBase64Image(base64Image);
	    savedChatMessageImage.setMemberNo(chatmessage.getMember().getMemberNo());
	    savedChatMessageImage.setMemberName(member.getMemberName());


	    // 이미지 정보 저장
	    Long ChatImageNo = chatMessageImageService.createImage(savedChatMessageImage).getImageNo();
	    savedChatMessageImage.setImageNo(ChatImageNo);
	    // 저장된 이미지 객체 반환
	    return savedChatMessageImage;
	}


 // 이미지 요청 처리 (클라이언트에서 이미지 다운로드)
	@GetMapping("/image/{chatMessageNo}")
	public ResponseEntity<String> getImage(@PathVariable("chatMessageNo") Long chatMessageNo) {
	    // ChatMessageNo로 해당 메시지를 조회
	    ChatMessage chatMessage = chatMessageService.getChatMessageByNo(chatMessageNo);

	    // 메시지가 존재하지 않으면 404 오류 반환
	    if (chatMessage == null || !chatMessage.getChatMessageContent().equals("이미지")) {
	        return ResponseEntity.notFound().build();
	    }

	    // ChatMessage에 연관된 이미지 조회
	    ChatMessageImage image = chatMessageImageRepository.findByChatMessageChatMessageNo(chatMessageNo);

	    // 이미지가 존재하지 않으면 404 오류 반환
	    if (image == null) {
	        return ResponseEntity.notFound().build();
	    }

	    // Base64 형식의 이미지 데이터 가져오기
	    String base64Image = image.getBase64Image();

	    // Base64 형식의 이미지 반환
	    return ResponseEntity.ok(base64Image);
	}

	
	// 읽음 상태 업데이트 처리
    @MessageMapping("/chat/read/{roomId}") // 읽음 상태 업데이트를 처리
    @SendTo("/topic/read-status/{roomId}") // 읽음 상태를 해당 방의 모든 클라이언트에게 브로드캐스트
    public ChatMessageDto updateReadStatus(@DestinationVariable("roomId") String roomId, @Payload ChatMessageDto message) {
        if (message.getChatMessageNo() != null) {
        	System.out.println("읽은 메세지"+message);
            System.out.println("읽은 메세지 번호 " + message.getChatMessageNo());
            Long chatMessageNo = chatMessageService.updateChatMessageCheck(message.getChatMessageNo()).getChatMessageNo(); // 읽음 상태 업데이트
            message.setChatMessageNo(chatMessageNo);
        }
        return message;
    }

}