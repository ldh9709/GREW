package com.itwill.jpa.dto;

import com.itwill.jpa.entity.Follow;

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
    private MemberDto member;
    private MemberDto followedMember;

    /*
     * Entity -> DTO
     */
    public static FollowDto toDto(Follow followEntity) {
        return FollowDto.builder()
                .followNo(followEntity.getFollowNo())
                .member(MemberDto.toDto(followEntity.getMember()))
                .followedMember(MemberDto.toDto(followEntity.getFollowedMember()))
                .build();
    }

 
}
