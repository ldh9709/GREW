package com.itwill.jpa.service.member_information;

import java.util.List;

import com.itwill.jpa.dto.member_information.FollowDto;
import com.itwill.jpa.dto.member_information.InterestDto;
import com.itwill.jpa.entity.member_information.Interest;

public interface InterestService {
	
	public Interest saveInterest(InterestDto interestDto);
	
	public Interest updateInterest(InterestDto interestDto);
	
	
	public Interest deleteInterest(Long interestNo);
	
	//관심사 조회
	public Interest getInterest(Long interestNo);
	
	//특정 사용자 관심사 리스트 조회
	public List<Interest> getInterestList(Long memberNo);
	
	
	
	
}
