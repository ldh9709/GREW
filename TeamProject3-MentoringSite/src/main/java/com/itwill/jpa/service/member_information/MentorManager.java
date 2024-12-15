package com.itwill.jpa.service.member_information;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.service.chatting_review.ReviewService;

@Service
public class MentorManager {
	
	@Autowired
	private MentorProfileService mentorProfileService;
	@Autowired
	private ReviewService reviewService;
	
	@Transactional
    public double handleMentorRating(Long memberNo) {
		
		// 1. 멘토의 리뷰 데이터 가져오기
		List<ReviewDto> reviewList = reviewService.getReviewByMemberNo(memberNo);
		
		// 2. 평균 평점 계산
		double count = reviewList.size();
		double totScore = 0;
		   
		for (ReviewDto reviewDto : reviewList) {
			totScore += reviewDto.getReviewScore();
		}
		// 3. 리뷰 평균 점수 계산
		double averageScore = totScore/count;
		
		// 4.. 멘토 프로필의 평점 업데이트
		mentorProfileService.updateMentorRatingg(memberNo, averageScore);
		
		// 5.. 결과 반환
		return averageScore;
    }
	
}
