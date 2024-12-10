package com.itwill.jpa.service.chatting_review;

import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.entity.chatting_review.Review;
import com.itwill.jpa.repository.chatting_review.ChatRoomRepository;
import com.itwill.jpa.repository.chatting_review.ReviewRepository;

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

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ChatRoomRepository chatRoomRepository) {
        this.reviewRepository = reviewRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    /* 리뷰 생성 */
    @Override
    public Review saveReview(ReviewDto reviewDto) {
        // DTO를 엔티티로 변환
        Review review = Review.toEntity(reviewDto);
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
    public Review selectReviewByReviewNo(Long reviewNo) {
//        // 리뷰 조회
//        Optional<Review> reviewOptional = reviewRepository.findById(reviewNo);
//        if (reviewOptional.isPresent()) {
//            return ReviewDto.toDto(reviewOptional.get());
//        } else {
//            throw new RuntimeException("Review not found");
//        }
    	return reviewRepository.findByReviewNo(reviewNo);
    }

    /* 특정 요청 번호에 따른 리뷰 리스트 조회 */
    @Override
    public List<ReviewDto> selectReviewByChatRoomNo(Long chatRoomNo) {
        List<Review> reviews = reviewRepository.findReviewByChatRoom_ChatRoomNoAndReviewStatus(chatRoomNo,1);
        List<ReviewDto> reviewDtolist = new ArrayList<>();
        for (Review review : reviews) {
            reviewDtolist.add(ReviewDto.toDto(review));
        }
        return reviewDtolist;
    }

    /* 특정 멤버 번호에 따른 리뷰 리스트 조회 */
    @Override
    public List<ReviewDto> selectReviewByMemberNo(Long memberNo) {
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
    public List<ReviewDto> selectReviewAll() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for (Review review : reviews) {
            reviewDtoList.add(ReviewDto.toDto(review));
        }
        return reviewDtoList;
    }
}