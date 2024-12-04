package com.itwill.jpa.service.bullentin_board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	//질문보기
	@Override
	public InquiryDto getInquiry(Long InquiryNo) {
		return InquiryDto.toDto(inquiryRepository.findByInquiryNo(InquiryNo));
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
	public List<InquiryDto> findByCategoryInquiryOrderByAnswer(Long categoryNo) {
		List<Inquiry> inquiryEntityList =
				inquiryRepository.findByCategoryInquiryOrderByAnswer(categoryNo);
		List<InquiryDto> inquiryDtoList = new ArrayList<>();
		for(Inquiry inquiryEntity:inquiryEntityList) {
			inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
		}
		return inquiryDtoList;
	}
	//조회순
	@Override
	public List<InquiryDto> findByCategoryInquiryOrderByView(Long categoryNo) {
		List<Inquiry> inquiryEntityList =
				inquiryRepository.findByCategoryInquiryOrderByView(categoryNo);
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
	public Page<InquiryDto> findByAllInquiryOrderByView(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Inquiry> inquiryEntityList =
				inquiryRepository.findAllInquiryOrderByView(pageable);
		List<InquiryDto> inquiryDtoList = new ArrayList<>();
	    for (Inquiry inquiryEntity : inquiryEntityList) {
	        inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
	    }
	    
	    // PageImpl을 사용해 Page<InquiryDto> 반환
	    return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
	}
	//검색기능
	@Override
	public List<InquiryDto> findInquiryBySearch(String search) {
		List<Inquiry> inquiryEntityList=
				inquiryRepository.findInquiryBySearch(search);
		List<InquiryDto> inquiryDtoList = new ArrayList<>();
		for(Inquiry inquiryEntity:inquiryEntityList) {
			inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
		}
		return inquiryDtoList;
	}
	
}
