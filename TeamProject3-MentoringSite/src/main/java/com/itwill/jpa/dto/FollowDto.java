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
    private UserDto user;
    private UserDto followedUser;

    /*
     * Entity -> DTO
     */
    public static FollowDto toDto(Follow followEntity) {
        return FollowDto.builder()
                .followNo(followEntity.getFollowNo())
                .user(UserDto.toDto(followEntity.getUser()))
                .followedUser(UserDto.toDto(followEntity.getFollowedUser()))
                .build();
    }

 
}
