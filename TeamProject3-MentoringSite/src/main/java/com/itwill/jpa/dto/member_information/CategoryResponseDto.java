package com.itwill.jpa.dto.member_information;

import com.itwill.jpa.entity.member_information.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponseDto {
	private String primaryCategoryName;
	private String subCategoryName;
	
	public static CategoryResponseDto toDto(Category category) {
		return CategoryResponseDto.builder()
				.primaryCategoryName(category.getParentCategory().getCategoryName())
				.subCategoryName(category.getCategoryName())
				.build();
	}
	
}
