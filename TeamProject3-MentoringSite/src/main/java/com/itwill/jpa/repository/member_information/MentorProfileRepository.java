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
            "(LOWER(mp.mentorIntroduce) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(mp.mentorCareer) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(mp.member.memberName) LIKE LOWER(CONCAT('%', :search, '%'))) ")
     Page<MentorProfile> searchMentorProfiles(@Param("search") String search, Pageable pageable);

     @Query("SELECT mp FROM MentorProfile mp WHERE mp.category.categoryNo = :categoryNo")
     Page<MentorProfile> findByCategoryNo(@Param("categoryNo") Long categoryNo, Pageable pageable);

     @Query("SELECT mp FROM MentorProfile mp WHERE mp.mentorStatus = :status")
     Page<MentorProfile> findByMentorStatus(@Param("status") int status, Pageable pageable);
    
     
     /**
      * 멘토 프로필의 특정 필드들을 업데이트하는 메서드
      */
     @Modifying
     @Query("UPDATE MentorProfile mp SET " +
            "mp.mentorCareer = :mentorCareer, " +
            "mp.mentorIntroduce = :mentorIntroduce, " +
            "mp.mentorImage = :mentorImage, " +
            "mp.category.categoryNo = :categoryNo " +
            "WHERE mp.mentorProfileNo = :mentorProfileNo")
     int updateMentorProfile(
             Long mentorProfileNo, 
             String mentorCareer, 
             String mentorIntroduce, 
             String mentorImage, 
             Long categoryNo
     );
     
     //멘토 프로필 전체 조회
     Page<MentorProfile> findAll(Pageable pageable);
     
     // 멘토 프로필의 멘토링 횟수 조회
     @Query("SELECT mp.mentorMentoringCount FROM MentorProfile mp WHERE mp.mentorProfileNo = :mentorProfileNo")
     Integer findMentorMentoringCountByProfileNo(@Param("mentorProfileNo") Long mentorProfileNo);
     
     // 멘토 프로필의 팔로우 수 조회
     @Query("SELECT mp.mentorFollowCount FROM MentorProfile mp WHERE mp.mentorProfileNo = :mentorProfileNo")
     Integer findMentorFollowCountByProfileNo(@Param("mentorProfileNo") Long mentorProfileNo);

     // 멘토 프로필의 활동 수 조회
     @Query("SELECT mp.mentorActivityCount FROM MentorProfile mp WHERE mp.mentorProfileNo = :mentorProfileNo")
     Integer findMentorActivityCountByProfileNo(@Param("mentorProfileNo") Long mentorProfileNo);
     
     @Query("SELECT mp FROM MentorProfile mp JOIN FETCH mp.member JOIN FETCH mp.category WHERE mp.mentorProfileNo = :mentorProfileNo")
     MentorProfile findDetailedProfileByNo(@Param("mentorProfileNo") Long mentorProfileNo);
     
     //조회 리스트
     Page<MentorProfile> findByOrderByMentorFollowCountDesc(Pageable pageable);
     Page<MentorProfile> findByOrderByMentorMentoringCountDesc(Pageable pageable);
     Page<MentorProfile> findByOrderByMentorActivityCountDesc(Pageable pageable);
}








