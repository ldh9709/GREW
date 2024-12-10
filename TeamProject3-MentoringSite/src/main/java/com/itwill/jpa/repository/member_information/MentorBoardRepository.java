package com.itwill.jpa.repository.member_information;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.bullentin_board.Inquiry;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.member_information.MentorBoard;
import com.itwill.jpa.entity.member_information.MentorProfile;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MentorBoardRepository extends JpaRepository<MentorBoard, Long> {

	   // 특정 사용자와 관련된 게시글 조회
    List<MentorBoard> findByMember(Member member);
 
    // 조회수순
 	@Query("SELECT i FROM MentorBoard i ORDER BY i. mentorBoardViews DESC")
 	Page<MentorBoard> findAllMentorBoardOrderByView(Pageable pageable);

 
 	//검색기능
  	@Query("SELECT i FROM MentorBoard i "
  		     + "JOIN i.member m "
  		     + "WHERE (i.mentorBoardTitle LIKE %:search% "
  		     + "OR i.mentorBoardContent LIKE %:search% "
  		     + "OR m.memberName LIKE %:search%) "
  		     + "AND i.mentorBoardStatus = 1")
  		List<MentorBoard> findMentorBoardBySearch(@Param("search") String search);
}
