package com.itwill.jpa.dto.user_information;


import com.itwill.jpa.entity.user_information.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private String categoryName;
    private Integer categoryLevel;

    /*
     * Entity -> DTO
     */
    public static CategoryDto toDto(Category categoryEntity) {
        return CategoryDto.builder()
                .categoryName(categoryEntity.getCategoryName())
                .categoryLevel(categoryEntity.getCategoryLevel())
                .build();
    }

}
