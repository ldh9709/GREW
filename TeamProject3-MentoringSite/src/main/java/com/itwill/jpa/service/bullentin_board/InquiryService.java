package com.itwill.jpa.service.bullentin_board;


import org.springframework.data.domain.Page;

import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.util.ClientIp;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

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
	InquiryDto increaseViewInquiry(InquiryDto inquiryDto, String ipAddress) throws Exception;
	//IP체크
	String ipcheck(HttpServletRequest httpServletRequest);
	/*카테고리별 질문*/
	//답변갯수순
	Page<InquiryDto> findByCategoryInquiryOrderByAnswer(Long categoryNo,int pageNumber, int pageSize);
	//조회순
	Page<InquiryDto> findByCategoryInquiryOrderByView(Long categoryNo,int pageNumber, int pageSize);
	/*전체질문*/
	//답변갯수순
	Page<InquiryDto> findByAllInquiryOrderByAnswer(int pageNumber, int pageSize);
	//조회순
	Page<InquiryDto> findByAllInquiryOrderByView(int pageNumber, int pageSize);
	
	//검색
	Page<InquiryDto> findInquiryBySearch(String search,int pageNumber, int pageSize);
}
