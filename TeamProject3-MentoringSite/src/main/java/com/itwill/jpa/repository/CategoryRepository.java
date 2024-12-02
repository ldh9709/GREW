package com.itwill.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.user_information.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // 특정 이름의 카테고리 조회
    List<Category> findByName(String name);

    // 특정 레벨의 카테고리 조회
    List<Category> findByLevel(Integer level);

    // 이름과 레벨로 카테고리 조회
    List<Category> findByNameAndLevel(String name, Integer level);


}
