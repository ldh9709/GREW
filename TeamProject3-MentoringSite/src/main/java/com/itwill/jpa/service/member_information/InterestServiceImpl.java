package com.itwill.jpa.service.member_information;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.member_information.InterestDto;
import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Interest;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.InterestRepository;
import com.itwill.jpa.repository.member_information.MemberRepository;

@Service
public class InterestServiceImpl implements InterestService {
	
	@Autowired
	InterestRepository interestRepository;
	
	/* 관심사DTO 객체로 관심사 저장 */
	@Override
	public Interest saveInterest(InterestDto interestDto) {
		//DTO객체 Entity로 변환
		Interest interest = Interest.toEntity(interestDto);
		
		//저장
		return interestRepository.save(interest);
	}
	
	/* 관심사 번호로 관심사 삭제 */
	@Override
	public Interest deleteInterest(Long interestNo) {
		//관심사 번호로 관심사 찾기
		Interest interest = interestRepository.findByInterestNo(interestNo);
		
		//관심사 삭제
		interestRepository.deleteById(interestNo);
		
		//삭제한 관심사 반환
		return interest;
	}
	
	/* 관심사 번호로 관심사 객체 조회 */
	@Override
	public Interest getInterest(Long interestNo) {
		//관심사 번호로 관심사 찾기
		return interestRepository.findByInterestNo(interestNo);
	}
	
	/* 사용자 번호로 관심사 리스트 조회 */
	@Override
	public List<Interest> getInterestList(Long memberNo) {
		
		return interestRepository.findByMemberMemberNo(memberNo);
	}
	
	
	

}
