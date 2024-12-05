package com.itwill.jpa.service.member_information;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.member_information.FollowRequestDto;
import com.itwill.jpa.dto.member_information.FollowResponseDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Follow;
import com.itwill.jpa.repository.member_information.CategoryRepository;
import com.itwill.jpa.repository.member_information.FollowReporitory;

@Service
public class FollowServiceImpl implements FollowService{
	
	@Autowired
	private FollowReporitory followReporitory;
	@Autowired
	private CategoryRepository categoryReporitory;
	
	/*팔로우 등록*/
	public FollowRequestDto createFollow(FollowRequestDto followDto) {
		Follow follow = Follow.toEntity(followDto);
		followReporitory.save(follow);
		return followDto;
	}
	/*팔로우 취소*/
	public FollowRequestDto deleteFollow(Long followNo) {
		followReporitory.deleteById(followNo);
		return FollowRequestDto.toDto(followReporitory.findById(followNo).get()); 
	}
	/*팔로잉 리스트 출력(멘토리스트)*/
	public List<FollowResponseDto> getMentorList(Long menteeMemberNo){
		return followReporitory.findFollowMentors(menteeMemberNo);
	}
	
	/*팔로워 수(멘티 수)*/
	public Integer countFollower(Long mentorMemberNo) {
		return followReporitory.countBymentorMember_MemberNo(mentorMemberNo);
	}
	
	
}
