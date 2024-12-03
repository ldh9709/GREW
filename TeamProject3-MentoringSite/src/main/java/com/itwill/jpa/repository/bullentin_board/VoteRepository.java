package com.itwill.jpa.repository.bullentin_board;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.bullentin_board.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{
	//추천/비추천을 눌렀는지 계산
	Optional<Vote> findByMemberMemberNoAndAnswerAnswerNoAndVoteType(Long memberNo, Long answerNo, int i);
	//추천에서 비추천을 뺀 값을 계산
	Long countByAnswerAnswerNoAndVoteType(Long answerNo, int voteType);
}
