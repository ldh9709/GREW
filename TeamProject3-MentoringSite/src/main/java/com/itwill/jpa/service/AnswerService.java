package com.itwill.jpa.service;

import java.util.List;

import com.itwill.jpa.dto.bulletin_board.AnswerDto;

public interface AnswerService {
	//답변작성
	AnswerDto saveAnswer(AnswerDto answerDto);
	//답변수정
	AnswerDto updateAnswer(AnswerDto answerDto) throws Exception;
	//답변삭제
	AnswerDto deleteAnswer(AnswerDto answerDto) throws Exception;
	/*질문하나에달린답변*/
	//추천순
	List<AnswerDto> findByInquiryAnswerOrderByVotes(Long inquiryNo);
	//조회순
	List<AnswerDto> findByInquiryAnswerOrderByDate(Long inquiryNo);
	/*카테고리별 답변*/
	//추천순
	List<AnswerDto> findByCategoryAnswerOrderByVotes(Long categoryNo);
	//조회순
	List<AnswerDto> findByCategoryAnswerOrderByDate(Long categoryNo);
	//최근3일간 상위추천 답변
	List<AnswerDto> findByAnswerOrderByVoteDate();
}
