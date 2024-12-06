package com.itwill.jpa.entity.alarm;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.entity.member_information.Member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="alarm")
public class Alarm {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alarm_no_SEQ")	
	@SequenceGenerator(name="alarm_no_SEQ",allocationSize = 1, initialValue = 1)
	@Column(name="alarm_no")
	private Long alarmNo;
	/*
	 * example : 회원님의 질문에 답변 #12이 달렸습니다
	 * type : 질문
	 * referenceType : 답변
	 * referenceNo : 12
	 */
	@Column(name="alarm_type")
	private String alarmType;
	@Column(name="alarm_content")
	private String alarmContent;
	@Column(name="reference_no")
	private Long referenceNo;
	@Column(name="reference_type")
	private String referenceType;
	@Column(name="is_read")
	private int isRead;
	@Column(name="alarm_date")
	private LocalDateTime alarmDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_no")
	private Member member;
	@PrePersist
    public void setDefaultValues() {
    	if(this.alarmDate==null) this.alarmDate = LocalDateTime.now();
    	if(this.isRead==0) this.isRead = 1;
    }
	public static Alarm toEntity(AlarmDto alarmDto) {
		return Alarm.builder()
				.alarmNo(alarmDto.getAlarmNo())
				.alarmContent(alarmDto.getAlarmContent())
				.alarmType(alarmDto.getAlarmType())
				.referenceNo(alarmDto.getReferenceNo())
				.referenceType(alarmDto.getReferenceType())
				.isRead(alarmDto.getIsRead())
				.alarmDate(alarmDto.getAlarmDate())	
				.member(Member.builder().memberNo(alarmDto.getMemberNo()).build())
				.build();
		
	}
}
