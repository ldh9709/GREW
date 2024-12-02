package com.itwill.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itwill.jpa.entity.bullentin_board.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
	/*질문 하나에 달린 답변 리스트*/
	List<Answer> findByInquiryInquiryNo(Long inquiryNo);
	
	/*카테고리별 답변 리스트(검색)*/
	/*추천순*/
	@Query("SELECT a FROM Answer a " +
		       "JOIN a.inquiry i " +
		       "JOIN i.category c " +
		       "LEFT JOIN a.votes v " +
		       "WHERE c.categoryNo = :categoryNo " +
		       "GROUP BY a.answerNo " +
		       "ORDER BY COUNT(v) DESC")
		List<Answer> findByInquiryCategoryCategoryNoOrderByVotesCountDesc(Long categoryNo);
	/*최신순*/
	
	/*최근 추천 많은 답변*/
}
