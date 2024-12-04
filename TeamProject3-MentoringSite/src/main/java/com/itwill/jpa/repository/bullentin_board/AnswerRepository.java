package com.itwill.jpa.repository.bullentin_board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.bullentin_board.Answer;
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{
	/*질문 하나에 달린 답변 리스트*/
	/*추천순*/
	@Query("""
            SELECT a
            FROM Answer a
            JOIN a.inquiry i
            LEFT JOIN Vote v ON v.answer.answerNo = a.answerNo
            WHERE i.inquiryNo = :inquiryNo
            AND a.answerStatus = 1
            GROUP BY a.answerNo, a.answerContent, a.answerDate, a.answerStatus, a.answerAccept
            ORDER BY a.answerAccept DESC, 
                     (COUNT(CASE WHEN v.voteType = 1 THEN 1 END) - 
                      COUNT(CASE WHEN v.voteType = 2 THEN 1 END)) DESC
    """)
		List<Answer> findByInquiryAnswerOrderByVotes(@Param("inquiryNo") Long inquiryNo);
	
	
	/*최신순*/
	@Query("SELECT a FROM Answer a " +
		       "JOIN a.inquiry i " +
		       "WHERE i.inquiryNo = :inquiryNo " +
		       "AND a.answerStatus = 1 " +
		       "ORDER BY a.answerAccept DESC,"+
		       "a.answerDate DESC")
		List<Answer> findByInquiryAnswerOrderByDate(@Param("inquiryNo") Long inquiryNo);

	
	/*카테고리별 답변 리스트(검색)*/
	/*추천순*/
	@Query("SELECT a FROM Answer a " +
		       "JOIN a.inquiry i " +
		       "JOIN i.category c " +
		       "LEFT JOIN a.votes v " +
		       "WHERE c.categoryNo = :categoryNo " +
		       "AND a.answerStatus = 1 " + 
		       "GROUP BY a.answerNo " +
		       "ORDER BY COUNT(v) DESC")
		List<Answer> findByCategoryAnswerOrderByVotes(@Param("categoryNo") Long categoryNo);

	/*최신순*/
	@Query("SELECT a FROM Answer a " +
		       "JOIN a.inquiry i " +
		       "JOIN i.category c " +
		       "WHERE c.categoryNo = :categoryNo " +
		       "AND a.answerStatus =1 "+
		       "ORDER BY a.answerDate DESC")
		List<Answer> findByCategoryAnswerOrderByDate(@Param("categoryNo") Long categoryNo);

	/*최근 3일동안 추천 많은 답변*/
	@Query(value = "SELECT a FROM Answer a " +
               "JOIN a.inquiry i " +
               "INNER JOIN a.votes v " +
               "WHERE v.voteDate >= SYSDATE - 3 " + 
               "AND a.answerStatus = 1 " +
               "GROUP BY a.answerNo " +
               "ORDER BY " +
               "COUNT(CASE WHEN v.voteType = 1 THEN 1 END) "
               + "- COUNT(CASE WHEN v.voteType = 2 THEN 1 END) DESC",
               nativeQuery = true)  //jpql엔 sysdate 사용불가하기 때문에 nativeQuery로 오라클의 sql사용
		List<Answer> findByAnswerOrderByVoteDate();

}
