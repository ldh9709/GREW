package com.itwill.jpa.entity.member_information;


import com.itwill.jpa.dto.member_information.FollowRequestDto;

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

    // 팔로우를 한 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_member", nullable = false)
    private Member menteeMember;

    //팔로우 대상 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_member", nullable = false) 
    private Member mentorMember;
 
    /*
     * DTO -> Entity
     */
    public static Follow toEntity(FollowRequestDto dto) {
        return Follow.builder()
                .followNo(dto.getFollowNo())
                .menteeMember(Member.builder().memberNo(dto.getMenteeMemberNo()).build())
                .mentorMember(Member.builder().memberNo(dto.getMentorMembedNo()).build())
                .build();
    }
}
