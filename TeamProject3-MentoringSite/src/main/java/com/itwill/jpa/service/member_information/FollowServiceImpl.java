package com.itwill.jpa.service.member_information;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.member_information.FollowRequestDto;
import com.itwill.jpa.dto.member_information.FollowResponseDto;
import com.itwill.jpa.entity.member_information.Follow;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.exception.AlreadyFollowedException;
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
		
		/* 팔로우가 이미 되어있는지 확인 */
		Boolean followExist = followReporitory.existsByMenteeMember_MemberNoAndMentorMember_MemberNo(followDto.getMenteeMemberNo(), followDto.getMenteeMemberNo());
		
		if(followExist == true) {
			throw new AlreadyFollowedException("해당 멘토는 이미 팔로우 되어있습니다.");
		}
		
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
	
	/*팔로잉 리스트 출력(멘토리스트, 이름 순서)*/
	public Page<FollowResponseDto> getMentorList(Long menteeMemberNo, int pageNumber, int pageSize){
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<FollowResponseDto> followList = followReporitory.findFollowMentors(menteeMemberNo, pageable);
		long totalCount = followList.size(); 
		
		return new PageImpl<>(followList, pageable, totalCount);
	}
	
	/*팔로워 수(멘티 수)*/
	public Integer countFollower(Long mentorMemberNo) {
		return followReporitory.countBymentorMember_MemberNo(mentorMemberNo);
	}
	
	
}
