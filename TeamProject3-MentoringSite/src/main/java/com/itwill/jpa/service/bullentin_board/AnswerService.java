package com.itwill.jpa.service.bullentin_board;

import java.util.List;

import org.springframework.data.domain.Page;

import com.itwill.jpa.dto.bulletin_board.AnswerDto;

public interface AnswerService {
	//답변작성
	AnswerDto createAnswer(AnswerDto answerDto,Long inquiryNo);
	//답변수정
	AnswerDto updateAnswer(AnswerDto answerDto) throws Exception;
	//답변삭제
	AnswerDto deleteAnswer(Long answerNo) throws Exception;
	//답변채택
	AnswerDto acceptAnswer(Long answerNo) throws Exception;
	/*질문하나에달린답변*/
	//답변상세보기
	AnswerDto getAnswer(Long answerNo);
	//추천순
	Page<AnswerDto> getByInquiryAnswerOrderByVotes(Long inquiryNo,int pageNumber, int pageSize);
	//최신순
	Page<AnswerDto> getByInquiryAnswerOrderByDate(Long inquiryNo,int pageNumber, int pageSize);
	/*카테고리별 답변*/
	//추천순
	Page<AnswerDto> getByCategoryAnswerOrderByVotes(Long categoryNo,int pageNumber, int pageSize);
	//최신순
	Page<AnswerDto> getByCategoryAnswerOrderByDate(Long categoryNo,int pageNumber, int pageSize);
	//최근3일간 상위추천 답변
	List<AnswerDto> getByAnswerOrderByVoteDate();
	
	//내가 작성한 답변내역
	Page<AnswerDto> getAnswerByMember(Long MemberNo, int pageNumber, int pageSize);
	
	//답변 수 
	Long getAnswerCount(Long inquiryNo);
	//질문에 답변을 남겼는지 여부
	boolean inAnswerByMember(Long inquiryNo,Long memberNo);
	
}