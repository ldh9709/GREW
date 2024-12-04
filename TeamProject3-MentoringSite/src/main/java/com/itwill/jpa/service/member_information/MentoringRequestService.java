package com.itwill.jpa.service.member_information;

import java.util.List;

import com.itwill.jpa.dto.chatting_review.MentoringRequestDto;
import com.itwill.jpa.entity.chatting_review.MentoringRequest;

public interface MentoringRequestService {
	MentoringRequestDto getMentoringRequest(Long requestNo);
	MentoringRequest saveMentoringRequest(MentoringRequestDto mentoringRequestDto);
	MentoringRequest updateActive(Long requestNo) throws Exception;
	MentoringRequest updateCompleted(Long requestNo) throws Exception;
	MentoringRequest updateRejected(Long requestNo) throws Exception;
	MentoringRequest updateCanceled(Long requestNo) throws Exception;
	MentoringRequest updateForceClosed(Long requestNo) throws Exception;
	List<MentoringRequestDto> selectMentoringRequestAll();
}