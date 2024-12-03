package com.itwill.jpa.repository.member_information;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Interest;
import com.itwill.jpa.entity.member_information.Member;

import java.util.List;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {

    // 특정 사용자와 관련된 관심사를 조회
    //List<Interest> findByMember(Member member);

    // 특정 카테고리와 관련된 관심사를 조회
    //List<Interest> findByCategory(Category category);

    // 특정 사용자와 카테고리를 기반으로 관심사 조회
    //List<Interest> findByMemberAndCategory(Member member, Category category);
}
