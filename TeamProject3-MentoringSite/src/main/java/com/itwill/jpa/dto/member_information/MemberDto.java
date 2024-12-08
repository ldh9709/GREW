package com.itwill.jpa.dto.member_information;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itwill.jpa.entity.member_information.Interest;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.role.Role;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private Role memberRole;
	private String memberProvider;
	private String memberProviderId;
	
	private List<InterestDto> interests;
	
	
	 /* Entitiy -> DTO*/
	public static MemberDto toDto(Member memberEntity) {
		return MemberDto.builder()
				.memberNo(memberEntity.getMemberNo())
	            .memberId(memberEntity.getMemberId())
	            .memberPassword(memberEntity.getMemberPassword())
	            .memberEmail(memberEntity.getMemberEmail())
	            .memberName(memberEntity.getMemberName())
	            .memberStatus(memberEntity.getMemberStatus())
	            .interests(memberEntity.getInterests().stream()
	            		.map(InterestDto::toDto)
	            		.toList())
	            .build();
	}
	
	
	/* JWT 토큰을 생성하는데 필요한 사용자 정보 */
	public Map<String, Object> getClaims(){
		Map<String, Object> dataMap = new HashMap<>();
		
		dataMap.put("id", memberId);
		dataMap.put("email", memberEmail);
		dataMap.put("password", memberPassword);
		dataMap.put("name", memberName);
		dataMap.put("role", memberRole);
		dataMap.put("provider", memberProvider);
		dataMap.put("providerId", memberProviderId);
		
		return dataMap;
		
	}
	
	
}
