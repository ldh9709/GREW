package com.itwill.jpa.service.bullentin_board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.entity.bullentin_board.Inquiry;
import com.itwill.jpa.repository.bullentin_board.InquiryRepository;

import jakarta.transaction.Transactional;
@Transactional
@Service
public class InquiryServiceImpl implements InquiryService {
	@Autowired
	private InquiryRepository inquiryRepository;
	//질문등록
	@Override
	public InquiryDto saveInquiry(InquiryDto inquiryDto) {
		return InquiryDto.toDto(inquiryRepository.save(Inquiry.toEntity(inquiryDto)));
	}
	//질문수정
	@Override
	public InquiryDto updateInquiry(InquiryDto inquiryDto) throws Exception {
		return InquiryDto.toDto(inquiryRepository.save(Inquiry.toEntity(inquiryDto)));
	}
	//질문삭제
	@Override
	public InquiryDto deleteInquiry(InquiryDto inquiryDto) throws Exception {
		Inquiry inquiry = inquiryRepository.findById(inquiryDto.getInquiryNo()).get();
		inquiry.setInquiryStatus(2);
		return InquiryDto.toDto(inquiryRepository.save(inquiry));
	}
	//조회수증가
	@Override
	public InquiryDto increaseViewInquiry(InquiryDto inquiryDto) throws Exception {
        Inquiry inquiry = inquiryRepository.findById(inquiryDto.getInquiryNo()).get();
        inquiry.setInquiryViews(inquiry.getInquiryViews() + 1);
        return InquiryDto.toDto(inquiryRepository.save(inquiry));  
	}
	
	/**카테고리별**/
	//답변갯수순
	@Override
	public List<InquiryDto> findByCategoryInquiryOrderByAnswer(Long CategoryNo) {
		List<Inquiry> inquiryEntityList =
				inquiryRepository.findByCategoryInquiryOrderByAnswer(CategoryNo);
		List<InquiryDto> inquiryDtoList = new ArrayList<>();
		for(Inquiry inquiryEntity:inquiryEntityList) {
			inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
		}
		return inquiryDtoList;
	}
	//조회순
	@Override
	public List<InquiryDto> findByCategoryInquiryOrderByView(Long CategoryNo) {
		List<Inquiry> inquiryEntityList =
				inquiryRepository.findByCategoryInquiryOrderByView(CategoryNo);
		List<InquiryDto> inquiryDtoList = new ArrayList<>();
		for(Inquiry inquiryEntity:inquiryEntityList) {
			inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
		}
		return inquiryDtoList;
	}
	/**전체질문**/
	//답변갯수순
	@Override
	public List<InquiryDto> findByAllInquiryOrderByAnswer() {
		List<Inquiry> inquiryEntityList =
				inquiryRepository.findAllInquiriOrderByAnswer();
		List<InquiryDto> inquiryDtoList = new ArrayList<>();
		for(Inquiry inquiryEntity:inquiryEntityList) {
			inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
		}
		return inquiryDtoList;
	}
	//조회순
	@Override
	public List<InquiryDto> findByAllInquiryOrderByView() {
		List<Inquiry> inquiryEntityList =
				inquiryRepository.findAllInquiryOrderByView();
		List<InquiryDto> inquiryDtoList = new ArrayList<>();
		for(Inquiry inquiryEntity:inquiryEntityList) {
			inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
		}
		return inquiryDtoList;
	}
}
