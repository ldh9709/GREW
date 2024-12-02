package com.itwill.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.alarm.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm, Long>{ 

}
