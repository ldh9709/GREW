package com.itwill.jpa.service.member_information;

import java.util.List;

import org.springframework.data.domain.Page;

import com.itwill.jpa.dto.member_information.FollowRequestDto;
import com.itwill.jpa.dto.member_information.FollowResponseDto;

public interface FollowService {
	FollowRequestDto createFollow(FollowRequestDto followDto);
	Long deleteFollow(Long followNo);
	Page<FollowResponseDto> getMentorList(Long menteeMemberNo, int pageNumber, int pageSize);
	Integer countFollower(Long mentorMemberNo);
}
