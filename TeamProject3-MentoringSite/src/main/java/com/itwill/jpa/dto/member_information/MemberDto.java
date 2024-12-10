package com.itwill.jpa.dto.member_information;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itwill.jpa.entity.member_information.Interest;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.role.Role;
import com.itwill.jpa.validation.annotation.Email;

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
	private Role memberRole;
	private String memberProvider;
	
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
		
		return dataMap;
		
	}
	
	@Builder
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	//임시 비밀번호 발급 시 사용
	public static class findPassword {
		@Email
		private String email;
		
		private String MemberId;
	}
	
	/*
	 * 일반 회원 가입에 사용
	 * builderClassName = "JoinForm"으로 설정하면 JoinForm이라는 이름의 빌더 클래스가 생성된다.
	 * builderMethodName = "JoinForm"으로 설정하면 JoinForm() 메서드를 통해 빌더 객체를 생성할 수 있다.
	 */
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder(builderClassName = "JoinForm", builderMethodName = "JoinForm")
    public static class JoinFormDto {
        private String email;
        
        private Integer tempNo;
    }
	
	/*
	 * SNS 회원가입에 사용
	 */
	@Builder(builderClassName = "JoinOAuth2", builderMethodName = "JoinOAuth2")
    public static class JoinOAuth2Dtox {
        private String email;
        private String password;
        private String provider;
    }
	
	
	
}
