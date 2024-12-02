package com.itwill.jpa.entity.user_information;


import com.itwill.jpa.dto.user_information.InterestDto;

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
@Table(name = "Interest")
public class Interest {

    @Id
    @SequenceGenerator(name = "interest_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "interest_SEQ")
   
    @Column(name = "interest_no" )
    private Long interestNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no" ,nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no" ,nullable = false)
    private Category category;
    
    
    public static Interest toEntity(InterestDto interestDto) {
        return Interest.builder()
        		.interestNo(interestDto.getInterestNo())
                .member(Member.toEntity(interestDto.getMember()))
                .category(Category.toEntity(interestDto.getCategory()))
                .build();
    }
}
