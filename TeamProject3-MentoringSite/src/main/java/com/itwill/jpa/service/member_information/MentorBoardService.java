package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.entity.member_information.MentorBoard;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface MentorBoardService {

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

    Page<MentorBoardDto> findByMentorBoardOrderByView(int page, int size);
    
    Page<MentorBoardDto> findMentorBoardBySearch(String search, int page, int size);
    
    Page<MentorBoardDto> getMentorBoardsSortedByDate(int page, int size);
    
    Page<MentorBoardDto> findByMember(Long memberNo, int page, int size);
    
   
    
    // 이미지 업로드 메서드 (파일을 저장하고 URL을 반환)
    void uploadImage(Long mentorBoardNo, MultipartFile file) throws Exception;

    // 이미지 URL 가져오기 메서드
    String getImageUrl(Long mentorBoardNo) throws Exception;
    
}
