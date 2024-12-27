package com.itwill.jpa.service.member_information;

import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.entity.member_information.MentorBoard;
import com.itwill.jpa.entity.member_information.MentorProfile;
import com.itwill.jpa.exception.CustomException;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.MentorBoardRepository;
import com.itwill.jpa.repository.member_information.MentorProfileRepository;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.repository.member_information.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;

import java.io.File;
import java.io.IOException;

@Transactional
@Service
public class MentorBoardServiceImpl implements MentorBoardService {

	private static final String UPLOAD_DIR = "C:/upload/mentor-board/"; // **외부 경로**
		
	  	@Autowired
	    private MentorBoardRepository mentorBoardRepository;

	    @Autowired
	    private MemberRepository memberRepository;
	    
	    @Autowired
	    private MentorProfileRepository mentorProfileRepository;
	    

	    
	    /**
	     * 멘토 보드 등록 메서드
	     */
	    @Override
	    public MentorBoardDto saveMemtorBoard(MentorBoardDto mentorBoardDto) {
	        try {
	            Long memberNo = mentorBoardDto.getMemberNo();
	            Member member = memberRepository.findById(memberNo).orElse(null);
	            
	            // 멤버가 존재하지 않을 때 예외 발생
	            if (member == null) {
	                throw new CustomException(ResponseStatusCode.MEMBER_NOT_FOUND, ResponseMessage.MEMBER_NOT_FOUND, null);
	            }
	            
	            // 멘토 프로필 존재 및 상태 확인
	            MentorProfile mentorProfile = mentorProfileRepository.findByMemberNo(memberNo);
	            if (mentorProfile == null || mentorProfile.getMentorStatus() != 3) {
	                throw new CustomException(ResponseStatusCode.NOT_A_MENTOR, ResponseMessage.NOT_A_MENTOR, null);
	            }

	            // 멘토 보드 엔티티 생성 및 저장
	            MentorBoard mentorBoard = MentorBoard.toEntity(mentorBoardDto);
	            MentorBoard savedBoard = mentorBoardRepository.save(mentorBoard);
	            return MentorBoardDto.toDto(savedBoard);
	        } catch (Exception e) {
	            throw new CustomException(ResponseStatusCode.CREATED_MENTOR_BOARD_FAIL, ResponseMessage.CREATED_MENTOR_BOARD_FAIL, e);
	        }
	    }

	    /**
	     * 멘토 보드 수정 메서드
	     */
	    @Override
	    public MentorBoardDto updateMemtorBoard(MentorBoardDto mentorBoardDto) {
	        try {
	            MentorBoard existingBoard = mentorBoardRepository.findById(mentorBoardDto.getMentorBoardNo()).orElse(null);
	            
	            // 멘토 보드를 찾을 수 없는 경우 예외 발생
	            if (existingBoard == null) {
	                throw new CustomException(ResponseStatusCode.MENTOR_BOARD_NOT_FOUND, ResponseMessage.MENTOR_BOARD_NOT_FOUND, null);
	            }

	            // 멘토 보드의 제목과 내용을 업데이트
	            if (mentorBoardDto.getMentorBoardTitle() != null) {
	                existingBoard.setMentorBoardTitle(mentorBoardDto.getMentorBoardTitle());
	            }
	            if (mentorBoardDto.getMentorBoardContent() != null) {
	                existingBoard.setMentorBoardContent(mentorBoardDto.getMentorBoardContent());
	            }

	            // 변경 사항 저장
	            MentorBoard updatedBoard = mentorBoardRepository.save(existingBoard);
	            return MentorBoardDto.toDto(updatedBoard);
	        } catch (Exception e) {
	            throw new CustomException(ResponseStatusCode.UPDATE_MENTOR_BOARD_FAIL, ResponseMessage.UPDATE_MENTOR_BOARD_FAIL, e);
	        }
	    }

	    /**
	     * 멘토 보드 삭제 메서드 (상태 변경)
	     */
	    @Override
	    public MentorBoardDto deleteMemtorBoard(MentorBoardDto mentorBoardDto) {
	        try {
	            MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardDto.getMentorBoardNo()).orElse(null);
	            
	            // 멘토 보드를 찾을 수 없는 경우 예외 발생
	            if (mentorBoard == null) {
	                throw new CustomException(ResponseStatusCode.MENTOR_BOARD_NOT_FOUND, ResponseMessage.MENTOR_BOARD_NOT_FOUND, null);
	            }

	            // 상태를 삭제 상태로 변경
	            mentorBoard.setMentorBoardStatus(2); 
	            return MentorBoardDto.toDto(mentorBoardRepository.save(mentorBoard));
	        } catch (Exception e) {
	            throw new CustomException(ResponseStatusCode.DELETE_MENTOR_BOARD_FAIL, ResponseMessage.DELETE_MENTOR_BOARD_FAIL, e);
	        }
	    }

	    /**
	     * 멘토 보드 상세 조회 메서드
	     */
	    @Override
	    public MentorBoardDto getMentorBoard(Long mentorBoardNo) {
	        try {
	            MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardNo).orElse(null);
	            
	            // 멘토 보드를 찾을 수 없는 경우 예외 발생
	            if (mentorBoard == null) {
	                throw new CustomException(ResponseStatusCode.MENTOR_BOARD_NOT_FOUND, ResponseMessage.MENTOR_BOARD_NOT_FOUND, null);
	            }

	            return MentorBoardDto.toDto(mentorBoard);
	        } catch (Exception e) {
	            throw new CustomException(ResponseStatusCode.MENTOR_BOARD_NOT_FOUND, ResponseMessage.MENTOR_BOARD_NOT_FOUND, e);
	        }
	    }

	    /**
	     * 멘토 보드 조회수 증가 메서드
	     */
	    @Override
	    public MentorBoardDto increaseViewMentorBoard(MentorBoardDto mentorBoardDto) {
	        try {
	            MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardDto.getMentorBoardNo()).orElse(null);
	            
	            // 멘토 보드를 찾을 수 없는 경우 예외 발생
	            if (mentorBoard == null) {
	                throw new CustomException(ResponseStatusCode.MENTOR_BOARD_NOT_FOUND, ResponseMessage.MENTOR_BOARD_NOT_FOUND, null);
	            }

	            // 조회수 증가
	            mentorBoard.setMentorBoardViews(mentorBoard.getMentorBoardViews() + 1);
	            return MentorBoardDto.toDto(mentorBoardRepository.save(mentorBoard));
	        } catch (Exception e) {
	            throw new CustomException(ResponseStatusCode.UPDATE_MENTOR_BOARD_FAIL, ResponseMessage.UPDATE_MENTOR_BOARD_FAIL, e);
	        }
	    }
/*
	    * 멘토 보드 이미지 업로드 메서드
	     */
	    @Override
	    public String uploadImage(Long mentorBoardNo, MultipartFile file) {
	        try {
	            // 1️⃣ 멘토 보드 조회
	            MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardNo).orElse(null);

	            // 2️⃣ 멘토 보드를 찾을 수 없는 경우 예외 발생
	            if (mentorBoard == null) {
	                throw new CustomException(ResponseStatusCode.MENTOR_BOARD_NOT_FOUND, ResponseMessage.MENTOR_BOARD_NOT_FOUND, null);
	            }

	            // 3️⃣ 업로드할 파일 정보 설정
	            String fileName = file.getOriginalFilename();
	            String filePath = UPLOAD_DIR + mentorBoardNo + "/" + fileName;

	            // 4️⃣ 디렉토리 생성 (존재하지 않으면)
	            File directory = new File(UPLOAD_DIR + mentorBoardNo + "/");
	            if (!directory.exists()) {
	                directory.mkdirs();
	            }

	            // 5️⃣ 파일 저장
	            file.transferTo(new File(filePath));

	            // 6️⃣ 저장된 이미지 URL 생성
	            String imageUrl = "/upload/mentor-board/" + mentorBoardNo + "/" + fileName;
	            
	            // 7️⃣ 멘토 보드에 이미지 URL 저장
	            mentorBoard.setMentorBoardImage(imageUrl);
	            mentorBoardRepository.save(mentorBoard);
	            
	            // 8️⃣ 업로드된 이미지 URL 반환
	            return imageUrl;
	        } catch (IOException e) {
	            throw new CustomException(ResponseStatusCode.IMAGE_UPLOAD_FAIL, ResponseMessage.IMAGE_UPLOAD_FAIL, e);
	        }
	    }

	    /**
	     * 멘토 보드 이미지 URL 가져오기 메서드
	     */
	    @Override
	    public String getImageUrl(Long mentorBoardNo) {
	        try {
	            MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardNo).orElse(null);

	            // 멘토 보드를 찾을 수 없는 경우 예외 발생
	            if (mentorBoard == null) {
	                throw new CustomException(ResponseStatusCode.MENTOR_BOARD_NOT_FOUND, ResponseMessage.MENTOR_BOARD_NOT_FOUND, null);
	            }

	            return mentorBoard.getMentorBoardImage();
	        } catch (CustomException e) {
	            throw e;
	        } catch (Exception e) {
	            throw new CustomException(ResponseStatusCode.MENTOR_BOARD_NOT_FOUND, ResponseMessage.MENTOR_BOARD_NOT_FOUND, e);
	        }
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
	    //멘토보드 리스트 12/22일 추가
	    @Override
	    public Page<MentorBoardDto> getMentorBoardsSortedByDate(int status, int page, int size) {
	        PageRequest pageable = PageRequest.of(page, size);
	        return mentorBoardRepository.findByMentorBoardStatusOrderByMentorBoardDateDesc(status, pageable)
	                                    .map(MentorBoardDto::toDto);
	    }
	    
	    
	    ///////////////////////////카테고리 리스트 대분류 소분류
	    @Override
	    public Page<MentorBoardDto> getByCategoryMentorBoardOrderByView(Long categoryNo, int page, int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        return mentorBoardRepository.findByCategoryMentorBoardOrderByView(categoryNo, pageable)
	                                    .map(MentorBoardDto::toDto);
	    }

	    @Override
	    public Page<MentorBoardDto> getByParentCategoryMentorBoardOrderByView(Long categoryNo, int page, int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        return mentorBoardRepository.findByParentCategoryMentorBoardOrderByView(categoryNo, pageable)
	                                    .map(MentorBoardDto::toDto);
	    }

	    @Override
	    public Page<MentorBoardDto> getByCategoryMentorBoardOrderByDate(Long categoryNo, int page, int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        return mentorBoardRepository.findByCategoryMentorBoardOrderByDate(categoryNo, pageable)
	                                    .map(MentorBoardDto::toDto);
	    }

	    @Override
	    public Page<MentorBoardDto> getByParentCategoryMentorBoardOrderByDate(Long categoryNo, int page, int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        return mentorBoardRepository.findByParentCategoryMentorBoardOrderByDate(categoryNo, pageable)
	                                    .map(MentorBoardDto::toDto);
	    }
	    
	    @Override
	    public Page<MentorBoardDto> getMentorBoardsByStatusAndSortedByViews(int status, int page, int size) {
	        PageRequest pageable = PageRequest.of(page, size);
	        // Repository 호출하여 데이터 가져오기
	        Page<MentorBoard> mentorBoards = mentorBoardRepository.findByStatusAndSortedByViews(status, pageable);
	        // Entity -> DTO 변환 및 반환
	        return mentorBoards.map(MentorBoardDto::toDto);
	    }
	    
	    
	    
	  
	}
	    
	    
