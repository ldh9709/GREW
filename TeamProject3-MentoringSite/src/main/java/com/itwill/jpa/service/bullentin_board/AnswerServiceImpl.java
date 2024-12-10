package com.itwill.jpa.service.bullentin_board;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		return AnswerDto.toDto(answerRepository.save(answer));
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
	public Page<AnswerDto> findByInquiryAnswerOrderByVotes(Long inquiryNo,int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Answer> answerEntityList = 
				answerRepository.findByInquiryAnswerOrderByVotes(inquiryNo,pageable);
		List<AnswerDto> answerDtoList = new ArrayList<>();
		for(Answer answerEntity:answerEntityList) {
			answerDtoList.add(AnswerDto.toDto(answerEntity));
		}
		return new PageImpl<>(answerDtoList, pageable, answerEntityList.getTotalElements());
	}
	
	/*최신순*/
	@Override
	public Page<AnswerDto> findByInquiryAnswerOrderByDate(Long inquiryNo,int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Answer> answerEntityList =
				answerRepository.findByInquiryAnswerOrderByDate(inquiryNo,pageable);
		List<AnswerDto> answerDtoList = new ArrayList<>();
		for(Answer answerEntity:answerEntityList) {
			answerDtoList.add(AnswerDto.toDto(answerEntity));
		}
		return new PageImpl<>(answerDtoList, pageable, answerEntityList.getTotalElements());
	}
	
	/*카테고리 별 답변*/
	/*추천순*/
	@Override
	public Page<AnswerDto> findByCategoryAnswerOrderByVotes(Long categoryNo,int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Answer> answerEntityList = 
				answerRepository.findByCategoryAnswerOrderByVotes(categoryNo,pageable);
		List<AnswerDto> answerDtoList = new ArrayList<>();
		for(Answer answerEntity:answerEntityList) {
			answerDtoList.add(AnswerDto.toDto(answerEntity));
		}
		return new PageImpl<>(answerDtoList, pageable, answerEntityList.getTotalElements());
	}
	
	/*최신순*/
	@Override
	public Page<AnswerDto> findByCategoryAnswerOrderByDate(Long categoryNo,int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Answer> answerEntityList = 
				answerRepository.findByCategoryAnswerOrderByDate(categoryNo,pageable);
		List<AnswerDto> answerDtoList = new ArrayList<>();
		for(Answer answerEntity:answerEntityList) {
			answerDtoList.add(AnswerDto.toDto(answerEntity));
		}
		return new PageImpl<>(answerDtoList, pageable, answerEntityList.getTotalElements());
	}
	
	/*최근 3일간 추천 많은 답변*/
	@Override
	public Page<AnswerDto> findByAnswerOrderByVoteDate(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Answer> answerEntityList = 
				answerRepository.findByAnswerOrderByVoteDate(pageable);
		List<AnswerDto> answerDtoList = new ArrayList<>();
		for(Answer answerEntity:answerEntityList) {
			answerDtoList.add(AnswerDto.toDto(answerEntity));
		}
		return new PageImpl<>(answerDtoList, pageable, answerEntityList.getTotalElements());
	}

	
	
	
	
	

	

}
