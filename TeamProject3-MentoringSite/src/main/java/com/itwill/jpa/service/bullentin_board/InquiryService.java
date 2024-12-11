package com.itwill.jpa.service.bullentin_board;


import org.springframework.data.domain.Page;

import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.util.ClientIp;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

public interface InquiryService {
	//질문작성
	InquiryDto createInquiry(InquiryDto inquiryDto);
	//질문수정
	InquiryDto updateInquiry(InquiryDto inquiryDto) throws Exception;
	//질문삭제
	InquiryDto deleteInquiry(Long inquiryNo) throws Exception;
	//질문보기
	InquiryDto getInquiry(Long InquiryNo);
	//조회수증가
	InquiryDto increaseViewInquiry(Long inquiryNo, String ipAddress) throws Exception;
	//IP체크
	String ipcheck(HttpServletRequest httpServletRequest);
	/*카테고리별 질문*/
	//답변갯수순
	Page<InquiryDto> getByCategoryInquiryOrderByAnswer(Long categoryNo,int pageNumber, int pageSize);
	//조회순
	Page<InquiryDto> getByCategoryInquiryOrderByView(Long categoryNo,int pageNumber, int pageSize);
	/*전체질문*/
	//답변갯수순
	Page<InquiryDto> getByAllInquiryOrderByAnswer(int pageNumber, int pageSize);
	//조회순
	Page<InquiryDto> getByAllInquiryOrderByView(int pageNumber, int pageSize);
	
	//검색
	Page<InquiryDto> getInquiryBySearch(String search,int pageNumber, int pageSize);
	
	//내가 작성한 질문내역
	Page<InquiryDto> getInquiryByMember(Long memberNo, int pageNumber, int pageSize);
	
}
