package com.itwill.jpa.service.member_information;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
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
	
	/*활동 요청(기본 상태 요청 중)*/
	@Override
	public MentoringRequest saveMentoringRequest(MentoringRequestDto mentoringRequestDto) {
		MentoringRequest mentoringRequest = MentoringRequest.toEntity(mentoringRequestDto);
		return mentoringRequestRepository.save(mentoringRequest);
	}
	/*활동 상태 확인*/
	@Override
	public MentoringRequestDto getMentoringRequest(Long requestNo) {
		MentoringRequest mentoringRequest = mentoringRequestRepository.findById(requestNo).get();
		MentoringRequestDto mentoringRequestDto = MentoringRequestDto.toDto(mentoringRequest);
		return mentoringRequestDto;
	}
	/*활동 진행중*/
	@Override
	public MentoringRequest updateActive(Long requestNo) throws Exception {
		if (mentoringRequestRepository.findById(requestNo).isPresent()) {
			MentoringRequest mentoringRequest = mentoringRequestRepository.findById(requestNo).get();
			mentoringRequest.setRequestStatus(7100);
			return mentoringRequestRepository.save(mentoringRequest);
		}
		return new MentoringRequest();
	}
	/*활동 완료*/
	@Override
	public MentoringRequest updateCompleted(Long requestNo) throws Exception {
		if (mentoringRequestRepository.findById(requestNo).isPresent()) {
			MentoringRequest mentoringRequest = mentoringRequestRepository.findById(requestNo).get();
			mentoringRequest.setRequestStatus(7200);
			mentoringRequest.setRequestEndDate(LocalDateTime.now());
			return mentoringRequestRepository.save(mentoringRequest);
		}
		return new MentoringRequest();
	}
	/*요청 거절*/
	@Override
	public MentoringRequest updateRejected(Long requestNo) throws Exception {
		if (mentoringRequestRepository.findById(requestNo).isPresent()) {
			MentoringRequest mentoringRequest = mentoringRequestRepository.findById(requestNo).get();
			mentoringRequest.setRequestStatus(7300);
			mentoringRequest.setRequestEndDate(LocalDateTime.now());
			return mentoringRequestRepository.save(mentoringRequest);
		}
		return new MentoringRequest();
	}
	/*요청 취소*/
	@Override
	public MentoringRequest updateCanceled(Long requestNo) throws Exception {
		if (mentoringRequestRepository.findById(requestNo).isPresent()) {
			MentoringRequest mentoringRequest = mentoringRequestRepository.findById(requestNo).get();
			mentoringRequest.setRequestStatus(7400);
			mentoringRequest.setRequestEndDate(LocalDateTime.now());
			return mentoringRequestRepository.save(mentoringRequest);
		}
		return new MentoringRequest();
	}
	/*강제 종료*/
	@Override
	public MentoringRequest updateForceClosed(Long requestNo) throws Exception {
		if (mentoringRequestRepository.findById(requestNo).isPresent()) {
			MentoringRequest mentoringRequest = mentoringRequestRepository.findById(requestNo).get();
			mentoringRequest.setRequestStatus(7500);
			mentoringRequest.setRequestEndDate(LocalDateTime.now());
			return mentoringRequestRepository.save(mentoringRequest);
		}
		return new MentoringRequest();
	}
	/*본인 활동 리스트 출력*/
	@Override
	public List<MentoringRequestDto> selectMentoringRequestAll() {
		List<MentoringRequest> mentoringRequests = mentoringRequestRepository.findAll();
		List<MentoringRequestDto> mentoringRequestDtos = new ArrayList<MentoringRequestDto>();
		for (int i = 0; i <mentoringRequests.size(); i++) {
			mentoringRequestDtos.add(MentoringRequestDto.toDto(mentoringRequests.get(i)));
		}
		return mentoringRequestDtos;
	}
}
