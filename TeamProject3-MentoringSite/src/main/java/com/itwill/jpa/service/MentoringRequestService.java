package com.itwill.jpa.service;

import java.util.List;

import com.itwill.jpa.entity.chatting_review.MentoringRequest;

public interface MentoringRequestService {
	MentoringRequest getMentoringRequest(Long requestNo);
	MentoringRequest saveMentoringRequest(MentoringRequest mentoringRequest);
	MentoringRequest updateMentoringRequest(MentoringRequest mentoringRequest) throws Exception;
	List<MentoringRequest> selectMentoringRequestAll();
}