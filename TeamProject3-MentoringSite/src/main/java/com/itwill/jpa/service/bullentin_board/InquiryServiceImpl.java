package com.itwill.jpa.service.bullentin_board;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.dto.bulletin_board.InquiryIpViewDto;
import com.itwill.jpa.dto.member_information.CategoryRequestDto;
import com.itwill.jpa.entity.bullentin_board.Inquiry;
import com.itwill.jpa.entity.bullentin_board.InquiryIpView;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.repository.bullentin_board.InquiryIpViewRepository;
import com.itwill.jpa.repository.bullentin_board.InquiryRepository;
import com.itwill.jpa.repository.member_information.CategoryRepository;
import com.itwill.jpa.util.ClientIp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class InquiryServiceImpl implements InquiryService {
	@Autowired
	private InquiryRepository inquiryRepository;
	@Autowired
	private InquiryIpViewRepository inquiryIpViewRepository; // Repository를 통해 조회 기록을 관리

	private final ClientIp clientIpUtil = new ClientIp();// ip
	// 질문등록

	@Override
	public InquiryDto saveInquiry(InquiryDto inquiryDto) {
		
		return InquiryDto.toDto(inquiryRepository.save(Inquiry.toEntity(inquiryDto)));
	}

	// 질문수정
	@Override
	public InquiryDto updateInquiry(InquiryDto inquiryDto) throws Exception {
		Inquiry inquiry = inquiryRepository.findByInquiryNo(inquiryDto.getInquiryNo());
		inquiry.setInquiryTitle(inquiryDto.getInquiryTitle());
		inquiry.setInquiryContent(inquiryDto.getInquiryContent());
		return InquiryDto.toDto(inquiryRepository.save(inquiry));

	}

	// 질문삭제
	@Override
	public InquiryDto deleteInquiry(InquiryDto inquiryDto) throws Exception {
		Inquiry inquiry = inquiryRepository.findById(inquiryDto.getInquiryNo()).get();
		inquiry.setInquiryStatus(2);
		return InquiryDto.toDto(inquiryRepository.save(inquiry));
	}

	// 질문보기
	@Override
	public InquiryDto getInquiry(Long InquiryNo) {
		return InquiryDto.toDto(inquiryRepository.findByInquiryNo(InquiryNo));
	}

	// 조회수 증가 제한: IP별로 일정 시간 내에 조회수 증가 제한
	@Override
	public InquiryDto increaseViewInquiry(InquiryDto inquiryDto, String ipAddress) throws Exception {
		// Inquiry 조회
		Inquiry inquiry = inquiryRepository.findById(inquiryDto.getInquiryNo()).orElse(null);

		// IP 조회 기록을 DB에서 확인
		InquiryIpView lastView = inquiryIpViewRepository.findByIpAddressAndInquiry_InquiryNo(ipAddress,
				inquiryDto.getInquiryNo());

		long currentTime = System.currentTimeMillis();
		long lastViewTime = lastView != null
				? lastView.getLastViewed().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
				: 0;

		// 조회수 증가 제한 시간 (1시간)
		if (lastViewTime != 0 && currentTime - lastViewTime < 30 * 60 * 1000) {
			System.out.println("제한시간이 지나지않음");
		} else {
			// 조회수 증가
			inquiry.setInquiryViews(inquiry.getInquiryViews() + 1);
			inquiryRepository.save(inquiry);

			// IP 조회 기록 업데이트
			InquiryIpViewDto updatedIpView = new InquiryIpViewDto(null, ipAddress, inquiry.getInquiryNo(),
					LocalDateTime.now());
			inquiryIpViewRepository.save(InquiryIpView.toEntity(updatedIpView));
		}

		// DTO로 변환하여 반환
		return InquiryDto.toDto(inquiry);
	}

	/** 카테고리별 **/
	// 답변갯수순
	@Override
	public Page<InquiryDto> findByCategoryInquiryOrderByAnswer(Long categoryNo, int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Inquiry> inquiryEntityList = inquiryRepository.findByCategoryInquiryOrderByAnswer(categoryNo, pageable);
		List<InquiryDto> inquiryDtoList = new ArrayList<>();
		for (Inquiry inquiryEntity : inquiryEntityList) {
			inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
		}
		return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
	}

	// 조회순
	@Override
	public Page<InquiryDto> findByCategoryInquiryOrderByView(Long categoryNo, int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Inquiry> inquiryEntityList = inquiryRepository.findByCategoryInquiryOrderByView(categoryNo, pageable);
		List<InquiryDto> inquiryDtoList = new ArrayList<>();
		for (Inquiry inquiryEntity : inquiryEntityList) {
			inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
		}
		return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
	}

	/** 전체질문 **/
	// 답변갯수순
	@Override
	public Page<InquiryDto> findByAllInquiryOrderByAnswer(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Inquiry> inquiryEntityList = inquiryRepository.findAllInquiriOrderByAnswer(pageable);
		List<InquiryDto> inquiryDtoList = new ArrayList<>();
		for (Inquiry inquiryEntity : inquiryEntityList) {
			inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
		}
		return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
	}

	// 조회순
	@Override
	public Page<InquiryDto> findByAllInquiryOrderByView(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Inquiry> inquiryEntityList = inquiryRepository.findAllInquiryOrderByView(pageable);
		List<InquiryDto> inquiryDtoList = new ArrayList<>();
		for (Inquiry inquiryEntity : inquiryEntityList) {
			inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
		}

		// PageImpl을 사용해 Page<InquiryDto> 반환
		return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
	}

	// 검색기능
	@Override
	public Page<InquiryDto> findInquiryBySearch(String search, int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Inquiry> inquiryEntityList = inquiryRepository.findInquiryBySearch(search, pageable);
		List<InquiryDto> inquiryDtoList = new ArrayList<>();
		for (Inquiry inquiryEntity : inquiryEntityList) {
			inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
		}
		return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
	}

	// ip중복제거
	@Override
	public String ipcheck(HttpServletRequest httpServletRequest) {
		String clientIp = clientIpUtil.getClientIp(httpServletRequest);
		return clientIp;
	}

	@Override
	public InquiryDto increaseViewInquiry(InquiryDto inquiryDto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
