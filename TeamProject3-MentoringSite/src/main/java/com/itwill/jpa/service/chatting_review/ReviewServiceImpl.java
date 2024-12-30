package com.itwill.jpa.service.chatting_review;

import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.entity.chatting_review.Review;
import com.itwill.jpa.exception.CustomException;
import com.itwill.jpa.repository.chatting_review.ChatRoomRepository;
import com.itwill.jpa.repository.chatting_review.ReviewRepository;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.member_information.MentorProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MentorProfileService mentorProfileService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ChatRoomRepository chatRoomRepository, MentorProfileService mentorProfileService) {
        this.reviewRepository = reviewRepository;
        this.chatRoomRepository = chatRoomRepository;
		this.mentorProfileService = mentorProfileService;
    }

    /* 리뷰 생성 */
    @Override
    public Review createReview(ReviewDto reviewDto) {
    	try {
        // DTO를 엔티티로 변환
        Review review = Review.toEntity(reviewDto);

        // 리뷰 저장
        return reviewRepository.save(review);
    	}catch (Exception e) {
    		throw new CustomException(ResponseStatusCode.CREATED_REVIEW_FAIL, ResponseMessage.CREATED_REVIEW_FAIL, e);
    	}
    }

    /* 리뷰 업데이트 *///수정필요 
    @Transactional
    @Override
    public Review updateReview(ReviewDto reviewDto) {
    	try {
    	Review review = reviewRepository.findByReviewNo(reviewDto.getReviewNo());
    	
    	review.setReviewTitle(reviewDto.getReviewTitle());
    	review.setReviewContent(review.getReviewContent());
    	review.setReviewScore(reviewDto.getReviewScore());
    	
    	return reviewRepository.save(review);
    	}catch (Exception e) {
    		throw new CustomException(ResponseStatusCode.UPDATE_REVIEW_FAIL, ResponseMessage.UPDATE_REVIEW_FAIL, e);
    	}
    	
    	
    }

    /* 리뷰 삭제(상태업데이트) */
    @Transactional
    @Override
    public Review deleteReview(Long reviewNo) {
    	try {
        // 리뷰 조회
        Review findReview = reviewRepository.findByReviewNo(reviewNo);
        findReview.setReviewStatus(2);
        reviewRepository.save(findReview);
        
        return findReview;
    	}catch (Exception e) {
    		throw new CustomException(ResponseStatusCode.DELETE_REVIEW_FAIL, ResponseMessage.DELETE_REVIEW_FAIL, e);
    	}
    }

    /* 특정 리뷰 조회 */
    @Override
    public ReviewDto getReviewByReviewNo(Long reviewNo) {
    	try {
        // 리뷰 조회
        Review review = reviewRepository.findByReviewNo(reviewNo);
        ReviewDto reviewDto = ReviewDto.toDto(review);
        
    	return reviewDto;
    	}catch (Exception e) {
    		throw new CustomException(ResponseStatusCode.VIEW_REVIEW_FAIL, ResponseMessage.VIEW_REVIEW_FAIL, e);
    	}
    }

    /* 특정 요청 번호에 따른 리뷰 리스트 조회 */
    @Override
    public Page<ReviewDto> getReviewByChatRoomNo(Long chatRoomNo,int pageNumber, int pageSize) {
    	try {
    		Pageable pageable = PageRequest.of(pageNumber, pageSize);
    	Page<Review> reviews = reviewRepository.findReviewByChatRoom_ChatRoomNoAndReviewStatus(chatRoomNo,1,pageable);
        List<ReviewDto> reviewDtolist = new ArrayList<>();
        for (Review review : reviews) {
            reviewDtolist.add(ReviewDto.toDto(review));
        }
        return new PageImpl<>(reviewDtolist, pageable, reviews.getTotalElements());
    	}catch (Exception e) {
    		throw new CustomException(ResponseStatusCode.READ_REVIEW_LIST_FAIL, ResponseMessage.READ_REVIEW_LIST_FAIL, e);
    	}
    }

    /* 특정 멤버 번호에 따른 리뷰 리스트 조회 */
    @Override
    public Page<ReviewDto> getReviewByMemberNo(Long memberNo, int pageNumber, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize); // Pageable 객체 생성


            // 멘토 번호로 리뷰 조회 (페이징 처리)
            Page<Review> mentorReviews = reviewRepository.findReviewByChatRoom_Mentor_MemberNoAndReviewStatus(memberNo, 1,pageable);
            System.out.println("mentorReviews.getTotalElements() = " + mentorReviews.getTotalElements());
            // 멘티와 멘토의 리뷰를 합침 (합치기 전에 두 페이지를 병합할 필요가 있음)
            List<Review> allReviews = new ArrayList<>();
            allReviews.addAll(mentorReviews.getContent());

            // 리뷰를 DTO로 변환
            List<ReviewDto> reviewDtoList = new ArrayList<>();
            for (Review review : allReviews) {
                reviewDtoList.add(ReviewDto.toDto(review));
            }

            // Page 객체로 반환 (totalElements는 두 페이지 합친 전체 리뷰 개수)
            return new PageImpl<>(reviewDtoList, pageable, mentorReviews.getTotalElements());
        } catch (Exception e) {
            throw new CustomException(ResponseStatusCode.READ_REVIEW_LIST_FAIL, ResponseMessage.READ_REVIEW_LIST_FAIL, e);
        }
    }


    /* 모든 리뷰 리스트 조회 */
    @Override
    public Page<ReviewDto> getReviewAll(int pageNumber, int pageSize) {
        try {
            // Pageable 객체 생성 (pageNumber는 0부터 시작, pageSize는 한 페이지에 표시할 리뷰 개수)
            Pageable pageable = PageRequest.of(pageNumber, pageSize);

            // 리뷰를 페이징하여 조회
            Page<Review> reviews = reviewRepository.findAll(pageable);

            // 조회된 Review 객체를 DTO로 변환
            List<ReviewDto> reviewDtoList = new ArrayList<>();
            for (Review review : reviews) {
                reviewDtoList.add(ReviewDto.toDto(review));
            }

            // PageImpl을 사용하여 Page<ReviewDto> 객체 반환
            return new PageImpl<>(reviewDtoList, pageable, reviews.getTotalElements());
        } catch (Exception e) {
            throw new CustomException(ResponseStatusCode.READ_REVIEW_LIST_FAIL, ResponseMessage.READ_REVIEW_LIST_FAIL, e);
        }
    }

    
}