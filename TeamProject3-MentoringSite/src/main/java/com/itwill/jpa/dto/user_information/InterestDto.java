package com.itwill.jpa.dto.user_information;

import com.itwill.jpa.entity.user_information.Interest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestDto {
    private MemberDto member;
    private CategoryDto category;

    /*
     * Entity -> DTO
     */
    public static InterestDto toDto(Interest interestEntity) {
        return InterestDto.builder()
                .member(MemberDto.toDto(interestEntity.getMember()))
                .category(CategoryDto.toDto(interestEntity.getCategory()))
                .build();
    }

    
}
