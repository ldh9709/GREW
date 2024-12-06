package com.itwill.jpa.repository.bullentin_board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.bullentin_board.Inquiry;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
	//PK로 질문 조회
	Inquiry findByInquiryNo(Long inquiryNo);
	/** 카테고리별 질문 **/
	// 조회수순
	@Query("SELECT i FROM Inquiry i " + 
			"WHERE i.category.categoryNo = :categoryNo " + 
			"ORDER BY i.inquiryViews DESC")
	List<Inquiry> findByCategoryInquiryOrderByView(@Param("categoryNo") Long categoryNo);

	// 답변갯수순
	@Query("SELECT i FROM Inquiry i " + 
			"WHERE i.category.categoryNo = :categoryNo "
			+ "ORDER BY (SELECT COUNT(a) FROM Answer a WHERE a.inquiry = i) DESC")
	List<Inquiry> findByCategoryInquiryOrderByAnswer(@Param("categoryNo") Long categoryNo);

	/** 전체질문 **/
	// 조회수순
	@Query("SELECT i FROM Inquiry i ORDER BY i.inquiryViews DESC")
	Page<Inquiry> findAllInquiryOrderByView(Pageable pageable);

	// 답변갯수순
	@Query("SELECT i FROM Inquiry i " + 
			"ORDER BY (SELECT COUNT(a) FROM Answer a WHERE a.inquiry = i) DESC")
	List<Inquiry> findAllInquiriOrderByAnswer();
	
	//검색기능
	@Query("SELECT i FROM Inquiry i "
		     + "JOIN i.member m "
		     + "WHERE (i.inquiryTitle LIKE %:search% "
		     + "OR i.inquiryContent LIKE %:search% "
		     + "OR m.memberName LIKE %:search%) "
		     + "AND i.inquiryStatus = 1")
		List<Inquiry> findInquiryBySearch(@Param("search") String search);

}
