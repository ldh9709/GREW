package com.itwill.jpa.dto.member_information;

import java.util.HashMap;
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
	
	@Schema(description = "멤버 번호", example = "1")
	private Long memberNo;
	
	@Schema(description = "멤버 ID", example = "aaa")
	private String memberId;
	
	@Schema(description = "멤버 PASSWORD", example = "aaa")
	private String memberPassword;
	
	@Schema(description = "멤버 EMAIL", example = "aaa@naver.com")
	private String memberEmail;
	
	@Schema(description = "멤버 NAME", example = "김진영")
	private String memberName;
	
	@Schema(description = "멤버 STATUS", example = "1")
	private Integer memberStatus;	
	
	@Schema(description = "멤버 ROLE", example = "ROLE_MENTEE")
	private Role memberRole;
	
	@Schema(description = "멤버 PROVIDER", example = "NULL")
	private String memberProvider;
	
	@ArraySchema(
	        schema = @Schema(description = "멤버 관심사 리스트", example = "[\n" +
	                "  {\"interestNo\": null, \"memberNo\": 1, \"categoryNo\": 19},\n" +
	                "  {\"interestNo\": null, \"memberNo\": 1, \"categoryNo\": 20},\n" +
	                "  {\"interestNo\": null, \"memberNo\": 1, \"categoryNo\": 21}\n" +
	                "]")
	    )
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
	            .memberRole(memberEntity.getMemberRole())
	            .memberProvider(memberEntity.getMemberProvider())
	            .interests(memberEntity.getInterests().stream()
	            		.map(InterestDto::toDto)
	            		.toList())
	            .build();
	}
	
	
	public static MemberDto toSecurityDto(Member memberEntity) {
		return MemberDto.builder()
				.memberNo(memberEntity.getMemberNo())
	            .memberId(memberEntity.getMemberId())
	            .memberPassword(memberEntity.getMemberPassword())
	            .memberEmail(memberEntity.getMemberEmail())
	            .memberName(memberEntity.getMemberName())
	            .memberStatus(memberEntity.getMemberStatus())
	            .memberRole(memberEntity.getMemberRole())
	            .memberProvider(memberEntity.getMemberProvider())
	            .build();
	}
	
	
	
	@Builder
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	//임시 비밀번호 발급 시 사용
	public static class findPassword {
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
		
        private String email;
        
    }
	
	
	
	
	
}
