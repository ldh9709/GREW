package com.itwill.jpa.service.chatting_review;

import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.entity.chatting_review.Review;
import com.itwill.jpa.entity.member_information.MentorProfile;
import com.itwill.jpa.repository.chatting_review.ChatRoomRepository;
import com.itwill.jpa.repository.chatting_review.ReviewRepository;
import com.itwill.jpa.service.member_information.MentorProfileService;
import com.itwill.jpa.service.member_information.MentorManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MentorManager manager;
    private final MentorProfileService mentorProfileService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ChatRoomRepository chatRoomRepository, MentorProfileService mentorProfileService) {
        this.reviewRepository = reviewRepository;
        this.chatRoomRepository = chatRoomRepository;
		this.manager = new MentorManager();
		this.mentorProfileService = mentorProfileService;
    }

    /* 리뷰 생성 */
    @Override
    public Review createReview(ReviewDto reviewDto) {
        // DTO를 엔티티로 변환
        Review review = Review.toEntity(reviewDto);
        
        /* 멘토 평점 업데이트 */
        Double averageScore = reviewReverageScore(reviewDto.getMentorMemberNo());
        mentorProfileService.updateMentorRatingg2(reviewDto.getMentorMemberNo(), averageScore);

//        manager.handleMentorRating(reviewDto.getMentorMemberNo());
        // 리뷰 저장
        return reviewRepository.save(review);
    }

    /* 리뷰 업데이트 *///수정필요 
    @Transactional
    @Override
    public Review updateReview(ReviewDto reviewDto) {

    	Review review = reviewRepository.findByReviewNo(reviewDto.getReviewNo());
    	
    	review.setReviewTitle(reviewDto.getReviewTitle());
    	review.setReviewContent(review.getReviewContent());
    	review.setReviewScore(reviewDto.getReviewScore());
    	
    	return reviewRepository.save(review);
    	
    	
    	
    }

    /* 리뷰 삭제(상태업데이트) */
    @Transactional
    @Override
    public Review deleteReview(Long reviewNo) {
        // 리뷰 조회
        Review findReview = reviewRepository.findByReviewNo(reviewNo);
        findReview.setReviewStatus(2);
        reviewRepository.save(findReview);
        
        return findReview;
    }

    /* 특정 리뷰 조회 */
    @Override
    public ReviewDto getReviewByReviewNo(Long reviewNo) {
        // 리뷰 조회
        Review review = reviewRepository.findByReviewNo(reviewNo);
        ReviewDto reviewDto = ReviewDto.toDto(review);
        
    	return reviewDto;
    }

    /* 특정 요청 번호에 따른 리뷰 리스트 조회 */
    @Override
    public List<ReviewDto> getReviewByChatRoomNo(Long chatRoomNo) {
        List<Review> reviews = reviewRepository.findReviewByChatRoom_ChatRoomNoAndReviewStatus(chatRoomNo,1);
        List<ReviewDto> reviewDtolist = new ArrayList<>();
        for (Review review : reviews) {
            reviewDtolist.add(ReviewDto.toDto(review));
        }
        return reviewDtolist;
    }

    /* 특정 멤버 번호에 따른 리뷰 리스트 조회 */
    @Override
    public List<ReviewDto> getReviewByMemberNo(Long memberNo) {
        Set<Review> reviews = new HashSet<>();

        // 멘티 번호로 리뷰 조회
        reviews.addAll(reviewRepository.findReviewByChatRoom_Mentee_MemberNoAndReviewStatus(memberNo,1));

        // 멘토 번호로 리뷰 조회
        reviews.addAll(reviewRepository.findReviewByChatRoom_Mentor_MemberNoAndReviewStatus(memberNo,1));

        // 리뷰를 DTO로 변환
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for (Review review : reviews) {
            reviewDtoList.add(ReviewDto.toDto(review));
        }

        return reviewDtoList;
    }


    /* 모든 리뷰 리스트 조회 */
    @Override
    public List<ReviewDto> getReviewAll() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for (Review review : reviews) {
            reviewDtoList.add(ReviewDto.toDto(review));
        }
        return reviewDtoList;
    }
    
    
    /* 특정 멘토 리뷰 평점 */
    public Double reviewReverageScore(Long mentorNo) {
        /* 멘토 평점 업데이트 (리뷰 수, 총점 전달) */
        List<ReviewDto> reviews = getReviewByMemberNo(mentorNo);
        
        Integer totCount = reviews.size();
        Double totScore = 0.0;
        Double averageScore = 0.0;
        for (ReviewDto review : reviews) {
        	totScore += review.getReviewScore();
		}
        
        averageScore =totScore/totCount;
        
        return averageScore;
        
    }
}