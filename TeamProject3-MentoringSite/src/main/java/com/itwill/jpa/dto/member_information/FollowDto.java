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
public class FollowDto {
    private Long followNo;
    private Long followerMember;
    private Long followedMember;

    /*
     * Entity -> DTO
     */
    public static FollowDto toDto(Follow entity) {
        return FollowDto.builder()
                .followNo(entity.getFollowNo())
                .followerMember(entity.getFollowerMember().getMemberNo())
                .followedMember(entity.getFollowedMember().getMemberNo())
                .build();
    }

 
}
