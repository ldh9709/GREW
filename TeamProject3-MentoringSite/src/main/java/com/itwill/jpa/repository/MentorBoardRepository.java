package com.itwill.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.user_information.Member;
import com.itwill.jpa.entity.user_information.MentorBoard;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MentorBoardRepository extends JpaRepository<MentorBoard, Long> {

    // 특정 사용자와 관련된 게시글 조회
    //List<MentorBoard> findByMember(Member member);

    // 특정 제목을 포함한 게시글 조회
    //List<MentorBoard> findByTitleContaining(String keyword);

    // 게시글 작성 날짜로 게시글 조회
    //List<MentorBoard> findByCreatedAtAfter(LocalDate date);

    // 특정 사용자와 키워드로 게시글 조회
    //List<MentorBoard> findByMemberAndTitleContaining(Member member, String keyword);
}
