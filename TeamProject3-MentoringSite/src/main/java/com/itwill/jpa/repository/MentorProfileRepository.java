package com.itwill.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.user_information.Category;
import com.itwill.jpa.entity.user_information.Member;
import com.itwill.jpa.entity.user_information.MentorProfile;

@Repository
public interface MentorProfileRepository extends JpaRepository<MentorProfile, Long> {

    // 특정 사용자와 관련된 멘토 프로필을 조회
    //MentorProfile findByMember(Member member);

    // 특정 카테고리와 관련된 멘토 프로필을 조회
    //List<MentorProfile> findByCategory(Category category);

    // 특정 사용자와 카테고리를 기반으로 멘토 프로필 조회
    //MentorProfile findByMemberAndCategory(Member member, Category category);
}

