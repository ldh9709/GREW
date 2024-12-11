package com.itwill.jpa.service.bullentin_board;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.bulletin_board.VoteDto;
import com.itwill.jpa.entity.bullentin_board.Vote;
import com.itwill.jpa.repository.bullentin_board.VoteRepository;

import jakarta.transaction.Transactional;
@Transactional
@Service
public class VoteServiceImpl implements VoteService{
	@Autowired
	private VoteRepository voteRepository;
	//추천
	@Override
	public VoteDto upVote(VoteDto voteDto) {
		// 유저가 이미 비추천을 한 경우
	    Optional<Vote> existingDownVote = 
	    		voteRepository.findByMemberMemberNoAndAnswerAnswerNoAndVoteType(
	    				voteDto.getMemberNo(), 
	    				voteDto.getAnswerNo(), 
	    				2);
	    if (existingDownVote.isPresent()) {
	        throw new IllegalArgumentException("비추천을 이미 하셨으므로 추천은 할 수 없습니다.");
	    }
	    
	    // 유저가 이미 추천을 한 경우
	    Optional<Vote> existingUpVote = 
	    		voteRepository.findByMemberMemberNoAndAnswerAnswerNoAndVoteType(
	    				voteDto.getMemberNo(), 
	    				voteDto.getAnswerNo(), 
	    				1);
	    if (existingUpVote.isPresent()) {
	        throw new IllegalArgumentException("이미 추천을 하셨습니다.");
	    }
	    
	    // 추천을 하지 않았다면 추천 기록을 저장
	    voteDto.setVoteType(1);
	    return VoteDto.toDto(voteRepository.save(Vote.toEntity(voteDto)));
	}

	//비추천
	@Override
	public VoteDto downVote(VoteDto voteDto) {
		// 유저가 이미 추천을 한 경우
	    Optional<Vote> existingUpVote = 
	    		voteRepository.findByMemberMemberNoAndAnswerAnswerNoAndVoteType(
	    				voteDto.getMemberNo(),
	    				voteDto.getAnswerNo(), 
	    				1);
	    if (existingUpVote.isPresent()) {
	        throw new IllegalArgumentException("추천을 이미 하셨으므로 비추천은 할 수 없습니다.");
	    }
	    
	    // 유저가 이미 비추천을 한 경우
	    Optional<Vote> existingDownVote = 
	    		voteRepository.findByMemberMemberNoAndAnswerAnswerNoAndVoteType(
	    				voteDto.getMemberNo(), 
	    				voteDto.getAnswerNo(), 
	    				2);
	    if (existingDownVote.isPresent()) {
	        throw new IllegalArgumentException("이미 비추천을 하셨습니다.");
	    }
	    
	    // 비추천을 하지 않았다면 비추천 기록을 저장
	    voteDto.setVoteType(2);
	    return VoteDto.toDto(voteRepository.save(Vote.toEntity(voteDto)));
	}
	//추천-비추천 값계산을 위한 답변의 추천 비추천 갯수 구하기
	@Override
	public Integer calcVotes(Long answerNo) {
		// 추천 수 구하기
	    Long upVotes = voteRepository.countByAnswerAnswerNoAndVoteType(answerNo, 1);  // 1은 추천
	    // 비추천 수 구하기
	    Long downVotes = voteRepository.countByAnswerAnswerNoAndVoteType(answerNo, 2);  // 2는 비추천

	    // 추천에서 비추천을 뺀 값
	    return upVotes.intValue() - downVotes.intValue();
	}
	
	
}
