package com.itwill.jpa.service.member_information;

import java.util.ArrayList;
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
import com.itwill.jpa.exception.CustomException;
import com.itwill.jpa.repository.member_information.CategoryRepository;
import com.itwill.jpa.repository.member_information.FollowReporitory;
import com.itwill.jpa.repository.member_information.MemberRepository;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;


@Service
public class FollowServiceImpl implements FollowService{
	
	@Autowired
	private FollowReporitory followReporitory;
	@Autowired
	private CategoryRepository categoryReporitory;
	@Autowired
	private MemberRepository memberRepository;
	
	/*팔로우 되어 있는지 체크*/
	public Boolean isExistFollow(Long menteeNo, Long mentorNo) {
		try {
			Boolean followExist =followReporitory.existsByMenteeMember_MemberNoAndMentorMember_MemberNo(menteeNo, mentorNo);
			return followExist;
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.CHECK_FOLLOW_FAIL, ResponseMessage.CHECK_FOLLOW_FAIL, e);
		}
	}
	
	/*팔로우 등록*/
	public FollowRequestDto createFollow(FollowRequestDto followDto) {
		try {
			/* 팔로우가 이미 되어있는지 확인 */
			Boolean followExist = isExistFollow(followDto.getMenteeMemberNo(), followDto.getMentorMemberNo());
			
			/* 팔로우 멘토 follow_count 증가*/
			Member mentorMember = memberRepository.findById(followDto.getMentorMemberNo()).get();
			mentorMember.getMentorProfile().setMentorFollowCount(mentorMember.getMentorProfile().getMentorFollowCount()+1); 
			memberRepository.save(mentorMember);
			
			/* 팔로우 저장 */
			Follow follow = Follow.toEntity(followDto);
			followReporitory.save(follow);
			return followDto;
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.CREATE_FOLLOW_FAIL, ResponseMessage.CREATE_FOLLOW_FAIL,e);
		}
	}
	
	/*팔로우 취소*/
	public Long deleteFollow(Long menteeNo, Long mentorNo) {
		try {
			
			Follow follow = followReporitory.findByMenteeMember_MemberNoAndMentorMember_MemberNo(menteeNo, mentorNo);
			/* 팔로우 멘토 follow_count감소*/
			followReporitory.findById(follow.getFollowNo()).get();
			
			Member mentorMember = follow.getMentorMember();
			mentorMember.getMentorProfile().setMentorFollowCount(mentorMember.getMentorProfile().getMentorFollowCount()-1); 
			memberRepository.save(mentorMember);
			
			/* 팔로우 삭제 */
			followReporitory.deleteById(follow.getFollowNo());
			
			return follow.getFollowNo(); 
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.DELETE_FOLLOW_FAIL, ResponseMessage.DELETE_FOLLOW_FAIL, e);
		}
	}
	
	/*팔로잉 리스트 출력(멘토리스트, 이름 순서)*/
	public Page<FollowResponseDto> getMentorList(Long memberNo, int pageNumber, int pageSize){
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<Follow> follows = followReporitory.findByMentorMember_MemberNo(memberNo,pageable);
			List<FollowResponseDto> followList = new ArrayList<>();
			
			for (Follow follow : follows) {
				followList.add(FollowResponseDto.toDto(follow));
			}
			
			return new PageImpl<>(followList, pageable,follows.getTotalElements());
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_MENTORLIST_FAIL, ResponseMessage.READ_MENTORLIST_FAIL, e);
		}
		
	}
	
	/*팔로워 수(멘티 수)*/
	public Integer countFollower(Long mentorMemberNo) {
		try {
			return followReporitory.countBymentorMember_MemberNo(mentorMemberNo);
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_MENTEE_COUNT_FAIL, ResponseMessage.READ_MENTEE_COUNT_FAIL, e);
		}
	}
	
	
}
