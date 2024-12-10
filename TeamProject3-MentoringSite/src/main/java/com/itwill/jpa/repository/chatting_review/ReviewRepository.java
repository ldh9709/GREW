package com.itwill.jpa.repository.chatting_review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.jpa.entity.chatting_review.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	// 특정 요청 번호에 해당하는 리뷰 리스트 조회
    List<Review> findReviewByChatRoom_ChatRoomNoAndReviewStatus(Long chatRoomNo,Integer reviewStatus);
    
 // 멘티 번호에 해당하는 리뷰 리스트 조회
    List<Review> findReviewByChatRoom_Mentee_MemberNoAndReviewStatus(Long memberNo,Integer reviewStatus);

    // 멘토 번호에 해당하는 리뷰 리스트 조회
    List<Review> findReviewByChatRoom_Mentor_MemberNoAndReviewStatus(Long memberNo,Integer reviewStatus);
	
    Review findByReviewNo(Long reviewNo);
    
    //리뷰 대상 멘토찾기(알림용)
    @Query("SELECT c.mentor.memberNo "
    	       + "FROM ChatRoom c "
    	       + "JOIN Review r ON r.chatRoom.chatRoomNo = c.chatRoomNo "
    	       + "WHERE r.reviewNo = :reviewNo")
    	Long findMentorNoByReviewNo(@Param("reviewNo") Long reviewNo);
    
}
