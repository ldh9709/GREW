package com.itwill.jpa.service.alarm;

import com.itwill.jpa.dto.alarm.AlarmDto;

public interface AlarmService{
	//알림추가
	AlarmDto saveAlarm(AlarmDto alarmDto);
	//알림읽음표시
	AlarmDto readAlarm(AlarmDto alarmDto);
	//알림한개삭제
	void deleteAlarm(Long AlarmNo);
	//사용자의 모든 알림 삭제
	void deleteAlarmByMemberId(Long memberId);
}
