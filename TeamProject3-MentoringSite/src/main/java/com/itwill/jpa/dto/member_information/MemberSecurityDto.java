package com.itwill.jpa.dto.member_information;

import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.role.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class MemberSecurityDto {
	
	private Long memberNo;
	private String memberId;
	private String memberPassword;
	private String memberEmail;
	private String memberName;
	private Integer memberStatus;	
	private Role memberRole;
	private String memberProvider;
	
	
	public static MemberSecurityDto toDto(Member entity) {
		return MemberSecurityDto.builder()
				.memberNo(entity.getMemberNo())
	            .memberId(entity.getMemberId())
	            .memberPassword(entity.getMemberPassword())
	            .memberEmail(entity.getMemberEmail())
	            .memberName(entity.getMemberName())
	            .memberStatus(entity.getMemberStatus())
	            .memberRole(entity.getMemberRole())
	            .memberProvider(entity.getMemberProvider())
	            .build();
	}
	
	/*
	 * SNS 회원가입에 사용
	 */
	@Builder(builderClassName = "JoinOAuth2", builderMethodName = "JoinOAuth2")
    public MemberSecurityDto(String memberEmail, String memberPassword, String memberProvider) {
		this.memberEmail = memberEmail;
		this.memberPassword = memberPassword;
		this.memberProvider = memberProvider;
	}
	
	
}
