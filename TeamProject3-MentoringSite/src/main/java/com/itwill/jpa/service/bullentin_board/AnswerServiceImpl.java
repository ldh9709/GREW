package com.itwill.jpa.service.bullentin_board;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.entity.bullentin_board.Answer;
import com.itwill.jpa.repository.bullentin_board.AnswerRepository;
import com.itwill.jpa.repository.bullentin_board.InquiryRepository;

import jakarta.transaction.Transactional;
@Transactional
@Service
public class AnswerServiceImpl implements AnswerService{
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private InquiryRepository inquiryRepository;
	/*답변등록*/
	@Override
	public AnswerDto saveAnswer(AnswerDto answerDto){
		
		return AnswerDto.toDto(answerRepository.save(Answer.toEntity(answerDto)));
	}
	
	/*답변수정*/
	@Override
	public AnswerDto updateAnswer(AnswerDto answerDto) throws Exception{
		Answer answer = answerRepository.findById(answerDto.getAnswerNo()).get();
		answer.setAnswerContent(answerDto.getAnswerContent());
		return AnswerDto.toDto(answerRepository.save(Answer.toEntity(answerDto)));
	}
	
	/*답변채택*/
	@Override
	public AnswerDto acceptAnswer(AnswerDto answerDto) throws Exception {
		
		//이미 채택된 답변이 있는지 확인
	    Answer acceptedAnswer = answerRepository.findAcceptedAnswerByInquiry(answerDto.getInquiryNo());
	    if (acceptedAnswer != null) {
	    	// 예외를 던질 때 ResponseStatusException을 사용
	        throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 채택된 답변이 존재합니다.");
	    }
		
		Answer answer = answerRepository.findById(answerDto.getAnswerNo()).get();
		answer.setAnswerAccept(2);
		return AnswerDto.toDto(answerRepository.save(answer));
	}
	
	/*답변삭제*/
	@Override
	public AnswerDto deleteAnswer(AnswerDto answerDto) throws Exception {
		Answer answer = answerRepository.findById(answerDto.getAnswerNo()).get();
	    answer.setAnswerStatus(2);  
	    return AnswerDto.toDto(answerRepository.save(answer));
	}
	
	
	/* 답변상세보기 */
	@Override
	public AnswerDto getAnswer(Long answerNo) {
		return AnswerDto.toDto(answerRepository.findByAnswerNo(answerNo));
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
	
	/*최신순*/
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
