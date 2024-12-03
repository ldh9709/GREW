package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.entity.member_information.MentorBoard;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.MentorBoardRepository;
import com.itwill.jpa.repository.member_information.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemtorBoardServiceImpl implements MemtorBoardService {

    @Autowired
    private MentorBoardRepository mentorBoardRepository;

    @Autowired
    private MemberRepository memberRepository;

    /* 멘토 보드 등록 */
    @Override
    public MentorBoardDto savememtorboard(MentorBoardDto mentorBoardDto) {
        // DTO -> Entity 변환 후 저장
        MentorBoard mentorBoard = MentorBoard.toEntity(mentorBoardDto);
        MentorBoard savedBoard = mentorBoardRepository.save(mentorBoard);
        return MentorBoardDto.toDto(savedBoard);
    }

    /* 멘토 보드 수정 */
    @Override
    public MentorBoardDto updatememtorboard(MentorBoardDto mentorBoardDto) throws Exception {
        // 기존 멘토 보드 조회
        MentorBoard existingBoard = mentorBoardRepository.findById(mentorBoardDto.getMentorBoardNo())
                .orElseThrow(() -> new Exception("MentorBoard not found with ID: " + mentorBoardDto.getMentorBoardNo()));

        // 수정 내용 반영
        existingBoard.setMentorBoardTitle(mentorBoardDto.getMentorBoardTitle());
        existingBoard.setMentorBoardContent(mentorBoardDto.getMentorBoardContent());
        existingBoard.setMentorBoardImage(mentorBoardDto.getMentorBoardImage());

        // 수정된 엔티티 저장
        MentorBoard updatedBoard = mentorBoardRepository.save(existingBoard);
        return MentorBoardDto.toDto(updatedBoard);
    }

    /* 멘토 보드 삭제 */
    @Override
    public MentorBoardDto deletememtorboard(MentorBoardDto mentorBoardDto) throws Exception {
        // 삭제할 멘토 보드 조회
        MentorBoard boardToDelete = mentorBoardRepository.findById(mentorBoardDto.getMentorBoardNo())
                .orElseThrow(() -> new Exception("MentorBoard not found with ID: " + mentorBoardDto.getMentorBoardNo()));

        // 삭제
        mentorBoardRepository.delete(boardToDelete);
        return MentorBoardDto.toDto(boardToDelete); // 삭제된 보드 반환
    }

    /* 멘토 보드 상세 조회 */
    @Override
    public MentorBoardDto getmemtorboard(Long mentorBoardNo) {
        // 멘토 보드 조회
        MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardNo)
                .orElseThrow(() -> new RuntimeException("MentorBoard not found with ID: " + mentorBoardNo));

        return MentorBoardDto.toDto(mentorBoard);
    }

    /* 특정 멤버의 모든 멘토 보드 조회 */
    @Override
    public List<MentorBoardDto> getMentorBoardsByMemberNo(Long memberNo) {
        // 멤버 조회
        Member member = memberRepository.findById(memberNo)
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + memberNo));

        // 멤버의 게시글 조회
        List<MentorBoard> boards = mentorBoardRepository.findByMember(member);

        // DTO 리스트로 변환 후 반환
        return boards.stream()
                .map(MentorBoardDto::toDto)
                .collect(Collectors.toList());
    }
}
