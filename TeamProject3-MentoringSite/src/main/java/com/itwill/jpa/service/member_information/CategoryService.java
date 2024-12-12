package com.itwill.jpa.service.member_information;

import java.util.List;

import com.itwill.jpa.dto.member_information.CategoryRequestDto;
import com.itwill.jpa.dto.member_information.CategoryResponseDto;

public interface CategoryService {
	
	CategoryRequestDto createCategory(CategoryRequestDto categoryDto);
	CategoryRequestDto updateCategory(CategoryRequestDto categoryDto);
	CategoryResponseDto getCategoriesBycategoryNo(Long categoryNo);
	List<CategoryResponseDto> getCategories();
}
