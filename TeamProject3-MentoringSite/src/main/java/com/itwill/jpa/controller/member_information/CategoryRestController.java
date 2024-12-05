package com.itwill.jpa.controller.member_information;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.service.member_information.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;
	
	
}
