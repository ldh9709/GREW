package com.itwill.jpa.repository.member_information;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.member_information.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@EntityGraph(attributePaths = {"category.parentCategory"})
	public List<Category> findByParentCategory_categoryNo(@Param("parentCategoryNo") Long ParentcategoryNo);
}
