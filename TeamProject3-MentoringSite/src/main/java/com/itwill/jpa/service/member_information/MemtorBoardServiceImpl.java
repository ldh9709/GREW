package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.entity.member_information.MentorBoard;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.MentorBoardRepository;
import com.itwill.jpa.service.alarm.AlarmService;
import com.itwill.jpa.repository.member_information.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class MemtorBoardServiceImpl implements MemtorBoardService {

		private static final String IMAGE_PATH = "src/main/resources/static/images/mentor-board/";
		
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
	        MentorBoard existingBoard = mentorBoardRepository.findById(mentorBoardDto.getMentorBoardNo()).get();
	        
	        /* != null 인 경우로 나눠서 저장해야하는지? */
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
	        MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardDto.getMentorBoardNo()).get();
	        
	        mentorBoard.setMentorBoardStatus(2); // 상태를 변경
	        return MentorBoardDto.toDto(mentorBoardRepository.save(mentorBoard));
	    }


	    /* 멘토 보드 상세 조회 */
	    @Override
	    public MentorBoardDto getMemtorBoard(Long mentorBoardNo) {
	        MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardNo).get();
	        
	        return MentorBoardDto.toDto(mentorBoard);
	    }



	    /* 멘토 보드 조회수 증가 */
	    @Override
	    public MentorBoardDto increaseViewMentorBoard(MentorBoardDto mentorBoardDto) throws Exception {
	        MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardDto.getMentorBoardNo()).get();

	        mentorBoard.setMentorBoardViews(mentorBoard.getMentorBoardViews() + 1);
	        return MentorBoardDto.toDto(mentorBoardRepository.save(mentorBoard));
	    }


	    @Override
	    public Page<MentorBoardDto> findByMentorBoardOrderByView(int page, int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<MentorBoard> mentorBoardPage = mentorBoardRepository.findAllMentorBoardOrderByView(pageable);
	        return mentorBoardPage.map(MentorBoardDto::toDto);
	    }

	    @Override
	    public Page<MentorBoardDto> findMentorBoardBySearch(String search, int page, int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<MentorBoard> mentorBoardPage = mentorBoardRepository.findMentorBoardBySearch(search, pageable);
	        return mentorBoardPage.map(MentorBoardDto::toDto);
	    }

	    @Override
	    public Page<MentorBoardDto> getMentorBoardsSortedByDate(int page, int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<MentorBoard> mentorBoardPage = mentorBoardRepository.findAllMentorBoardsByDateOrderByDateDesc(pageable);
	        return mentorBoardPage.map(MentorBoardDto::toDto);
	    }

	    @Override
	    public Page<MentorBoardDto> findByMember(Long memberNo, int page, int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        Member member = new Member();
	        member.setMemberNo(memberNo);
	        Page<MentorBoard> mentorBoardPage = mentorBoardRepository.findByMember(member, pageable);
	        return mentorBoardPage.map(MentorBoardDto::toDto);
	    }   
	    
	    
	    @Override
	    public void updateMentorBoardImage(Long mentorBoardNo, MultipartFile file) throws Exception {
	        // 1️ 멘토 보드 정보 조회
	        MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardNo)
	                .orElseThrow(() -> new IllegalArgumentException("해당 멘토 보드를 찾을 수 없습니다. mentorBoardNo: " + mentorBoardNo));

	        // 2️ 절대 경로 가져오기
	        String absolutePath = new File("").getAbsolutePath(); 
	        String IMAGE_PATH = absolutePath + "/src/main/resources/static/images/mentor-board/"; // 절대 경로 생성

	        // 3️ 디렉터리 확인 및 생성
	        File saveDir = new File(IMAGE_PATH);
	        if (!saveDir.exists()) {
	            saveDir.mkdirs(); // 디렉터리가 없으면 생성
	        }

	        // 4️ 파일명 생성 (고유한 이름으로 생성)
	        String originalFilename = file.getOriginalFilename();
	        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
	        String fileName = UUID.randomUUID().toString() + fileExtension;

	        // 5️ 파일 저장 경로 생성
	        File saveFile = new File(IMAGE_PATH + fileName);

	        // 6️ 파일 저장
	        file.transferTo(saveFile);

	        // 7️ mentorBoard 엔티티에 이미지 경로 저장
	        mentorBoard.setMentorBoardImage("/images/mentor-board/" + fileName);
	        mentorBoardRepository.save(mentorBoard);
	    }
	    
	    
	    
}

	    
	    
	    
	    
	    
	    
	    
//	 /* 특정 멘토의 모든 멘토 보드 조회 */
//    @Override
//    public List<MentorBoardDto> getMentorBoardsByMemberNo(Long memberNo) {
//        Member member = memberRepository.findById(memberNo).get();
//
//        List<MentorBoard> mentorBoards = mentorBoardRepository.findByMember(member);
//        List<MentorBoardDto> mentorBoardDtos = new ArrayList<>();
//        for (MentorBoard board : mentorBoards) {
//            mentorBoardDtos.add(MentorBoardDto.toDto(board));
//        }
//        return mentorBoardDtos;
//    }

//    /* 조회수 별로 멘토 보드 리스트 조회 */ 
//    @Override
//    public List<MentorBoardDto> findByMentorBoardNoOrderByView(Long mentorBoardNo) {
//        List<MentorBoard> boards = mentorBoardRepository.findAllMentorBoardOrderByView(Pageable.unpaged()).getContent();
//        List<MentorBoardDto> mentorBoardDtos = new ArrayList<>();
//        for (MentorBoard board : boards) {
//            mentorBoardDtos.add(MentorBoardDto.toDto(board));
//        }
//        return mentorBoardDtos;
//    }
//    /* 멘토 보드 검색창 검색 */
//    @Override
//    public List<MentorBoardDto> findMentorBoardBySearch(String search) {
//        List<MentorBoard> boards = mentorBoardRepository.findMentorBoardBySearch(search);
//        List<MentorBoardDto> mentorBoardDtos = new ArrayList<>();
//        for (MentorBoard board : boards) {
//            mentorBoardDtos.add(MentorBoardDto.toDto(board));
//        }
//        return mentorBoardDtos;
//    }
    
//    @Override
//    public List<MentorBoard> getMentorBoardsSortedByDate() {
//        return mentorBoardRepository.findAllMentorBoardsByDateOrderByDateDesc();
//    }
//    
    
    