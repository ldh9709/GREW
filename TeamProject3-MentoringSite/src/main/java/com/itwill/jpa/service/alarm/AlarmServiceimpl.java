package com.itwill.jpa.service.alarm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.entity.alarm.Alarm;
import com.itwill.jpa.repository.alarm.AlarmRepository;
import com.itwill.jpa.repository.bullentin_board.AnswerRepository;
import com.itwill.jpa.repository.member_information.FollowReporitory;
@Service
public class AlarmServiceimpl implements AlarmService{
	@Autowired
	private AlarmRepository alarmRepository;
	@Autowired
	private FollowReporitory followReporitory;
	@Autowired
	private AnswerRepository answerRepository;
	//알림등록
	@Override
	public AlarmDto saveAlarm(AlarmDto alarmDto) {
        return AlarmDto.toDto(alarmRepository.save(Alarm.toEntity(alarmDto)));

	}
	//알림한개삭제
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
	
	@Override
	public AlarmDto saveAlarmByAnswerToInquiry(AnswerDto answerDto) {
		AlarmDto alarmDto = new AlarmDto();
		alarmDto.setReferenceNo(answerDto.getAnswerNo());
		alarmDto.setAlarmContent("회원님의 질문에 답변이 달렸습니다");
		alarmDto.setAlarmType("질문");
		alarmDto.setReferenceType("답변");
		alarmDto.setMemberNo(answerRepository.findByMemberNoByInquiryByAnswer(answerDto.getAnswerNo()));
		return AlarmDto.toDto(alarmRepository.save(Alarm.toEntity(alarmDto)));
	}
	//멘토보드등록시 알림추가
	@Override
	public List<AlarmDto> saveAlarmsByMentorBoard(MentorBoardDto mentorBoardDto) {
		List<AlarmDto> alarmDtos = new ArrayList<>();
	    List<Long> menteeList = followReporitory.findMenteeByMentor(mentorBoardDto.getMemberNo());
	    for (Long menteeMemberNo : menteeList) {
	        AlarmDto alarmDto = AlarmDto.builder()
	            .alarmContent("팔로잉하는 멘토가 게시물을 등록했습니다.")
	            .alarmType("follower")
	            .referenceType("mentorBoard")
	            .referenceNo(mentorBoardDto.getMentorBoardNo())
	            .memberNo(menteeMemberNo)
	            .build();
	        alarmRepository.save(Alarm.toEntity(alarmDto));
	        alarmDtos.add(alarmDto);
	    }
	    return alarmDtos;
	}
	

}
