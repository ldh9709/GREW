package com.itwill.jpa.repository.bullentin_board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.bullentin_board.Answer;
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{
	
	
	// 해당 질문에 채택된 답변을 조회
	@Query("SELECT a FROM Answer a WHERE a.inquiry.inquiryNo = :inquiryNo AND a.answerAccept = 2")
	Answer findAcceptedAnswerByInquiry(@Param("inquiryNo") Long inquiryNo);
	
	/*질문 하나에 달린 답변 리스트*/
	/*추천순*/
	@Query("""
            SELECT a
            FROM Answer a
            JOIN a.inquiry i
            LEFT JOIN a.votes v 
            WHERE i.inquiryNo = :inquiryNo
            AND a.answerStatus = 1
            GROUP BY a.answerNo, a.answerContent, a.answerDate, a.answerStatus, a.answerAccept, a.member, a.inquiry
            ORDER BY a.answerAccept DESC, 
                     (COUNT(CASE WHEN v.voteType = 1 THEN 1 END) - 
                      COUNT(CASE WHEN v.voteType = 2 THEN 1 END)) DESC
    """)
		Page<Answer> findByInquiryAnswerOrderByVotes(@Param("inquiryNo") Long inquiryNo,Pageable pageable);
	
	
	/*최신순*/
	@Query("SELECT a FROM Answer a " +
		       "JOIN a.inquiry i " +
		       "WHERE i.inquiryNo = :inquiryNo " +
		       "AND a.answerStatus = 1 " +
		       "ORDER BY a.answerAccept DESC,"+
		       "a.answerDate DESC")
		Page<Answer> findByInquiryAnswerOrderByDate(@Param("inquiryNo") Long inquiryNo,Pageable pageable);

	
	/*카테고리별 답변 리스트(검색)*/
	/*추천순*/
	@Query("SELECT a FROM Answer a " +
		       "JOIN a.inquiry i " +
		       "JOIN i.category c " +
		       "LEFT JOIN a.votes v " +
		       "WHERE c.categoryNo = :categoryNo " +
		       "AND a.answerStatus = 1 " + 
		       "GROUP BY a.answerNo, a.answerContent, a.answerDate, a.answerStatus, a.answerAccept, a.member, a.inquiry " +
		       "ORDER BY a.answerAccept DESC, " +
		       "(COUNT(CASE WHEN v.voteType = 1 THEN 1 END) - " +
		       "COUNT(CASE WHEN v.voteType = 2 THEN 1 END)) DESC")
		Page<Answer> findByCategoryAnswerOrderByVotes(@Param("categoryNo") Long categoryNo,Pageable pageable);

	/*최신순*/
	@Query("SELECT a FROM Answer a " +
		       "JOIN a.inquiry i " +
		       "JOIN i.category c " +
		       "WHERE c.categoryNo = :categoryNo " +
		       "AND a.answerStatus =1 "+
		       "ORDER BY a.answerDate DESC")
		Page<Answer> findByCategoryAnswerOrderByDate(@Param("categoryNo") Long categoryNo,Pageable pageable);

	/*최근 3일동안 추천 많은 답변*/
	   @Query(value = "SELECT a.* " +
	            "FROM answer a " +
	            "LEFT JOIN vote v " +
	            "ON a.answer_no = v.answer_no " +
	            "WHERE (v.vote_date >= SYSDATE - INTERVAL '3' DAY OR v.vote_date IS NULL) " +
	            "AND a.answer_status = 1 " +
	            "GROUP BY a.answer_no, a.answer_content, a.answer_date, a.answer_status, a.answer_accept, a.inquiry_no, a.member_no " +
	            "ORDER BY COUNT(CASE WHEN v.vote_type = 1 THEN 1 END) - " +
	            "COUNT(CASE WHEN v.vote_type = 2 THEN 1 END) DESC," +
	            "a.answer_no DESC", 
            nativeQuery = true)//jpql엔 sysdate 사용불가하기 때문에 nativeQuery로 오라클의 sql사용
		List<Answer> findByAnswerOrderByVoteDate();

	@Query("SELECT i.member.memberNo "
		       + "FROM Answer a "
		       + "JOIN a.inquiry i "
		       + "WHERE a.answerNo = :answerNo")
		Long findByMemberNoByInquiryByAnswer(@Param("answerNo")Long answerNo);

	//내가 작성한 답변내역
	@Query("SELECT a FROM Answer a WHERE a.member.memberNo = :memberNo AND a.answerStatus = 1 " +
		       "ORDER BY a.answerDate DESC")
	Page<Answer> findByMemberMemberNoOrderByAnswerDateDesc(@Param("memberNo") Long memberNo, Pageable pageable);

	// 답변 내용(answer_content) 또는 질문 제목(inquiry_title)에서 검색어를 포함하는 답변 찾기

    @Query("SELECT a FROM Answer a " +
           "JOIN a.inquiry i " +
           "JOIN i.category c " +
           "LEFT JOIN a.votes v " +
           "WHERE (a.answerContent LIKE %:search% OR i.inquiryTitle LIKE %:search%) " +
           "AND a.answerStatus = 1 " +
           "GROUP BY a.answerNo, a.answerContent, a.answerDate, a.answerStatus, a.answerAccept, a.member, a.inquiry " +
           "ORDER BY a.answerAccept DESC, " +
           "(COUNT(CASE WHEN v.voteType = 1 THEN 1 END) - " +
           "COUNT(CASE WHEN v.voteType = 2 THEN 1 END)) DESC")
    Page<Answer> searchAnswersByKeyword(@Param("search") String search, Pageable pageable);
    
    //질문의 답변 수
    Long countByInquiry_InquiryNo(Long inquiryNo);



}