package com.itwill.jpa.service.bullentin_board;

import com.itwill.jpa.dto.bulletin_board.VoteDto;
import com.itwill.jpa.entity.bullentin_board.Vote;
import com.itwill.jpa.repository.bullentin_board.VoteRepository;

public class VoteServiceImpl implements VoteService{
	private VoteRepository voteRepository;
	//추천
	@Override
	public VoteDto UpVote(VoteDto voteDto) {
		voteDto.setVoteType(1);
		return VoteDto.toDto(voteRepository.save(Vote.toEntity(voteDto)));
	}

	//비추천
	@Override
	public VoteDto DownVote(VoteDto voteDto) {
		voteDto.setVoteType(2);
		return VoteDto.toDto(voteRepository.save(Vote.toEntity(voteDto)));
	}
	
	
}
