package com.itwill.jpa.controller.alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.entity.alarm.Alarm;
import com.itwill.jpa.repository.alarm.AlarmRepository;
import com.itwill.jpa.service.alarm.AlarmService;

@RestController
public class AlarmRestController {

    @Autowired
    private AlarmService alarmService;
    @Autowired
    private AlarmRepository alarmRepository;
    //알림으로 페이지이동
    @GetMapping("/alarms/{alarmId}/redirect")
    public ResponseEntity<String> redirectToPage(@PathVariable(name = "alarmId") Long alarmId) {
        AlarmDto alarmDto = AlarmDto.toDto(alarmRepository.findById(alarmId).get());

        String redirectUrl = alarmService.alarmRedirectURL(alarmDto);

        return ResponseEntity.ok(redirectUrl);
    }
}
