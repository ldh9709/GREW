package com.itwill.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.alarm.Alarm;

import jakarta.transaction.Transactional;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long>{ 
	@Modifying
    @Transactional
    @Query("DELETE FROM Alarm a WHERE a.member.id = :memberId")
    void deleteAlarmsByMemberId(@Param("memberId") Long memberId);
}
