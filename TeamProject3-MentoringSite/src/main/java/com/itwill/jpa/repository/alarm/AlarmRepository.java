package com.itwill.jpa.repository.alarm;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.alarm.Alarm;

import jakarta.transaction.Transactional;
@Transactional
@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long>{ 
	
	//유저의 전체 알림 지우기
	void deleteByMember_MemberNo(Long memberNo);
	//유저 알림 목록 찾기
	List<Alarm> findByMember_MemberNo(Long memberNo);
}
