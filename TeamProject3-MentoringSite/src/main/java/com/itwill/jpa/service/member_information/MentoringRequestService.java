package com.itwill.jpa.service.member_information;

import java.util.List;

import com.itwill.jpa.dto.chatting_review.MentoringRequestDto;
import com.itwill.jpa.entity.chatting_review.MentoringRequest;

public interface MentoringRequestService {
	MentoringRequest getMentoringRequest(Long requestNo);
	MentoringRequest saveMentoringRequest(MentoringRequestDto mentoringRequestDto);
	MentoringRequest updateActive(MentoringRequest mentoringRequest) throws Exception;
	MentoringRequest updateCompleted(MentoringRequest mentoringRequest) throws Exception;
	MentoringRequest updateRejected(MentoringRequest mentoringRequest) throws Exception;
	MentoringRequest updateCanceled(MentoringRequest mentoringRequest) throws Exception;
	MentoringRequest updateForceClosed(MentoringRequest mentoringRequest) throws Exception;
	List<MentoringRequest> selectMentoringRequestAll();
}