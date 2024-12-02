package com.itwill.jpa.dto.user_information;

import com.itwill.jpa.entity.user_information.Follow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowDto {
    private Long followNo;
    private MemberDto followerMember;
    private MemberDto followedMember;

    /*
     * Entity -> DTO
     */
    public static FollowDto toDto(Follow followEntity) {
        return FollowDto.builder()
                .followNo(followEntity.getFollowNo())
                .followerMember(MemberDto.toDto(followEntity.getFollowerMember()))
                .followedMember(MemberDto.toDto(followEntity.getFollowedMember()))
                .build();
    }

 
}
