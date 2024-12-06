package com.itwill.jpa.service.alarm;

import java.util.List;

import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.dto.member_information.MentorBoardDto;

public interface AlarmService{
	//알림추가
	AlarmDto saveAlarm(AlarmDto alarmDto);
	//알림읽음표시
	AlarmDto readAlarm(AlarmDto alarmDto);
	//알림한개삭제
	void deleteAlarm(Long AlarmNo);
	//사용자의 모든 알림 삭제
	void deleteAlarmByMemberNo(Long memberNo);
	//질문에 답변이 달렸을 때 질문자에게 알림 생성
	AlarmDto saveAlarmByAnswerToInquiry(AnswerDto answerDto);
	//멘토보드 알림 추가
	List<AlarmDto> saveAlarmsByMentorBoard(MentorBoardDto mentorBoardDto);
}
