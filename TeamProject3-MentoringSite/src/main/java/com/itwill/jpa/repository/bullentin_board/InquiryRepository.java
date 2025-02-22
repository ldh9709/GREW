package com.itwill.jpa.repository.bullentin_board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.bullentin_board.Inquiry;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

	/** 카테고리별 질문 **/
	// 조회수순
	@Query("SELECT i FROM Inquiry i " + 
			"WHERE i.category.categoryNo = :categoryNo "
			+ "AND i.inquiryStatus = 1" + 
			"ORDER BY i.inquiryViews DESC")
	Page<Inquiry> findByCategoryInquiryOrderByView(@Param("categoryNo") Long categoryNo,Pageable pageable);
	// 조회수순(대분류)
	@Query("SELECT i FROM Inquiry i " + 
			"WHERE i.category.parentCategory.categoryNo = :categoryNo "
			+ "AND i.inquiryStatus = 1" + 
			"ORDER BY i.inquiryViews DESC")
	Page<Inquiry> findByParentCategoryInquiryOrderByView(@Param("categoryNo") Long categoryNo,Pageable pageable);
	// 최신순
	@Query("SELECT i FROM Inquiry i " + 
			"WHERE i.category.categoryNo = :categoryNo "
			+ "AND i.inquiryStatus = 1" + 
			"ORDER BY i.inquiryDate DESC")
	Page<Inquiry> findByCategoryInquiryOrderByDate(@Param("categoryNo") Long categoryNo,Pageable pageable);
	// 최신순(대분류)
	@Query("SELECT i FROM Inquiry i " + 
			"WHERE i.category.parentCategory.categoryNo = :categoryNo "
			+ "AND i.inquiryStatus = 1" + 
			"ORDER BY i.inquiryDate DESC")
	Page<Inquiry> findByParentCategoryInquiryOrderByDate(@Param("categoryNo") Long categoryNo,Pageable pageable);

	// 답변갯수순
	@Query("SELECT i FROM Inquiry i " + 
			"WHERE i.category.categoryNo = :categoryNo "
			+ "AND i.inquiryStatus = 1"
			+ "ORDER BY (SELECT COUNT(a) FROM Answer a WHERE a.inquiry = i) DESC")
	Page<Inquiry> findByCategoryInquiryOrderByAnswer(@Param("categoryNo") Long categoryNo,Pageable pageable);
	// 답변갯수순
	@Query("SELECT i FROM Inquiry i " + 
			"WHERE i.category.parentCategory.categoryNo = :categoryNo "
			+ "AND i.inquiryStatus = 1"
			+ "ORDER BY (SELECT COUNT(a) FROM Answer a WHERE a.inquiry = i) DESC")
	Page<Inquiry> findByParentCategoryInquiryOrderByAnswer(@Param("categoryNo") Long categoryNo,Pageable pageable);

	/** 전체질문 **/
	// 조회수순
	@Query("SELECT i FROM Inquiry i "
			+ "WHERE i.inquiryStatus = 1"
			+ "ORDER BY i.inquiryViews DESC")
	Page<Inquiry> findAllInquiryOrderByView(Pageable pageable);
	//최신순
	@Query("SELECT i FROM Inquiry i "
			+ "WHERE i.inquiryStatus = 1"
			+ "ORDER BY i.inquiryDate DESC")
	Page<Inquiry> findAllInquiryOrderByDate(Pageable pageable);

	// 답변갯수순
	@Query("SELECT i FROM Inquiry i "
			+ "WHERE i.inquiryStatus = 1" + 
			"ORDER BY (SELECT COUNT(a) FROM Answer a WHERE a.inquiry = i) DESC")
	Page<Inquiry> findAllInquiriOrderByAnswer(Pageable pageable);
	
	//검색기능
	@Query("SELECT i FROM Inquiry i "
		     + "JOIN i.member m "
		     + "WHERE (i.inquiryTitle LIKE %:search% "
		     + "OR i.inquiryContent LIKE %:search% "
		     + "OR m.memberName LIKE %:search%) "
		     + "AND i.inquiryStatus = 1")
	Page<Inquiry> findInquiryBySearch(@Param("search") String search,Pageable pageable);

	// 내가 쓴 질문 리스트 출력
	@Query("SELECT i FROM Inquiry i WHERE i.member.memberNo = :memberNo AND i.inquiryStatus = 1 " +
		       "ORDER BY i.inquiryDate DESC")
	Page<Inquiry> findByMemberMemberNoOrderByInquiryDateDesc(@Param("memberNo") Long memberNo, Pageable pageable);
	
}
