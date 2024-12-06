package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.entity.member_information.MentorBoard;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.MentorBoardRepository;
import com.itwill.jpa.service.alarm.AlarmService;
import com.itwill.jpa.repository.member_information.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	    public MentorBoardDto saveMemtorBoard(MentorBoardDto mentorBoardDto) {
	        return MentorBoardDto.toDto(mentorBoardRepository.save(MentorBoard.toEntity(mentorBoardDto)));
	    }

	    @Override
	    public MentorBoardDto updateMemtorBoard(MentorBoardDto mentorBoardDto) throws Exception {
	        // 기존 객체를 조회
	        MentorBoard existingBoard = mentorBoardRepository.findById(mentorBoardDto.getMentorBoardNo())
	                .orElseThrow(() -> new Exception("MentorBoard not found with ID: " + mentorBoardDto.getMentorBoardNo()));

	        // 필요한 필드만 업데이트
	        if (mentorBoardDto.getMentorBoardTitle() != null) {
	            existingBoard.setMentorBoardTitle(mentorBoardDto.getMentorBoardTitle());
	        }
	        if (mentorBoardDto.getMentorBoardContent() != null) {
	            existingBoard.setMentorBoardContent(mentorBoardDto.getMentorBoardContent());
	        }
	        if (mentorBoardDto.getMentorBoardImage() != null) {
	            existingBoard.setMentorBoardImage(mentorBoardDto.getMentorBoardImage());
	        }
	        if (mentorBoardDto.getMentorBoardStatus() != null) {
	            existingBoard.setMentorBoardStatus(mentorBoardDto.getMentorBoardStatus());
	        }

	        // 수정된 객체를 저장
	        MentorBoard updatedBoard = mentorBoardRepository.save(existingBoard);

	        // DTO로 변환하여 반환
	        return MentorBoardDto.toDto(updatedBoard);
	    }


	    /* 멘토 보드 삭제(상태 변경) */
	    @Override
	    public MentorBoardDto deleteMemtorBoard(MentorBoardDto mentorBoardDto) throws Exception {
	        MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardDto.getMentorBoardNo())
	                .orElseThrow(() -> new Exception("MentorBoard not found with ID: " + mentorBoardDto.getMentorBoardNo()));
	        mentorBoard.setMentorBoardStatus(2); // 상태를 변경
	        return MentorBoardDto.toDto(mentorBoardRepository.save(mentorBoard));
	    }


	    /* 멘토 보드 상세 조회 */
	    @Override
	    public MentorBoardDto getMemtorBoard(Long mentorBoardNo) {
	        MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardNo)
	                .orElseThrow(() -> new RuntimeException("MentorBoard not found with ID: " + mentorBoardNo));
	        return MentorBoardDto.toDto(mentorBoard);
	    }



	    /* 멘토 보드 조회수 증가 */
	    @Override
	    public MentorBoardDto increaseViewMentorBoard(MentorBoardDto mentorBoardDto) throws Exception {
	        MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardDto.getMentorBoardNo())
	                .orElseThrow(() -> new Exception("MentorBoard not found with ID: " + mentorBoardDto.getMentorBoardNo()));

	        mentorBoard.setMentorBoardViews(mentorBoard.getMentorBoardViews() + 1);
	        return MentorBoardDto.toDto(mentorBoardRepository.save(mentorBoard));
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


    @Override
    public List<MentorBoardDto> findByMentorBoardNoOrderByView(Long mentorBoardNo) {
        List<MentorBoard> boards = mentorBoardRepository.findAllMentorBoardOrderByView(Pageable.unpaged()).getContent();
        List<MentorBoardDto> mentorBoardDtos = new ArrayList<>();
        for (MentorBoard board : boards) {
            mentorBoardDtos.add(MentorBoardDto.toDto(board));
        }
        return mentorBoardDtos;
    }
    /* 멘토 보드 검색창 검색 */
    @Override
    public List<MentorBoardDto> findMentorBoardBySearch(String search) {
        List<MentorBoard> boards = mentorBoardRepository.findMentorBoardBySearch(search);
        List<MentorBoardDto> mentorBoardDtos = new ArrayList<>();
        for (MentorBoard board : boards) {
            mentorBoardDtos.add(MentorBoardDto.toDto(board));
        }
        return mentorBoardDtos;
    }
}