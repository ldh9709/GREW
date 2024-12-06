package com.itwill.jpa.entity.member_information;


import com.itwill.jpa.dto.member_information.InterestDto;

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
@Table(name = "interest")
public class Interest {

    @Id
    @SequenceGenerator(name = "interest_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "interest_SEQ")
   
    @Column(name = "interest_no" )
    private Long interestNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no" ,nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
