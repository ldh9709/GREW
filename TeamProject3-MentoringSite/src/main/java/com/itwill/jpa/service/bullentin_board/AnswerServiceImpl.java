package com.itwill.jpa.service.bullentin_board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.entity.bullentin_board.Answer;
import com.itwill.jpa.repository.alarm.AnswerRepository;
@Service
public class AnswerServiceImpl implements AnswerService{
	
	private AnswerRepository answerRepository;
	
	/*답변등록*/
	@Override
	public AnswerDto saveAnswer(AnswerDto answerDto){
		return AnswerDto.toDto(answerRepository.save(Answer.toEntity(answerDto)));
	
	}
	/*답변수정*/
	@Override
	public AnswerDto updateAnswer(AnswerDto answerDto) throws Exception{
		return AnswerDto.toDto(answerRepository.save(Answer.toEntity(answerDto)));
	}
	/*답변삭제*/
	@Override
	public AnswerDto deleteAnswer(AnswerDto answerDto) throws Exception {
		Answer answer = answerRepository.findById(answerDto.getAnswerNo()).get();
	    answer.setAnswerStatus(2);  
	    return AnswerDto.toDto(answerRepository.save(answer));
	}
	/*질문 하나에 달린 답변*/
	/*추천순*/
	@Override
	public List<AnswerDto> findByInquiryAnswerOrderByVotes(Long inquiryNo) {
		List<Answer> answerEntityList = 
				answerRepository.findByInquiryAnswerOrderByVotes(inquiryNo);
		List<AnswerDto> answerDtoList = new ArrayList<>();
		for(Answer answerEntity:answerEntityList) {
			answerDtoList.add(AnswerDto.toDto(answerEntity));
		}
		return answerDtoList;
	}
	/*최신순*/
	@Override
	public List<AnswerDto> findByInquiryAnswerOrderByDate(Long inquiryNo) {
		List<Answer> answerEntityList =
				answerRepository.findByInquiryAnswerOrderByDate(inquiryNo);
		List<AnswerDto> answerDtoList = new ArrayList<>();
		for(Answer answerEntity:answerEntityList) {
			answerDtoList.add(AnswerDto.toDto(answerEntity));
		}
		return answerDtoList;
	}
	/*카테고리 별 답변*/
	/*추천순*/
	@Override
	public List<AnswerDto> findByCategoryAnswerOrderByVotes(Long categoryNo) {
		List<Answer> answerEntityList = 
				answerRepository.findByCategoryAnswerOrderByVotes(categoryNo);
		List<AnswerDto> answerDtoList = new ArrayList<>();
		for(Answer answerEntity:answerEntityList) {
			answerDtoList.add(AnswerDto.toDto(answerEntity));
		}
		return answerDtoList;
	}
	/*조회순*/
	@Override
	public List<AnswerDto> findByCategoryAnswerOrderByDate(Long categoryNo) {
		List<Answer> answerEntityList = 
				answerRepository.findByCategoryAnswerOrderByDate(categoryNo);
		List<AnswerDto> answerDtoList = new ArrayList<>();
		for(Answer answerEntity:answerEntityList) {
			answerDtoList.add(AnswerDto.toDto(answerEntity));
		}
		return answerDtoList;
	}
	/*최근 3일간 추천 많은 답변*/
	@Override
	public List<AnswerDto> findByAnswerOrderByVoteDate() {
		List<Answer> answerEntityList = 
				answerRepository.findByAnswerOrderByVoteDate();
		List<AnswerDto> answerDtoList = new ArrayList<>();
		for(Answer answerEntity:answerEntityList) {
			answerDtoList.add(AnswerDto.toDto(answerEntity));
		}
		return answerDtoList;
	}
	
	
	

	

}
