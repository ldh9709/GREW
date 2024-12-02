package com.itwill.jpa.service;

import java.util.List;

import com.itwill.jpa.dto.bulletin_board.AnswerDto;

public interface AnswerService {
	//답변작성
	AnswerDto saveAnswer(AnswerDto answerDto);
	//답변수정
	AnswerDto updateAnswer(AnswerDto answerDto) throws Exception;
	//답변삭제
	void deleteAnswer(Long answerNo) throws Exception;
	//질문에 대한 답변 목록 inactive 조건 추가해야함.
	List<AnswerDto> selectAnswerByInquiryNo(Long inquiryNo);
	//전체 답변 목록 inactive 조건 추가해야함.
	List<AnswerDto> selectAnswerAll();
}
