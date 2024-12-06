package com.itwill.jpa.service.alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.entity.alarm.Alarm;
import com.itwill.jpa.repository.alarm.AlarmRepository;
@Service
public class AlarmServiceimpl implements AlarmService{
	@Autowired
	private AlarmRepository alarmRepository;
	//알림등록
	@Override
	public AlarmDto saveAlarm(AlarmDto alarmDto) {
        alarmDto.setIsRead(1);

        return AlarmDto.toDto(alarmRepository.save(Alarm.toEntity(alarmDto)));

	}
	//알림삭제
	@Override
	public void deleteAlarm(Long AlarmNo) {
		alarmRepository.deleteById(AlarmNo);
		
	}
	//유저의 알림 전체삭제
	@Override
	public void deleteAlarmByMemberNo(Long memberNo) {
		alarmRepository.deleteAlarmsByMemberId(memberNo);
	}
	//알림 읽음표시
	@Override
	public AlarmDto readAlarm(AlarmDto alarmDto) {
		Alarm alarm = alarmRepository.findById(alarmDto.getAlarmNo()).get();
		alarm.setIsRead(2);
		return AlarmDto.toDto(alarmRepository.save(alarm));
	}
	

}
