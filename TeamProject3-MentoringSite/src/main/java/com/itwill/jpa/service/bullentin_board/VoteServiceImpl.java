package com.itwill.jpa.service.bullentin_board;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.bulletin_board.VoteDto;
import com.itwill.jpa.entity.bullentin_board.Answer;
import com.itwill.jpa.entity.bullentin_board.Vote;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.bullentin_board.AnswerRepository;
import com.itwill.jpa.repository.bullentin_board.VoteRepository;
import com.itwill.jpa.repository.member_information.MemberRepository;

import jakarta.transaction.Transactional;
@Transactional
@Service
public class VoteServiceImpl implements VoteService{
	@Autowired
	private VoteRepository voteRepository;
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private MemberRepository memberRepository;
	//추천
	@Override
	public VoteDto upVote(Long answerNo, Long memberNo) {
		Answer answer = answerRepository.findById(answerNo).get();
		Member member = memberRepository.findById(memberNo).get(); 
		// 유저가 이미 비추천을 한 경우
	    Optional<Vote> existingDownVote = 
	    		voteRepository.findByMemberMemberNoAndAnswerAnswerNoAndVoteType(
	    				memberNo, 
	    				answerNo, 
	    				2);
	    if (existingDownVote.isPresent()) {
	        throw new IllegalArgumentException("비추천을 이미 하셨으므로 추천은 할 수 없습니다.");
	    }
	    
	    // 유저가 이미 추천을 한 경우
	    Optional<Vote> existingUpVote = 
	    		voteRepository.findByMemberMemberNoAndAnswerAnswerNoAndVoteType(
	    				memberNo,
	    				answerNo, 
	    				1);
	    if (existingUpVote.isPresent()) {
	        throw new IllegalArgumentException("이미 추천을 하셨습니다.");
	    }
	    Vote vote = Vote.builder()
	    		.voteDate(LocalDate.now())
	    		.voteType(1)
	    		.answer(answer)
	    		.member(member)
	    		.build();
	    // 추천을 하지 않았다면 추천 기록을 저장
	    return VoteDto.toDto(voteRepository.save(vote));
	}

	//비추천
	@Override
	public VoteDto downVote(Long answerNo, Long memberNo) {
		Answer answer = answerRepository.findById(answerNo).get();
		Member member = memberRepository.findById(memberNo).get(); 
		// 유저가 이미 추천을 한 경우
	    Optional<Vote> existingUpVote = 
	    		voteRepository.findByMemberMemberNoAndAnswerAnswerNoAndVoteType(
	    				memberNo, 
	    				answerNo, 
	    				1);
	    if (existingUpVote.isPresent()) {
	        throw new IllegalArgumentException("추천을 이미 하셨으므로 비추천은 할 수 없습니다.");
	    }
	    
	    // 유저가 이미 비추천을 한 경우
	    Optional<Vote> existingDownVote = 
	    		voteRepository.findByMemberMemberNoAndAnswerAnswerNoAndVoteType(
	    				memberNo, 
	    				answerNo, 
	    				2);
	    if (existingDownVote.isPresent()) {
	        throw new IllegalArgumentException("이미 비추천을 하셨습니다.");
	    }
	    Vote vote = Vote.builder()
	    		.voteDate(LocalDate.now())
	    		.voteType(2)
	    		.answer(answer)
	    		.member(member)
	    		.build();
	    // 비추천을 하지 않았다면 비추천 기록을 저장
	    return VoteDto.toDto(voteRepository.save(vote));
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
