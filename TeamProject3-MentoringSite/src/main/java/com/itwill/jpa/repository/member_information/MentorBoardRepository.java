package com.itwill.jpa.repository.member_information;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.member_information.MentorBoard;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MentorBoardRepository extends JpaRepository<MentorBoard, Long> {

	   // 특정 사용자와 관련된 게시글 조회
    List<MentorBoard> findByMember(Member member);

  
}
