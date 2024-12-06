package com.itwill.jpa.service.chatting_review;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.entity.chatting_review.Review;

public interface ReviewService {
	//리뷰 생성
	public Review saveReview(ReviewDto reviewDto);
	//리뷰 수정
	public Review updateReview(ReviewDto reviewDto);
	//리뷰 삭제
	public Review deleteReview(Long reviewNo);
	
	//리뷰 선택(특정리뷰 수정?삭제?할때 필요할듯)
	public Review selectReviewByReviewNo(Long reviewNo);
	//요청번호에 따른 리뷰 리스트
	public List<ReviewDto> selectReviewByChatRoomNo(Long chatRoomNo);
	//멤버 번호의 리뷰 리스트
	public List<ReviewDto> selectReviewByMemberNo(Long memberNo);
	//모든 리뷰리스트
	public List<ReviewDto> selectReviewAll();
	
	/**
     * 특정 멘토의 평균 리뷰 점수를 반환
     * 
     * @param mentorNo 멘토 번호
     * @return 평균 리뷰 점수
     */
    Double getAverageReviewScoreByMentor(Long mentorNo);
	
}
