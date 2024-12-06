package com.itwill.jpa.service.member_information;

import java.util.List;

import com.itwill.jpa.dto.member_information.FollowRequestDto;
import com.itwill.jpa.dto.member_information.FollowResponseDto;

public interface FollowService {
	FollowRequestDto createFollow(FollowRequestDto followDto);
	Long deleteFollow(Long followNo);
	List<FollowResponseDto> getMentorList(Long menteeMemberNo);
	Integer countFollower(Long mentorMemberNo);
}
