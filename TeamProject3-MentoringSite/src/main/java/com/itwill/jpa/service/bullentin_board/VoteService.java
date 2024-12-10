package com.itwill.jpa.service.bullentin_board;

import com.itwill.jpa.dto.bulletin_board.VoteDto;

public interface VoteService {
	//추천
	VoteDto upVote(VoteDto voteDto);
	//비추천
	VoteDto downVote(VoteDto voteDto);
	//추천-비추천 값계산을 위한 답변의 추천 비추천 갯수 구하기
	public Integer calcVotes(Long answerNo); 
}
