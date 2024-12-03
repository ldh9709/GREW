package com.itwill.jpa.service.member_information;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.chatting_review.MentoringRequestDto;
import com.itwill.jpa.entity.chatting_review.MentoringRequest;
import com.itwill.jpa.repository.member_information.MentoringRequestRepository;

@Service
public class MentoringRequestServiceImpl implements MentoringRequestService {
	@Autowired
	private MentoringRequestRepository mentoringRequestRepository;
	
	/*활동 요청*/
	@Override
	public MentoringRequest saveMentoringRequest(MentoringRequestDto mentoringRequestDto) {
		MentoringRequest mentoringRequest = MentoringRequest.toEntity(mentoringRequestDto);
		return mentoringRequestRepository.save(mentoringRequest);
	}
	/*활동 상태 확인*/
	@Override
	public MentoringRequest getMentoringRequest(Long requestNo) {
		return mentoringRequestRepository.findByrequestNo(requestNo);
	}
	/*활동 진행중*/
	@Override
	public MentoringRequest updateActive(MentoringRequest mentoringRequest) throws Exception {
		if (mentoringRequestRepository.findById(mentoringRequest.getRequestNo()).isPresent()) {
			return mentoringRequestRepository.save(mentoringRequest);
		}
		return new MentoringRequest();
	}
	/*활동 완료*/
	@Override
	public MentoringRequest updateCompleted(MentoringRequest mentoringRequest) throws Exception {
		if (mentoringRequestRepository.findById(mentoringRequest.getRequestNo()).isPresent()) {
			return mentoringRequestRepository.save(mentoringRequest);
		}
		return new MentoringRequest();
	}
	/*요청 거절*/
	@Override
	public MentoringRequest updateRejected(MentoringRequest mentoringRequest) throws Exception {
		if (mentoringRequestRepository.findById(mentoringRequest.getRequestNo()).isPresent()) {
			return mentoringRequestRepository.save(mentoringRequest);
		}
		return new MentoringRequest();
	}
	/*요청 취소*/
	@Override
	public MentoringRequest updateCanceled(MentoringRequest mentoringRequest) throws Exception {
		if (mentoringRequestRepository.findById(mentoringRequest.getRequestNo()).isPresent()) {
			return mentoringRequestRepository.save(mentoringRequest);
		}
		return new MentoringRequest();
	}
	/*강제 종료*/
	@Override
	public MentoringRequest updateForceClosed(MentoringRequest mentoringRequest) throws Exception {
		if (mentoringRequestRepository.findById(mentoringRequest.getRequestNo()).isPresent()) {
			return mentoringRequestRepository.save(mentoringRequest);
		}
		return new MentoringRequest();
	}
	/*본인 활동 리스트 출력*/
	@Override
	public List<MentoringRequest> selectMentoringRequestAll() {
		return mentoringRequestRepository.findAll();
	}
}
