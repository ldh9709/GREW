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
	
    private Long memberNo;
    
    private Long categoryNo;

    /*
     * Entity -> DTO
     */
    public static InterestDto toDto(Interest interestEntity) {
        return InterestDto.builder()
        		.interestNo(interestEntity.getInterestNo())
                .memberNo(interestEntity.getMember().getMemberNo())
                .categoryNo(interestEntity.getCategory().getCategoryNo())
                .build();
    }
    
    
    
}
