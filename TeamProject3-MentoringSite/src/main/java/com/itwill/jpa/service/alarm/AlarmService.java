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
	AlarmDto isReadAlarm(AlarmDto alarmDto);
	//알림한개삭제
	void deleteAlarm(Long AlarmNo);
	//사용자의 모든 알림 삭제
	void deleteAlarmByMemberNo(Long memberNo);
	//질문에 답변이 달렸을 때 질문자에게 알림 생성
	AlarmDto createAlarmByAnswerToInquiry(AnswerDto answerDto);
	//멘토보드 알림 추가
	List<AlarmDto> createAlarmsByMentorBoard(MentorBoardDto mentorBoardDto);
	//리뷰달렸을때 멘토에게 알림
	AlarmDto createAlarmByReview(ReviewDto reviewDto);
	//신고 제재 시 신고자에게 알림
	AlarmDto createAlarmByReport(Long reportNo);
	//팔로우시 멘토에게 팔로워 증가 알림
	AlarmDto createAlarmByFollowByMentor(Long MentorMemberNo);
	//추천시 답글 작성자에게 추천 증가 알림
	AlarmDto createAlarmByVoteByMentor(Long answerNo);
	//알림 클릭시 URl전송
	String alarmRedirectURL(AlarmDto alarmDto);
}
