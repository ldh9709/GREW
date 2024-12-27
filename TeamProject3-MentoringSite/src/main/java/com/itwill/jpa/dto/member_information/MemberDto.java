package com.itwill.jpa.dto.member_information;

import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.itwill.jpa.entity.member_information.Interest;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.role.Role;
import com.itwill.jpa.validation.annotation.Email;
import com.itwill.jpa.validation.validator.MemberEmailValidator;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
	
	private Long memberNo;
	private String memberId;
	private String memberPassword;
	private String memberEmail;
	private String memberName;
	private Integer memberStatus;
	private Integer memberPoint;
	private LocalDateTime memberJoinDate;
	private Integer memberReportCount;
	private Role memberRole;
	private String memberProvider;
	private Long mentorProfileNo;
	
	private List<InterestDto> interests;
	
	/* 관심사 포함 */
	public static MemberDto toDto(Member memberEntity) {
		return MemberDto.builder()
				.memberNo(memberEntity.getMemberNo())
	            .memberId(memberEntity.getMemberId())
	            .memberPassword(memberEntity.getMemberPassword())
	            .memberEmail(memberEntity.getMemberEmail())
	            .memberName(memberEntity.getMemberName())
	            .memberStatus(memberEntity.getMemberStatus())
	            .memberPoint(memberEntity.getMemberPoints())
	            .memberJoinDate(memberEntity.getMemberJoinDate())
	            .memberReportCount(memberEntity.getMemberReportCount())
	            .memberRole(memberEntity.getMemberRole())
	            .memberProvider(memberEntity.getMemberProvider())
	            .mentorProfileNo(memberEntity.getMentorProfile() !=null ? memberEntity.getMentorProfile().getMentorProfileNo() : 0L)
	            .interests(memberEntity.getInterests().stream()
	            		.map(InterestDto::toDto)
	            		.toList())
	            .build();
	}
	
	
	/* 관심사 미포함 */
	public static MemberDto toBasicDto(Member memberEntity) {
		return MemberDto.builder()
				.memberNo(memberEntity.getMemberNo())
	            .memberId(memberEntity.getMemberId())
	            .memberPassword(memberEntity.getMemberPassword())
	            .memberEmail(memberEntity.getMemberEmail())
	            .memberName(memberEntity.getMemberName())
	            .memberStatus(memberEntity.getMemberStatus())
	            .memberPoint(memberEntity.getMemberPoints())
	            .memberJoinDate(memberEntity.getMemberJoinDate())
	            .memberReportCount(memberEntity.getMemberReportCount())
	            .memberRole(memberEntity.getMemberRole())
	            .memberProvider(memberEntity.getMemberProvider())
	            .build();
	}
	
	public static MemberDto tofindDto(Member memberEntity) {
		return MemberDto.builder()
				.memberNo(memberEntity.getMemberNo())
	            .memberEmail(memberEntity.getMemberEmail())
	            .memberName(memberEntity.getMemberName())
	            .build();
	}
	

	@Builder
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	//아이디 찾기 시 사용
	public static class findId {
		
		private String memberName;
		@Email
		private String memberEmail;
	}
	
	@Builder
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	//아이디 찾기 시 사용
	public static class certificationCode {
		
		@Email
		private String memberEmail;
		
		private Integer inputCode;
	}
	
	
	@Builder
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	//임시 비밀번호 발급 시 사용
	public static class findPassword {
		private String memberName;
		@Email
		private String email;
	}
	
	
	/*
	 * 일반 회원 가입에 사용
	 * builderClassName = "JoinForm"으로 설정하면 JoinForm이라는 이름의 빌더 클래스가 생성된다.
	 * builderMethodName = "JoinForm"으로 설정하면 JoinForm() 메서드를 통해 빌더 객체를 생성할 수 있다.
	 */
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
    public static class JoinFormDto {
		@Email
        private String email;
        
    }
	

	
}
