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
    @SequenceGenerator(name = "interest_no_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "interest_no_SEQ")
   
    @Column(name = "interest_no" )
    private Long interestNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no" ,nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no" ,nullable = false)
    private Category category;
    
    public static Interest toEntity(InterestDto interestDto) {
        
    	Member member = Member.builder()
						      .memberNo(interestDto.getMemberNo())
						      .build();
        
        Category category = Category.builder()
					        		.categoryNo(interestDto.getCategoryNo())
					        		.build();
					        		
    	return Interest.builder()
        		.interestNo(interestDto.getInterestNo())
                .member(member)
                .category(category)
                .build();
    }
}
