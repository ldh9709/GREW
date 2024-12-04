package com.itwill.jpa.repository.member_information;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.member_information.Follow;

@Repository
public interface FollowReporitory extends JpaRepository<Follow, Long>{

}
