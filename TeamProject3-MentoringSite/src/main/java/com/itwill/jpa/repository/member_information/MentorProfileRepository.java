package com.itwill.jpa.repository.member_information;

import java.util.List;

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




    /**
     * 멘토 프로필 검색: 이름, 소개글, 경력에서 키워드를 검색
     */
    @Query("SELECT mp FROM MentorProfile mp " +
           "WHERE mp.mentorStatus = 3 AND " + // 심사 완료 상태만 검색
           "(mp.member.memberName LIKE %:keyword% " +
           "OR mp.mentorIntroduce LIKE %:keyword% " +
           "OR mp.mentorCareer LIKE %:keyword%)")
    List<MentorProfile> searchMentorProfiles(@Param("keyword") String keyword);

    /**
     * 카테고리 번호로 멘토 프로필 조회
     */
    @Query("SELECT mp FROM MentorProfile mp WHERE mp.category.categoryNo = :categoryNo")
    List<MentorProfile> findByCategoryNo(@Param("categoryNo") Long categoryNo);


    /**
     * mentorStatus를 특정 상태로 업데이트 심사전 심사중 심사완료 탈퇴 
     */
    @Modifying
    @Query("UPDATE MentorProfile mp SET mp.mentorStatus = :status WHERE mp.member.memberNo = :memberNo")
    void updateMentorStatus(@Param("memberNo") Long memberNo, @Param("status") int status);

    /**
     * 특정 상태의 모든 멘토 프로필 조회
     */
    @Query("SELECT mp FROM MentorProfile mp WHERE mp.mentorStatus = :status")
    List<MentorProfile> findByMentorStatus(@Param("status") int status);
   
    //평점 
    @Query("SELECT AVG(r.reviewScore) FROM Review r WHERE r.chatRoom.mentor.memberNo = :mentorNo")
    Double findAverageScoreByMentor(@Param("mentorNo") Long mentorNo);
    
    /**
     * 멘토의 멘토 평점(mentor_rating)을 업데이트합니다.
     * @param memberNo 멘토의 멤버 번호
     */
    @Modifying
    @Query(
        "UPDATE MentorProfile mp " +
        "SET mp.mentorRating = COALESCE( " +
        "    (SELECT ROUND(AVG(r.reviewScore), 1) " +
        "     FROM Review r " +
        "     JOIN r.chatRoom cr " +
        "     WHERE cr.mentor.memberNo = mp.member.memberNo), 0.0) " +
        "WHERE mp.member.memberNo = :memberNo"
    )
    void updateMentorRatingByMemberNo(@Param("memberNo") Long memberNo);
    /**
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 멤버 번호로 멘토 프로필을 조회합니다.
     * @param memberNo 멤버 번호
     * @return MentorProfile 엔티티
     */
    @Query("SELECT mp FROM MentorProfile mp WHERE mp.member.memberNo = :memberNo")
    MentorProfile findByMemberNo(@Param("memberNo") Long memberNo);

/**
 * 멘토 프로필을 멤버 번호로 조회
 *
 * @param memberNo 멤버 번호
 * @return MentorProfile 엔티티
 */
MentorProfile findByMember_MemberNo(Long memberNo);

/**
 * 특정 멤버의 멘토 프로필을 조회
 */
MentorProfile findByMember(Member member);

}
//mentorStatus= (1=디폴트(심사전), 2=심사중 ,3=심사완료(멘토상태) 4=멘토탈퇴)
//MemberNo로 멘토 프로필 insert  상태는 status로 멘토 프로필 전환 상태로 바꾸는 메서드

//멘토프로필 탈퇴 mentorStatus=4로 업데이트 하기  mentorStatus=4는 탈퇴상태



//멘토프로필 상세보기 조회 멘토 상태이기 때문에 mentorStatus넘버는=3 

//검색 (이름 소개글 경력)


// 특정 카테고리와 관련된 멘토 프로필을 조회
//List<MentorProfile> findByCategory(Category category);






//멘토 평점 조회 - mentor_rating를 조회 ()