package com.itwill.jpa.entity.member_information;


import com.itwill.jpa.dto.member_information.FollowDto;

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
@Table(name = "follow")
public class Follow {

    @Id
    @SequenceGenerator(name = "follow_no_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "follow_no_SEQ")
    @Column(name = "follow_no")
    private Long followNo;

    // 팔로우를 한 사용자(멘티)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_member", nullable = false)
    private Member followerMember;

 // 팔로우 대상 사용자(멘토)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followed_member", nullable = false) 
    private Member followedMember;
 
    /*
     * DTO -> Entity
     */
    public static Follow toEntity(FollowDto dto) {
        return Follow.builder()
                .followNo(dto.getFollowNo())
                .followerMember(Member.builder().memberNo(dto.getFollowerMember()).build())
                .followedMember(Member.builder().memberNo(dto.getFollowedMember()).build())
                .build();
    }
}
