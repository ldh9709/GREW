package com.itwill.jpa.dto.member_information;

import java.time.LocalDateTime;
import java.util.List;

import com.itwill.jpa.entity.member_information.Interest;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.role.Role;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
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
	           schema = @Schema(description = "멤버 관심사 리스트", example = """
				[
				  {"interestNo": 1, "memberNo": 1, "categoryNo": 2},
				  {"interestNo": 2, "memberNo": 1, "categoryNo": 3},
				  {"interestNo": 3, "memberNo": 1, "categoryNo": 4}
				]""")
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
//	            .memberPoint(memberEntity.getMemberPoints())
//	            .memberJoinDate(memberEntity.getMemberJoinDate())
//	            .memberReportCount(memberEntity.getMemberReportCount())
	            .memberRole(memberEntity.getMemberRole())
	            .memberProvider(memberEntity.getMemberProvider())
	            .interests(memberEntity.getInterests().stream()
	            		.map(InterestDto::toDto)
	            		.toList())
	            .build();
	}
	
	public static MemberDto toBasicDto(Member memberEntity) {
		return MemberDto.builder()
				.memberNo(memberEntity.getMemberNo())
	            .memberId(memberEntity.getMemberId())
	            .memberPassword(memberEntity.getMemberPassword())
	            .memberEmail(memberEntity.getMemberEmail())
	            .memberName(memberEntity.getMemberName())
	            .memberStatus(memberEntity.getMemberStatus())
//	            .memberPoint(memberEntity.getMemberPoints())
//	            .memberJoinDate(memberEntity.getMemberJoinDate())
//	            .memberReportCount(memberEntity.getMemberReportCount())
	            .memberRole(memberEntity.getMemberRole())
	            .memberProvider(memberEntity.getMemberProvider())
	            .build();
	}
	
	
}
