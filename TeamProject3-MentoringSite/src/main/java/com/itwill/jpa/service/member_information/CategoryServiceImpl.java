package com.itwill.jpa.service.member_information;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.member_information.CategoryRequestDto;
import com.itwill.jpa.dto.member_information.CategoryResponseDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.repository.member_information.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	/* 카테고리 등록 */
	@Override
	public CategoryRequestDto createCategory(CategoryRequestDto categoryDto) {
		Category category = Category.toEntity(categoryDto);
		if(categoryDto.getParentCategoryNo() == null ) {
			category.setParentCategory(null);
		}
		categoryRepository.save(category);
		return categoryDto;
	}

	/* 카테고리 수정 */
	@Override
	public CategoryRequestDto updateCategory(CategoryRequestDto categoryDto) {
		Category category = categoryRepository.findById(categoryDto.getCategoryNo()).get();
		category.setCategoryName(categoryDto.getCategoryName());
		categoryRepository.save(category);	
		return categoryDto;
	}

	/* 
	 카테고리 삭제
	@Override
	public CategoryRequestDto deleteCategory(Long categoryNo) {
		Category category = categoryRepository.findById(categoryNo).get();
		
		categoryRepository.delete(category);
		return null;
	} 
 	*/	
 	
	/* 카테고리 리스트 출력 */
	@Override
	public List<CategoryResponseDto> getCategories() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryResponseDto> categoriesDto = new ArrayList<>();
		
		for (Category category : categories) {
			categoriesDto.add(CategoryResponseDto.toDto(category));
		}
		
		return categoriesDto;
	}
	
}

