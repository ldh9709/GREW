package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.entity.member_information.MentorBoard;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.MentorBoardRepository;
import com.itwill.jpa.repository.member_information.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MemtorBoardServiceImpl implements MemtorBoardService {

    @Autowired
    private MentorBoardRepository mentorBoardRepository;

    @Autowired
    private MemberRepository memberRepository;

    /* 멘토 보드 등록 */
    @Override
    public MentorBoardDto savememtorboard(MentorBoardDto mentorBoardDto) {
        return MentorBoardDto.toDto(mentorBoardRepository.save(MentorBoard.toEntity(mentorBoardDto)));
    }

    /* 멘토 보드 수정 */
    @Override
    public MentorBoardDto updatememtorboard(MentorBoardDto mentorBoardDto) throws Exception {
        return MentorBoardDto.toDto(mentorBoardRepository.save(MentorBoard.toEntity(mentorBoardDto)));
    }

    /* 멘토 보드 삭제(상태 변경) */
    @Override
    public MentorBoardDto deletememtorboard(MentorBoardDto mentorBoardDto) throws Exception {
        MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardDto.getMentorBoardNo())
                .orElseThrow(() -> new Exception("MentorBoard not found with ID: " + mentorBoardDto.getMentorBoardNo()));
        mentorBoard.setMentorBoardStatus(2); // 상태를 변경
        return MentorBoardDto.toDto(mentorBoardRepository.save(mentorBoard));
    }

    /* 멘토 보드 상세 조회 */
    @Override
    public MentorBoardDto getmemtorboard(Long mentorBoardNo) {
        MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardNo)
                .orElseThrow(() -> new RuntimeException("MentorBoard not found with ID: " + mentorBoardNo));
        return MentorBoardDto.toDto(mentorBoard);
    }

    /* 특정 멘토의 모든 멘토 보드 조회 */
    @Override
    public List<MentorBoardDto> getMentorBoardsByMemberNo(Long memberNo) {
        Member member = memberRepository.findById(memberNo)
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + memberNo));

        List<MentorBoard> mentorBoards = mentorBoardRepository.findByMember(member);
        List<MentorBoardDto> mentorBoardDtos = new ArrayList<>();
        for (MentorBoard board : mentorBoards) {
            mentorBoardDtos.add(MentorBoardDto.toDto(board));
        }
        return mentorBoardDtos;
    }
}
