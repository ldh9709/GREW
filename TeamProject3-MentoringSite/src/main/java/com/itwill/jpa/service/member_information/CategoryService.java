package com.itwill.jpa.service.member_information;

import java.util.List;

import com.itwill.jpa.dto.member_information.CategoryRequestDto;
import com.itwill.jpa.dto.member_information.CategoryResponseDto;

public interface CategoryService {
	
	//카테고리 생성
	CategoryRequestDto createCategory(CategoryRequestDto categoryDto);
	//카테고리 수정
	CategoryRequestDto updateCategory(CategoryRequestDto categoryDto);
	//카테고리 조회(카테고리별)
	CategoryResponseDto getCategoriesBycategoryNo(Long categoryNo);
	//카테고리 전체 조회
	List<CategoryResponseDto> getCategories();
}
