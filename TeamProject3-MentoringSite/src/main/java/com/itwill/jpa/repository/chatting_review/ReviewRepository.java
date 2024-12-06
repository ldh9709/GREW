package com.itwill.jpa.repository.chatting_review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.chatting_review.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	// 특정 요청 번호에 해당하는 리뷰 리스트 조회
    List<Review> findReviewByChatRoom_ChatRoomNo(Long chatRoomNo);
    
 // 멘티 번호에 해당하는 리뷰 리스트 조회
    List<Review> findReviewByChatRoom_Mentee_MemberNo(Long memberNo);

    // 멘토 번호에 해당하는 리뷰 리스트 조회
    List<Review> findReviewByChatRoom_Mentor_MemberNo(Long memberNo);
	
    Review findByReviewNo(Long reviewNo);
    
}
