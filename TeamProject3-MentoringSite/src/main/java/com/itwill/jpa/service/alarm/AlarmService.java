package com.itwill.jpa.service.alarm;

import java.util.List;

import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.dto.member_information.FollowRequestDto;
import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.dto.report.ReportDto;

public interface AlarmService{
	//알림추가
	AlarmDto createAlarm(AlarmDto alarmDto);
	//알림읽음표시
	AlarmDto isReadAlarm(Long alarmNo);
	//알림한개삭제
	void deleteAlarm(Long AlarmNo);
	//사용자의 모든 알림 삭제
	void deleteAlarmByMemberNo(Long memberNo);
	//질문에 답변이 달렸을 때 질문자에게 알림 생성
	AlarmDto createAlarmByAnswerToInquiry(AnswerDto answerDto);
	//멘토보드 알림 추가
	List<AlarmDto> createAlarmsByMentorBoard(MentorBoardDto mentorBoardDto);
	//리뷰달렸을때 멘토에게 알림
	AlarmDto createAlarmByReview(Long reviewNo);
	//신고 제재 시 신고자에게 알림
	AlarmDto createAlarmByReport(Long reportNo);
	//팔로우시 멘토에게 팔로워 증가 알림
	AlarmDto createAlarmByFollowByMentor(Long mentorMemberNo);
	//추천시 답글 작성자에게 추천 증가 알림
	AlarmDto createAlarmByVoteByMentor(Long answerNo);
	//멘토 심사 완료시 신청자에게 알림
	AlarmDto createAlarmByEvaluationByMentor(Long memberNo,int status);
	
	//멤버한명의 알림 리스트
	List<AlarmDto> findAlarmByMember(Long memberNo);
	
	//알림 클릭시 URl전송
	String alarmRedirectURL(AlarmDto alarmDto);
	//안읽음 알림 갯수
	Long alarmIsReadCount(Long memberNo);
	//알림 전체 읽음 처리
	List<AlarmDto> setAlarmIsReadByMember(Long memberNo); 
	//알림 넘버로 알림 뽑기
	AlarmDto findAlarm(Long alarmNo);
}
