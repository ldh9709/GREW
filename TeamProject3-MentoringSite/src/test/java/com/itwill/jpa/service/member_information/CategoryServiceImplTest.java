package com.itwill.jpa.service.member_information;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryServiceImplTest {
	
	@Autowired
	private CategoryService categoryService;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetCategoriesBycategoryNo() {
	}

	@Test
	void testGetCategories() {
		System.out.println(categoryService.getCategories() == null);
	}

	@Test
	void testGetCategroyNo() {
	}

}
