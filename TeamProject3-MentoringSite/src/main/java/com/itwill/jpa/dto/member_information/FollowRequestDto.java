package com.itwill.jpa.dto.member_information;

import com.itwill.jpa.entity.member_information.Follow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowRequestDto {
    private Long followNo;
    private Long menteeMemberNo;
    private Long mentorMemberNo;
    
    public static FollowRequestDto toDto(Follow entity) {
    	return FollowRequestDto.builder()
    			.followNo(entity.getFollowNo())
    			.menteeMemberNo(entity.getMenteeMember().getMemberNo())
    			.mentorMemberNo(entity.getMentorMember().getMemberNo())
    			.build();
    }
}
