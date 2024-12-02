package com.itwill.jpa.service;

import com.itwill.jpa.dto.bulletin_board.VoteDto;

public interface VoteService {
	//추천
	VoteDto UpVote(VoteDto voteDto);
	//비추천
	VoteDto DownVote(VoteDto voteDto);
}
