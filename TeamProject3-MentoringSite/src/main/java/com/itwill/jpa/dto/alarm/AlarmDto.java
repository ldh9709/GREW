package com.itwill.jpa.dto.alarm;

import java.time.LocalDate;

import com.itwill.jpa.dto.user_information.MemberDto;
import com.itwill.jpa.entity.alarm.Alarm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlarmDto {
	private Long alarmNo;
	private String alarmType;
	private String alarmContent;
	private Long referenceNo;
	private String referenceType;
	private int isRead;
	private LocalDate alarmDate;
	
	private MemberDto memberNo;
	
	public static AlarmDto toDto(Alarm alarmEntity) {
		return AlarmDto.builder()
				.alarmNo(alarmEntity.getAlarmNo())
				.alarmType(alarmEntity.getAlarmType())
				.alarmContent(alarmEntity.getAlarmContent())
				.referenceNo(alarmEntity.getReferenceNo())
				.referenceType(alarmEntity.getReferenceType())
				.isRead(alarmEntity.getIsRead())
				.alarmDate(alarmEntity.getAlarmDate())
				.memberNo(MemberDto.toDto(alarmEntity.getMember()))
				.build();
	}
}
