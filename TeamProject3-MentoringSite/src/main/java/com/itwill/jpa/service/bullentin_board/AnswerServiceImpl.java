package com.itwill.jpa.service.bullentin_board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.entity.bullentin_board.Answer;
import com.itwill.jpa.exception.CustomException;
import com.itwill.jpa.repository.bullentin_board.AnswerRepository;
import com.itwill.jpa.repository.bullentin_board.InquiryRepository;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;

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
	public AnswerDto createAnswer(AnswerDto answerDto){
		
		try {
            Answer answer = Answer.toEntity(answerDto);
            return AnswerDto.toDto(answerRepository.save(answer));
        } catch (Exception e) {
            throw new CustomException(ResponseStatusCode.CREATED_ANSWER_FAIL, ResponseMessage.CREATED_ANSWER_FAIL);
        }
	}
	
	/*답변수정*/
	@Override
	public AnswerDto updateAnswer(AnswerDto answerDto) throws Exception{
		
		Answer answer = answerRepository.findById(answerDto.getAnswerNo())
                .orElseThrow(() -> new CustomException(ResponseStatusCode.ACCEPT_ANSWER_FAIL, ResponseMessage.ACCEPT_ANSWER_FAIL));
		
		answer.setAnswerContent(answerDto.getAnswerContent());
		answer.setAnswerDate(LocalDateTime.now());
		return AnswerDto.toDto(answerRepository.save(answer));
	}
	
	/*답변채택*/
	@Override
	public AnswerDto acceptAnswer(Long answerNo) throws Exception {
		
		//답변이 존재하는 확인
		Answer answer = answerRepository.findById(answerNo)
                .orElseThrow(() -> new CustomException(ResponseStatusCode.ACCEPT_ANSWER_FAIL, ResponseMessage.ACCEPT_ANSWER_FAIL));
		
		//이미 채택된 답변이 있는지 확인
	    Answer acceptedAnswer = answerRepository.findAcceptedAnswerByInquiry(answer.getInquiry().getInquiryNo());
	    if (acceptedAnswer != null) {
	    	// 예외를 던질 때 사용자 정의 exception 사용
	        throw new CustomException(
	        		ResponseStatusCode.ACCEPT_ANSWER_FAIL,
	        		ResponseMessage.ACCEPT_ANSWER_FAIL);
	    }
		
		answer.setAnswerAccept(2);
		return AnswerDto.toDto(answerRepository.save(answer));
	}
	
	/*답변삭제*/
	@Override
	public AnswerDto deleteAnswer(Long answerNo) throws Exception {
		//답변이 존재하는지 확인
		Answer answer = answerRepository.findById(answerNo)
                .orElseThrow(() -> new CustomException(ResponseStatusCode.DELETE_ANSWER_FAIL, ResponseMessage.DELETE_ANSWER_FAIL));
		
	    answer.setAnswerStatus(2);  //삭제상태로 변경
	    return AnswerDto.toDto(answerRepository.save(answer));
	}
	
	
	/* 답변상세보기 */
	@Override
	public AnswerDto getAnswer(Long answerNo) {
		return AnswerDto.toDto(answerRepository.findById(answerNo).get());
	}
	
	/*질문 하나에 달린 답변*/
	/*추천순*/
	@Override
	public Page<AnswerDto> getByInquiryAnswerOrderByVotes(Long inquiryNo,int pageNumber, int pageSize) {
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
	public Page<AnswerDto> getByInquiryAnswerOrderByDate(Long inquiryNo,int pageNumber, int pageSize) {
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
	public Page<AnswerDto> getByCategoryAnswerOrderByVotes(Long categoryNo,int pageNumber, int pageSize) {
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
	public Page<AnswerDto> getByCategoryAnswerOrderByDate(Long categoryNo,int pageNumber, int pageSize) {
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
	public Page<AnswerDto> getByAnswerOrderByVoteDate(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Answer> answerEntityList = 
				answerRepository.findByAnswerOrderByVoteDate(pageable);
		List<AnswerDto> answerDtoList = new ArrayList<>();
		for(Answer answerEntity:answerEntityList) {
			answerDtoList.add(AnswerDto.toDto(answerEntity));
		}
		return new PageImpl<>(answerDtoList, pageable, answerEntityList.getTotalElements());
	}

	//내가 작성한 답변내역
	@Override
	public Page<AnswerDto> getAnswerByMember(Long memberNo, int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Answer> answerEntityList = 
				answerRepository.findByMemberMemberNoOrderByAnswerDateDesc(memberNo, pageable);
		List<AnswerDto> answerDtoList = new ArrayList<>();
		for(Answer answerEntity:answerEntityList) {
			answerDtoList.add(AnswerDto.toDto(answerEntity));
		}
		
		return new PageImpl<>(answerDtoList, pageable, answerEntityList.getTotalElements());
	}
	
	
	

	

}
