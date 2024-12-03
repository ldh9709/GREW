package com.itwill.jpa.repository.member_information;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.chatting_review.MentoringRequest;

public interface MentoringRequestRepository extends JpaRepository<MentoringRequest, Long>{
	public MentoringRequest findByrequestNo(Long requestNo);
}