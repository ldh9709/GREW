package com.itwill.jpa.service.member_information;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.member_information.FollowDto;
import com.itwill.jpa.entity.member_information.Follow;
import com.itwill.jpa.repository.member_information.FollowReporitory;

@Service
public class FollowServiceImpl implements FollowService{
	
	@Autowired
	private FollowReporitory followReporitory;
	
	/*팔로우 등록*/
	public void saveFollow(FollowDto followDto) {
		Follow follow = Follow.toEntity(followDto);
		followReporitory.save(follow);
	}
	/*팔로우 취소*/
	public void deleteFollow(Long followNo) {
		followReporitory.deleteById(followNo);
	}
	/*팔로우 리스트 출력(멘토리스트)*/
	
	/*팔로잉 수(멘티수)*/
}
