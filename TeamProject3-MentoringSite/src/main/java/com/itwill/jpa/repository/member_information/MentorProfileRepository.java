package com.itwill.jpa.repository.member_information;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.member_information.MentorProfile;

@Repository
public interface MentorProfileRepository extends JpaRepository<MentorProfile, Long> {



	
	 // 멤버 번호로 멘토 프로필을 조회합니다.
	@Query("SELECT mp FROM MentorProfile mp WHERE mp.member.memberNo = :memberNo")
	MentorProfile findByMemberNo(@Param("memberNo") Long memberNo);
	
	//멘토 프로필을 멤버 번호로 조회
	MentorProfile findByMember_MemberNo(Long memberNo);
	
	 //특정 멤버의 멘토 프로필을 조회
	MentorProfile findByMember(Member member);

    //mentorStatus를 특정 상태로 업데이트 심사전 심사중 심사완료 탈퇴 
    @Modifying
    @Query("UPDATE MentorProfile mp SET mp.mentorStatus = :status WHERE mp.member.memberNo = :memberNo")
    void updateMentorStatus(@Param("memberNo") Long memberNo, @Param("status") int status);

    //평점 
    @Query("SELECT AVG(r.reviewScore) FROM Review r WHERE r.chatRoom.mentor.memberNo = :mentorNo")
    Double findAverageScoreByMentor(@Param("mentorNo") Long mentorNo);
    
     // 멘토의 멘토 평점(mentor_rating)을 업데이트합니다.
    @Modifying
    @Query(
        "UPDATE MentorProfile mp " +
        "SET mp.mentorRating = COALESCE( " +
        "    (SELECT ROUND(AVG(r.reviewScore), 1) " +
        "     FROM Review r " +
        "     JOIN r.chatRoom cr " +
        "     WHERE cr.mentor.memberNo = mp.member.memberNo), 0.0) " +
        "WHERE mp.member.memberNo = :memberNo" )
    void updateMentorRatingByMemberNo(@Param("memberNo") Long memberNo);
    
    @Query("SELECT mp FROM MentorProfile mp " +
            "JOIN FETCH mp.member " +
            "JOIN FETCH mp.category " +
            "WHERE mp.mentorStatus = 3 AND " +
            "(LOWER(mp.mentorIntroduce) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(mp.mentorCareer) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(mp.member.memberName) LIKE LOWER(CONCAT('%', :keyword, '%'))) ")
     Page<MentorProfile> searchMentorProfiles(@Param("keyword") String keyword, Pageable pageable);

     @Query("SELECT mp FROM MentorProfile mp WHERE mp.category.categoryNo = :categoryNo")
     Page<MentorProfile> findByCategoryNo(@Param("categoryNo") Long categoryNo, Pageable pageable);

     @Query("SELECT mp FROM MentorProfile mp WHERE mp.mentorStatus = :status")
     Page<MentorProfile> findByMentorStatus(@Param("status") int status, Pageable pageable);
    
}










//    /**
//     * 특정 상태의 모든 멘토 프로필 조회
//     */
//    @Query("SELECT mp FROM MentorProfile mp WHERE mp.mentorStatus = :status")
//    List<MentorProfile> findByMentorStatus(@Param("status") int status);
//   
//    /**
//     * 카테고리 번호로 멘토 프로필 조회
//     */
//    @Query("SELECT mp FROM MentorProfile mp WHERE mp.category.categoryNo = :categoryNo")
//    List<MentorProfile> findByCategoryNo(@Param("categoryNo") Long categoryNo);

//멘토 평점 조회 - mentor_rating를 조회 ()
//    /**
//     * 멘토 프로필 검색: 이름, 소개글, 경력에서 키워드를 검색
//     */
//	@Query("SELECT mp FROM MentorProfile mp " +
//		       "JOIN FETCH mp.member " + 
//		       "JOIN FETCH mp.category " + 
//		       "WHERE mp.mentorStatus = 3 AND " + 
//		       "(LOWER(mp.mentorIntroduce) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
//		       "OR LOWER(mp.mentorCareer) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
//		       "OR LOWER(mp.member.memberName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
//		List<MentorProfile> searchMentorProfiles(@Param("keyword") String keyword);