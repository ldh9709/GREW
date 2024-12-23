package com.itwill.jpa.service.chatting_review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReviewServiceImplTest {
		
	@Autowired
	private ReviewService reviewService;

//	@Test
	void testCreateReview() {
		reviewService.createReview(null);
	}
	
//	@Test
	void testReviewByMemberNo() {
		System.out.println(reviewService.getReviewByMemberNo(5L,1,1));
	}
	

}
