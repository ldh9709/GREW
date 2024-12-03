package com.itwill.jpa.repository.bullentin_board;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.bullentin_board.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{
	
}
