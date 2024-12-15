package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.entity.member_information.MentorBoard;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface MentorBoardService {

    /**
     * 멘토 보드 글쓰기
     */
    MentorBoardDto saveMemtorBoard(MentorBoardDto mentorBoardDto);

    /**
     * 멘토 보드 수정
     */
    MentorBoardDto updateMemtorBoard(MentorBoardDto mentorBoardDto) throws Exception;

    /**
     * 멘토 보드 삭제 (업데이트를 통한 소프트 삭제)
     */
    MentorBoardDto deleteMemtorBoard(MentorBoardDto mentorBoardDto) throws Exception;

    /**
     * 멘토 보드 상세보기
     */
    MentorBoardDto getMemtorBoard(Long mentorBoardNo);

    /**
     * 멘토 보드의 조회수 증가
     */
    MentorBoardDto increaseViewMentorBoard(MentorBoardDto mentorBoardDto) throws Exception;

    /**
     * 멘토 보드를 조회수 기준으로 내림차순 정렬하여 페이지로 반환
     */
    Page<MentorBoardDto> findByMentorBoardOrderByView(int page, int size);
    
    /**
     * 검색어를 기준으로 멘토 보드 목록을 조회
     */
    Page<MentorBoardDto> findMentorBoardBySearch(String search, int page, int size);
    
    /**
     * 멘토 보드를 생성일 기준으로 내림차순 정렬하여 페이지로 반환
     */
    Page<MentorBoardDto> getMentorBoardsSortedByDate(int page, int size);
    
    /**
     * 특정 멤버가 작성한 멘토 보드 목록 조회
     */
    Page<MentorBoardDto> findByMember(Long memberNo, int page, int size);
    
    /**
     * 멘토 보드에 이미지를 업로드
     */
    void uploadImage(Long mentorBoardNo, MultipartFile file) throws Exception;

    /**
     * 멘토 보드의 이미지 URL을 가져오기
     */
    String getImageUrl(Long mentorBoardNo) throws Exception;
}
