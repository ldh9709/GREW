package com.itwill.jpa.entity;


import com.itwill.jpa.dto.FollowDto;

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
    @JoinColumn(name = "user_no")
    private Member user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followed_user_no") // 팔로우 대상 유저와의 관계
    private Member followedUser;
 
    /*
     * DTO -> Entity
     */
    public static Follow toEntity(FollowDto followDto) {
        return Follow.builder()
                .followNo(followDto.getFollowNo())
                .user(Member.toEntity(followDto.getUser()))
                .followedUser(Member.toEntity(followDto.getFollowedUser()))
                .build();
    }
}
