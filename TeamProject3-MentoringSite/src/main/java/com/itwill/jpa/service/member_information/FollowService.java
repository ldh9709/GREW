package com.itwill.jpa.service.member_information;

import java.util.List;

import com.itwill.jpa.dto.member_information.FollowRequestDto;
import com.itwill.jpa.dto.member_information.FollowResponseDto;

public interface FollowService {
	public void createFollow(FollowRequestDto followDto);
	public void deleteFollow(Long followNo);
	public List<FollowResponseDto> getMentorList(Long menteeMemberNo);
	public Integer countByMentee(Long mentorMemberNo);
}
