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

	 // 조회수 기준 정렬 페이징
    @Query("SELECT mb FROM MentorBoard mb WHERE mb.mentorBoardStatus = 1 ORDER BY mb.mentorBoardViews DESC")
    Page<MentorBoard> findAllMentorBoardOrderByView(Pageable pageable);

    // 검색 기능 페이징
    @Query("SELECT mb FROM MentorBoard mb JOIN mb.member m " +
           "WHERE (mb.mentorBoardTitle LIKE %:search% OR mb.mentorBoardContent LIKE %:search% OR m.memberName LIKE %:search%) " +
           "AND mb.mentorBoardStatus = 1")
    Page<MentorBoard> findMentorBoardBySearch(@Param("search") String search, Pageable pageable);

    // 날짜 기준 정렬 페이징
    @Query("SELECT mb FROM MentorBoard mb WHERE mb.mentorBoardStatus = 1 ORDER BY mb.mentorBoardDate DESC")
    Page<MentorBoard> findAllMentorBoardsByDateOrderByDateDesc(Pageable pageable);

    // 특정 사용자와 관련된 게시글 조회 페이징
    @Query("SELECT mb FROM MentorBoard mb WHERE mb.member = :member AND mb.mentorBoardStatus = 1")
    Page<MentorBoard> findByMember(@Param("member") Member member, Pageable pageable);
    
    
    
}
    
  