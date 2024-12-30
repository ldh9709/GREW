package com.itwill.jpa.repository.member_information;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.dto.member_information.CareerDto;
import com.itwill.jpa.entity.member_information.Career;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long>{
	List<Career> findByMentorProfile_MentorProfileNo(Long mentorProfileNo);
}
