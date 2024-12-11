package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.entity.member_information.MentorBoard;

import java.util.List;

import org.springframework.data.domain.Page;

public interface MemtorBoardService {

    // 멘토 보드 글쓰기
    MentorBoardDto saveMemtorBoard(MentorBoardDto mentorBoardDto);

    // 멘토 보드 수정
    MentorBoardDto updateMemtorBoard(MentorBoardDto mentorBoardDto) throws Exception;

    // 멘토 보드 삭제(업데이트)
    MentorBoardDto deleteMemtorBoard(MentorBoardDto mentorBoardDto) throws Exception;

    // 멘토 보드 보기 상세보기 
    MentorBoardDto getMemtorBoard(Long mentorBoardNo);

  //조회수증가
    MentorBoardDto increaseViewMentorBoard(MentorBoardDto mentorBoardDto) throws Exception;
    
    // 특정 멘토의 보드 리스트 보기
    List<MentorBoardDto> getMentorBoardsByMemberNo(Long memBerNo);
    
  //조회순
  	List<MentorBoardDto> findByMentorBoardNoOrderByView(Long mentorBoardNo);
  	
  //검색
  	List<MentorBoardDto> findMentorBoardBySearch(String search);
  
  	//시간으로 조회
    List<MentorBoard> getMentorBoardsSortedByDate();
  	}
