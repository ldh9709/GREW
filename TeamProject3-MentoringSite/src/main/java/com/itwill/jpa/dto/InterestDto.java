package com.itwill.jpa.dto;

import com.itwill.jpa.entity.Interest;

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
    private UserDto user;
    private CategoryDto category;

    /*
     * Entity -> DTO
     */
    public static InterestDto toDto(Interest interestEntity) {
        return InterestDto.builder()
                .interestNo(interestEntity.getInterestNo())
                .user(UserDto.toDto(interestEntity.getUser()))
                .category(CategoryDto.toDto(interestEntity.getCategory()))
                .build();
    }

    
}
