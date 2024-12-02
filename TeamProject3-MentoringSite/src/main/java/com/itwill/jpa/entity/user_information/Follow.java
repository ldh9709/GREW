package com.itwill.jpa.entity.user_information;


import com.itwill.jpa.dto.user_information.FollowDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Follow")
public class Follow {

    @Id
    @SequenceGenerator(name = "follow_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "follow_seq")
    private Long followNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_member") // 팔로우를 한 사용자
    private Member followerMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followed_member") // 팔로우 대상 사용자
    private Member followedMember;
 
    /*
     * DTO -> Entity
     */
    public static Follow toEntity(FollowDto followDto) {
        return Follow.builder()
                .followNo(followDto.getFollowNo())
                .followerMember(Member.toEntity(followDto.getFollowerMember()))
                .followedMember(Member.toEntity(followDto.getFollowedMember()))
                .build();
    }
}
