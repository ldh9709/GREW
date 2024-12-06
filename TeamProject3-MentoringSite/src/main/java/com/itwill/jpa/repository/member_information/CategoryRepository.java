package com.itwill.jpa.repository.member_information;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.member_information.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	/*
	 * @EntityGraph(attributePaths = {"childCategories", "mentorProfiles",
	 * "interests"})
	 * 
	 * @Query("SELECT c FROM Category c WHERE c.categoryNo = :categoryNo")
	 * Optional<Category> findCategoryWithChildrenAndProfiles(@Param("categoryNo")
	 * Long categoryNo);
	 */
}
