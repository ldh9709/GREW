package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.member_information.MentorBoardDto;
import java.util.List;

public interface MemtorBoardService {

    // 멘토 보드 글쓰기
    MentorBoardDto savememtorboard(MentorBoardDto mentorboardDto);

    // 멘토 보드 수정
    MentorBoardDto updatememtorboard(MentorBoardDto mentorboardDto) throws Exception;

    // 멘토 보드 삭제(업데이트)
    MentorBoardDto deletememtorboard(MentorBoardDto mentorboardDto) throws Exception;

    // 멘토 보드 보기
    MentorBoardDto getmemtorboard(Long MentorBoardNo);

    // 특정 멘토의 보드 리스트 보기
    List<MentorBoardDto> getMentorBoardsByMemberNo(Long memberNo);
}
