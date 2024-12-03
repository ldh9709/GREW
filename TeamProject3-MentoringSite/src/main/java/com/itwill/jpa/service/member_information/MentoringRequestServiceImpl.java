package com.itwill.jpa.service.member_information;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.entity.chatting_review.MentoringRequest;
import com.itwill.jpa.repository.member_information.MentoringRequestRepository;

@Service
public class MentoringRequestServiceImpl implements MentoringRequestService {
	@Autowired
	private MentoringRequestRepository mentoringRequestRepository;
	
	/*활동 상태 찾기*/
	@Override
	public MentoringRequest getMentoringRequest(Long requestNo) {
		return mentoringRequestRepository.findByrequestNo(requestNo);
	}
	
	@Override
	public MentoringRequest saveMentoringRequest(MentoringRequest mentoringRequest) {
		return mentoringRequestRepository.save(mentoringRequest);
	}
	/*활동 상태 변경*/
	@Override
	public MentoringRequest updateMentoringRequest(MentoringRequest mentoringRequest) throws Exception {
		if (mentoringRequestRepository.findById(mentoringRequest.getRequestNo()).isPresent()) {
			return mentoringRequestRepository.save(mentoringRequest);
		}
		return new MentoringRequest();
	}
	/*내 활동 리스트 출력*/
	@Override
	public List<MentoringRequest> selectMentoringRequestAll() {
		return mentoringRequestRepository.findAll();
	}
}
