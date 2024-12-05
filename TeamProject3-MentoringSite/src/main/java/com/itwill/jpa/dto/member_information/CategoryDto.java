package com.itwill.jpa.dto.member_information;


import com.itwill.jpa.entity.member_information.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	
	private Long categoryNo;
    private String categoryName;
    private Integer categoryDepth;
    private Long parentCategoryNo;

    /*
     * Entity -> DTO
     */
    public static CategoryDto toDto(Category categoryEntity) {
        return CategoryDto.builder()
        		.categoryNo(categoryEntity.getCategoryNo())
                .categoryName(categoryEntity.getCategoryName())
                .categoryDepth(categoryEntity.getCategoryDepth())
                .parentCategoryNo(
                		categoryEntity.getParentCategory() == null ? 0L : categoryEntity.getParentCategory().getCategoryNo()
                		)
                .build();
    }

}
