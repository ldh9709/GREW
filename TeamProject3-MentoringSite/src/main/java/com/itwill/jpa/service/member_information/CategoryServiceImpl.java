package com.itwill.jpa.service.member_information;

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
	public void createCategory(CategoryDto categoryDto) {
		Category category = Category.toEntity(categoryDto);
		categoryRepository.save(category);
	}

	/* 카테고리 수정 */
	@Override
	public void updateCategory(CategoryDto categoryDto) {
		
	}

	/* 카테고리 삭제 */
	@Override
	public void deleteCategory(Long categoryNo) {
		
	}

	/* 카테고리 출력 */
	@Override
	public List<CategoryDto> getCategory(Long categoryNo) {
		return null;
	}
	
	
	
	
	
}
