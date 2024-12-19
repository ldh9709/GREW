package com.itwill.jpa.repository.chatting_review;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.chatting_review.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>{
}