package com.itwill.jpa.repository.chatting_review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.chatting_review.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	// 특정 요청 번호에 해당하는 리뷰 리스트 조회
    List<Review> findByMentoringRequestRequestNo(Long requestNo);
    
    // 멤버 번호에 해당하는 리뷰 리스트 조회
    List<Review> findByMentoringRequestMember_Member_MemberNo(Long memberNo);
	
    Review findByReviewNo(Long reviewNo);
    
}
