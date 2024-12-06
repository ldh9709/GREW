package com.itwill.jpa.service.member_information;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.member_information.FollowRequestDto;
import com.itwill.jpa.dto.member_information.FollowResponseDto;
import com.itwill.jpa.entity.member_information.Follow;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.CategoryRepository;
import com.itwill.jpa.repository.member_information.FollowReporitory;
import com.itwill.jpa.repository.member_information.MemberRepository;

@Service
public class FollowServiceImpl implements FollowService{
	
	@Autowired
	private FollowReporitory followReporitory;
	@Autowired
	private CategoryRepository categoryReporitory;
	@Autowired
	private MemberRepository memberRepository;
	
	/*팔로우 등록*/
	public FollowRequestDto createFollow(FollowRequestDto followDto) {
		
		/* 팔로우 멘토 follow_count 증가*/
		Member mentorMember = memberRepository.findById(followDto.getMentorMemberNo()).get();
		mentorMember.getMentorProfile().setMentorFollowCount(mentorMember.getMentorProfile().getMentorFollowCount()+1); 
		memberRepository.save(mentorMember);
		
		/* 팔로우 저장 */
		Follow follow = Follow.toEntity(followDto);
		followReporitory.save(follow);
		
		return followDto;
	}
	
	/*팔로우 취소*/
	public Long deleteFollow(Long followNo) {
		
		/* 팔로우 멘토 follow_count감소*/
		Follow follow = followReporitory.findById(followNo).get();
		
		Member mentorMember = follow.getMentorMember();
		mentorMember.getMentorProfile().setMentorFollowCount(mentorMember.getMentorProfile().getMentorFollowCount()-1); 
		memberRepository.save(mentorMember);
		
		/* 팔로우 삭제 */
		followReporitory.deleteById(followNo);
		
		return followNo; 
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
