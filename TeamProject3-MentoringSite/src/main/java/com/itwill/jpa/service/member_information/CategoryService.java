package com.itwill.jpa.service.member_information;

import java.util.List;

import com.itwill.jpa.dto.member_information.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto);
	CategoryDto deleteCategory(Long categoryNo);
	CategoryDto getCategory(Long categoryNo);
	List<CategoryDto> getCategories();
}
