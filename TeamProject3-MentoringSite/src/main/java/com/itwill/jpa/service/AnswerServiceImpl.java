package com.itwill.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.entity.bullentin_board.Answer;
import com.itwill.jpa.repository.AnswerRepository;
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
	public void deleteAnswer(Long answerNo) throws Exception{
		answerRepository.deleteById(answerNo);
	}
	/*질문 하나에 달린 답변*/
	@Override
	public List<AnswerDto> selectAnswerByInquiryNo(Long inquiryNo) {
		List<Answer> answerEntityList = answerRepository.findByInquiryInquiryNo(inquiryNo);
		List<AnswerDto> answerDtoList = new ArrayList<>();
		for(Answer answerEntity:answerEntityList) {
			answerDtoList.add(AnswerDto.toDto(answerEntity));
		}
		return answerDtoList;
	}
	/*전체답변*/
	@Override
	public List<AnswerDto> selectAnswerAll() {
		List<Answer> answerEntityList = answerRepository.findAll();
		List<AnswerDto> answerDtoList = new ArrayList<>();
		for(Answer answerEntity:answerEntityList) {
			answerDtoList.add(AnswerDto.toDto(answerEntity));
		}
		return answerDtoList;
	}
	

	

}
