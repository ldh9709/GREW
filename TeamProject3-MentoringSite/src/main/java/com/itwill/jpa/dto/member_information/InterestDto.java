package com.itwill.jpa.dto.member_information;

import com.itwill.jpa.entity.member_information.Interest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestDto {
	
	private Long interestNo;
    private MemberDto member;
    private CategoryRequestDto category;

    /*
     * Entity -> DTO
     */
    public static InterestDto toDto(Interest interestEntity) {
        return InterestDto.builder()
        		.interestNo(interestEntity.getInterestNo())
                .member(MemberDto.toDto(interestEntity.getMember()))
                .category(CategoryRequestDto.toDto(interestEntity.getCategory()))
                .build();
    }

    
}
