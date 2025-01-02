package com.itwill.jpa.dto.member_information;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.itwill.jpa.entity.member_information.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {
	
	private Long categoryNo;
    private String categoryName;
    private Integer categoryDepth;
    private List<CategoryResponseDto> childCategories;
    private CategoryResponseDto parentCategory;

    /*
     * Entity -> DTO
     */
    public static CategoryResponseDto toBasic(Category categoryEntity) {
        return CategoryResponseDto.builder()
        		.categoryNo(categoryEntity.getCategoryNo())
                .categoryName(categoryEntity.getCategoryName())
                .categoryDepth(categoryEntity.getCategoryDepth())
                .build();
    }
    
    public static CategoryResponseDto toDto(Category categoryEntity) {
    	List<CategoryResponseDto> childcategoryList = new ArrayList<>();
    	
    	if(categoryEntity.getChildCategories() != null) {
    		for (Category category : categoryEntity.getChildCategories()) {
    			childcategoryList.add(CategoryResponseDto.toBasic(category));
    		}
    	}
    	
        return CategoryResponseDto.builder()
                .categoryNo(categoryEntity.getCategoryNo())
                .categoryName(categoryEntity.getCategoryName())
                .categoryDepth(categoryEntity.getCategoryDepth())
                .childCategories(childcategoryList)
                .parentCategory(categoryEntity.getParentCategory() != null 
                    ? CategoryResponseDto.toBasic(categoryEntity.getParentCategory()) 
                    : null)
                .build();
    }

}
