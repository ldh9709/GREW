package com.itwill.jpa.service.bullentin_board;

import java.util.List;

import org.springframework.data.domain.Page;

import com.itwill.jpa.dto.bulletin_board.AnswerDto;

public interface AnswerService {
	//답변작성
	AnswerDto saveAnswer(AnswerDto answerDto);
	//답변수정
	AnswerDto updateAnswer(AnswerDto answerDto) throws Exception;
	//답변삭제
	AnswerDto deleteAnswer(AnswerDto answerDto) throws Exception;
	//답변채택
	AnswerDto acceptAnswer(AnswerDto answerDto) throws Exception;
	/*질문하나에달린답변*/
	//답변상세보기
	AnswerDto getAnswer(Long answerNo);
	//추천순
	Page<AnswerDto> findByInquiryAnswerOrderByVotes(Long inquiryNo,int pageNumber, int pageSize);
	//최신순
	Page<AnswerDto> findByInquiryAnswerOrderByDate(Long inquiryNo,int pageNumber, int pageSize);
	/*카테고리별 답변*/
	//추천순
	Page<AnswerDto> findByCategoryAnswerOrderByVotes(Long categoryNo,int pageNumber, int pageSize);
	//최신순
	Page<AnswerDto> findByCategoryAnswerOrderByDate(Long categoryNo,int pageNumber, int pageSize);
	//최근3일간 상위추천 답변
	Page<AnswerDto> findByAnswerOrderByVoteDate(int pageNumber, int pageSize);
}
