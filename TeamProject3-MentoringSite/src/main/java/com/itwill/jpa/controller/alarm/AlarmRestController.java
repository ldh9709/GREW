package com.itwill.jpa.controller.alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.response.Response;
import com.itwill.jpa.service.alarm.AlarmService;

import io.swagger.v3.oas.annotations.Operation;
@RestController
@RequestMapping("/alarm")
public class AlarmRestController {
	@Autowired
	private AlarmService alarmService;
	
	//알림 하나 삭제하기
	@Operation(summary = "알림 한개 삭제")
	@DeleteMapping("delete/{alarmNo}")
	public void deleteAlarm(@PathVariable(name = "alarmNo") Long alarmNo){
		alarmService.deleteAlarm(alarmNo);
		
	}
}
