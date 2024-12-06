package com.itwill.jpa.service.member_information;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.member_information.CategoryDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.repository.member_information.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	/* 카테고리 등록 */
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = Category.toEntity(categoryDto);
		if(categoryDto.getParentCategoryNo() == null ) {
			category.setParentCategory(null);
		}
		categoryRepository.save(category);
		return categoryDto;
	}

	/* 카테고리 수정 */
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto) {
		Category category = categoryRepository.findById(categoryDto.getCategoryNo()).get();
		category.setCategoryName(categoryDto.getCategoryName());
		categoryRepository.save(category);	
		return categoryDto;
	}

	/* 카테고리 삭제 */
	@Override
	public CategoryDto deleteCategory(Long categoryNo) {
		/* 메인카테고리삭제 -> 서브카테고리까지 전체 삭제*/
		Category category = categoryRepository.findById(categoryNo).get();
		
		if(category.getParentCategory() == null) {
		}
		
		categoryRepository.deleteById(categoryNo);
		return CategoryDto.toDto(categoryRepository.findById(categoryNo).get());
	}

	/* 카테고리 상세보기 출력 */
	@Override
	public CategoryDto getCategory(Long categoryNo) {
		return CategoryDto.toDto(categoryRepository.findById(categoryNo).get());
	}
	
	/* 카테고리 리스트 출력 */
	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDto> categoriesDto = new ArrayList<>();
		
		for (Category category : categories) {
			categoriesDto.add(CategoryDto.toDto(category));
		}
		
		return categoriesDto;
	}
	
	
	
	
	
}

