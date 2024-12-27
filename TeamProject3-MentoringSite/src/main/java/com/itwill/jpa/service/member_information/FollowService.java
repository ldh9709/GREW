package com.itwill.jpa.service.member_information;

import java.util.List;

import org.springframework.data.domain.Page;

import com.itwill.jpa.dto.member_information.FollowRequestDto;
import com.itwill.jpa.dto.member_information.FollowResponseDto;

public interface FollowService {
	
	//팔로우 여부 체크
	Boolean isExistFollow(Long menteeNo, Long mentorNo);
	//팔로우 등록
	FollowRequestDto createFollow(FollowRequestDto followDto);
	//팔로우 삭제
	Long deleteFollow(Long followNo);
	//팔로우 멘토 리스트
	Page<FollowResponseDto> getMentorList(Long menteeMemberNo, int pageNumber, int pageSize);
	//팔로워 수
	Integer countFollower(Long mentorMemberNo);
}
