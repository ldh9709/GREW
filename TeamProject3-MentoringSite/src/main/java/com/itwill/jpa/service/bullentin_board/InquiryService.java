package com.itwill.jpa.service.bullentin_board;

import java.util.List;

import com.itwill.jpa.dto.bulletin_board.InquiryDto;

public interface InquiryService {
	//질문작성
	InquiryDto saveInquiry(InquiryDto inquiryDto);
	//질문수정
	InquiryDto updateInquiry(InquiryDto inquiryDto) throws Exception;
	//질문삭제
	InquiryDto deleteInquiry(InquiryDto inquiryDto) throws Exception;
	//질문보기
	InquiryDto getInquiry(Long InquiryNo);
	//조회수증가
	InquiryDto increaseViewInquiry(InquiryDto inquiryDto) throws Exception;
	
	/*카테고리별 질문*/
	//답변갯수순
	List<InquiryDto> findByCategoryInquiryOrderByAnswer(Long CategoryNo);
	//조회순
	List<InquiryDto> findByCategoryInquiryOrderByView(Long CategoryNo);
	/*전체질문*/
	//답변갯수순
	List<InquiryDto> findByAllInquiryOrderByAnswer();
	//조회순
	List<InquiryDto> findByAllInquiryOrderByView();
}
