package com.itwill.jpa.repository.member_information;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.jpa.dto.member_information.CategoryRequestDto;
import com.itwill.jpa.dto.member_information.CategoryResponseDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.service.member_information.CategoryService;

import jakarta.transaction.Transactional;

@SpringBootTest
class CategoryRepositoryTest {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	@Transactional
	void findByCategoryNo() {
		Category  category = categoryRepository.findByCategoryNo(1L);
		List<CategoryResponseDto> categories = new ArrayList<>();
		
		for (Category child : category.getChildCategories()) {
			categories.add(CategoryResponseDto.toDto(category));
		}
		
		System.out.println(categories);
	}
	
}
