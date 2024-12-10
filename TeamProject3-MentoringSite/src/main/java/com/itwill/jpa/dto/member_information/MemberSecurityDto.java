package com.itwill.jpa.dto.member_information;

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
	private String memberProviderId;
	
	
	public static MemberSecurityDto toDto(Member entity) {
		return MemberSecurityDto.builder()
				.memberNo(entity.getMemberNo())
	            .memberId(entity.getMemberId())
	            .memberPassword(entity.getMemberPassword())
	            .memberEmail(entity.getMemberEmail())
	            .memberName(entity.getMemberName())
	            .memberStatus(entity.getMemberStatus())
	            .build();
	}
	
}
