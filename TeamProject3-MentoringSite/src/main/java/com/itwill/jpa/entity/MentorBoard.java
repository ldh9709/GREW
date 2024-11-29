package com.itwill.jpa.entity;

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
@Table(name = "MentorBoard")
public class MentorBoard {

    @Id
    @SequenceGenerator(name = "mentorboard_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentorboard_seq")
    private Long mentorBoardNo;

    private String mentorBoardTitle;
    private String mentorBoardContent;
    private String mentorBoardImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private Member user;
}
