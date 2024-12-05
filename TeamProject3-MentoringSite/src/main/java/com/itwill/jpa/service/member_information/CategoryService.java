package com.itwill.jpa.service.member_information;

import java.util.List;

import com.itwill.jpa.dto.member_information.CategoryDto;

public interface CategoryService {
	
	public void createCategory(CategoryDto categoryDto);
	public void updateCategory(CategoryDto categoryDto);
	public void deleteCategory(Long categoryNo);
	public List<CategoryDto> getCategory(Long categoryNo);
}
