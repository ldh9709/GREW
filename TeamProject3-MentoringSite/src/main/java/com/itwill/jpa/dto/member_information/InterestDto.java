package com.itwill.jpa.dto.member_information;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.itwill.jpa.entity.member_information.Interest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestDto {
	
	@Schema(description = "관심사 고유 번호", example = "1")
	private Long interestNo;
	
	@Schema(description = "멤버 고유 번호", example = "1")
    private Long memberNo;
    
	@Schema(description = "카테고리 고유 번호", example = "2")
    private Long categoryNo;
    
    //// 문자열 또는 정수를 처리할 수 있는 생성자
    @JsonCreator
    public InterestDto(String categoryNoStr) {
    	this.interestNo = null;
    	this.categoryNo = Long.parseLong(categoryNoStr);
    }
    
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
