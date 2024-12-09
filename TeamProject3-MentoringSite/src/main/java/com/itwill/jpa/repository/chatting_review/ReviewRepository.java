package com.itwill.jpa.repository.chatting_review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.jpa.entity.chatting_review.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	// 특정 요청 번호에 해당하는 리뷰 리스트 조회
    List<Review> findReviewByChatRoom_ChatRoomNo(Long chatRoomNo);
    
 // 멘티 번호에 해당하는 리뷰 리스트 조회
    List<Review> findReviewByChatRoom_Mentee_MemberNo(Long memberNo);

    // 멘토 번호에 해당하는 리뷰 리스트 조회
    List<Review> findReviewByChatRoom_Mentor_MemberNo(Long memberNo);
	
    Review findByReviewNo(Long reviewNo);
   
    @Query("SELECT AVG(r.reviewScore) FROM Review r WHERE r.chatRoom.mentor.memberNo = :mentorNo")
    Double findAverageScoreByMentor(@Param("mentorNo") Long mentorNo);
    
    @Query("SELECT r.reviewScore FROM Review r WHERE r.chatRoom.mentor.memberNo = :memberNo")
    List<Integer> findReviewScoresByMentor(@Param("memberNo") Long memberNo);
    
    //리뷰 대상 멘토찾기(알림용)
    @Query("SELECT c.mentor.memberNo "
    	       + "FROM ChatRoom c "
    	       + "JOIN Review r ON r.chatRoom.chatRoomNo = c.chatRoomNo "
    	       + "WHERE r.reviewNo = :reviewNo")
    	Long findMentorNoByReviewNo(@Param("reviewNo") Long reviewNo);
    
}
