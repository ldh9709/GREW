package com.itwill.jpa.service.alarm;

import java.nio.channels.AlreadyBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.dto.chatting_review.ReviewDto;
import com.itwill.jpa.dto.member_information.MentorBoardDto;
import com.itwill.jpa.dto.report.ReportDto;
import com.itwill.jpa.entity.alarm.Alarm;
import com.itwill.jpa.entity.bullentin_board.Answer;
import com.itwill.jpa.entity.bullentin_board.Inquiry;
import com.itwill.jpa.entity.chatting_review.ChatRoom;
import com.itwill.jpa.entity.chatting_review.Review;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.alarm.AlarmRepository;
import com.itwill.jpa.repository.bullentin_board.AnswerRepository;
import com.itwill.jpa.repository.bullentin_board.InquiryRepository;
import com.itwill.jpa.repository.chatting_review.ChatRoomRepository;
import com.itwill.jpa.repository.chatting_review.ReviewRepository;
import com.itwill.jpa.repository.member_information.FollowReporitory;
import com.itwill.jpa.repository.member_information.MemberRepository;
import com.itwill.jpa.repository.report.ReportRepository;

@Service
public class AlarmServiceimpl implements AlarmService {
	@Autowired
	private AlarmRepository alarmRepository;
	@Autowired
	private FollowReporitory followReporitory;
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private ReportRepository reportRepository;
	@Autowired
	private InquiryRepository inquiryRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private ChatRoomRepository chatRoomRepository;

	// 알림등록
	@Override
	public AlarmDto createAlarm(AlarmDto alarmDto) {
		return AlarmDto.toDto(alarmRepository.save(Alarm.toEntity(alarmDto)));

	}

	// 알림한개삭제
	@Override
	public void deleteAlarm(Long AlarmNo) {
		alarmRepository.deleteById(AlarmNo);

	}

	// 유저의 알림 전체삭제
	@Override
	public void deleteAlarmByMemberNo(Long memberNo) {
		alarmRepository.deleteByMember_MemberNo(memberNo);
	}

	// 알림 읽음표시
	@Override
	public AlarmDto isReadAlarm(Long alarmNo) {
		Alarm alarm = alarmRepository.findById(alarmNo).get();
		alarm.setIsRead(2);
		return AlarmDto.toDto(alarmRepository.save(alarm));
	}

	// 질문에 답변이 달렸을 때 질문자에게 알림 생성
	@Override
	public AlarmDto createAlarmByAnswerToInquiry(AnswerDto answerDto) {
		AlarmDto alarmDto = new AlarmDto();
		alarmDto.setReferenceNo(answerDto.getInquiryNo());
		Inquiry inquiry = inquiryRepository.findById(answerDto.getInquiryNo()).get();
		String inquiryTitle = inquiry.getInquiryTitle();
		if (inquiryTitle.length() > 10) {
			inquiryTitle = inquiryTitle.substring(0, 10) + "..."; // 길이를 초과하면 '...' 추가
		}
		alarmDto.setAlarmContent("회원님의 질문" + inquiryTitle + "에 " + answerDto.getMemberName() + "멘토 님의 답변이 달렸습니다");
		alarmDto.setAlarmType("answer");
		alarmDto.setReferenceType("question");
		alarmDto.setMemberNo(answerRepository.findByMemberNoByInquiryByAnswer(answerDto.getAnswerNo()));
		return AlarmDto.toDto(alarmRepository.save(Alarm.toEntity(alarmDto)));
	}

	// 멘토보드등록시 알림추가
	@Override
	public List<AlarmDto> createAlarmsByMentorBoard(MentorBoardDto mentorBoardDto) {
		List<AlarmDto> alarmDtos = new ArrayList<>();
		List<Long> menteeList = followReporitory.findMenteeByMentor(mentorBoardDto.getMemberNo());
		Member member = memberRepository.findById(mentorBoardDto.getMemberNo()).get();
		for (Long menteeMemberNo : menteeList) {
			AlarmDto alarmDto = AlarmDto.builder().alarmContent(member.getMemberName() + " 멘토가 게시물을 등록했습니다.")
					.alarmType("follower").referenceType("mentorBoard").referenceNo(mentorBoardDto.getMentorBoardNo())
					.memberNo(menteeMemberNo).build();
			alarmRepository.save(Alarm.toEntity(alarmDto));
			alarmDtos.add(alarmDto);
		}
		return alarmDtos;
	}

	// 리뷰달렸을때 멘토에게 알림
	@Override
	public AlarmDto createAlarmByReview(Long reviewNo) {
		AlarmDto alarmDto = new AlarmDto();
		Review review = reviewRepository.findById(reviewNo).get();
		ChatRoom chatRoom = chatRoomRepository.findById(review.getChatRoom().getChatRoomNo()).get();

		alarmDto.setReferenceNo(reviewNo);
		alarmDto.setAlarmContent(chatRoom.getMentee().getMemberName() + " 멘티님이 리뷰를 달았습니다.");
		alarmDto.setAlarmType("mentee");
		alarmDto.setReferenceType("review");
		alarmDto.setMemberNo(reviewRepository.findMentorNoByReviewNo(reviewNo));
		return AlarmDto.toDto(alarmRepository.save(Alarm.toEntity(alarmDto)));
	}

	// 신고 제재 시 신고자에게 알림
	@Override
	public AlarmDto createAlarmByReport(Long reportNo) {
		AlarmDto alarmDto = new AlarmDto();
		alarmDto.setAlarmContent("회원님의 신고가 정상적으로 제재 처리 되었습니다.");
		alarmDto.setAlarmType("report");
		alarmDto.setMemberNo(reportRepository.findByReportNo(reportNo).getMember().getMemberNo());
		return AlarmDto.toDto(alarmRepository.save(Alarm.toEntity(alarmDto)));
	}

	// 팔로우 시 멘토에게 팔로워 증가 알림
	@Override
	public AlarmDto createAlarmByFollowByMentor(Long mentorMemberNo) {
		AlarmDto alarmDto = new AlarmDto();
		alarmDto.setAlarmContent("멘티가 회원님을 팔로우하기 시작했습니다.");
		alarmDto.setAlarmType("follower");
		alarmDto.setMemberNo(mentorMemberNo);
		return AlarmDto.toDto(alarmRepository.save(Alarm.toEntity(alarmDto)));
	}

	// 추천시 답변 작성자에게 추천 증가 알림
	@Override
	public AlarmDto createAlarmByVoteByMentor(Long answerNo) {
		AlarmDto alarmDto = new AlarmDto();
		Answer answer = answerRepository.findById(answerNo).get();
		String inquiryTitle = answer.getInquiry().getInquiryTitle();
		String answerContent = answer.getAnswerContent();
		if (answerContent.length() > 10) {
			answerContent = answerContent.substring(0, 10) + "...";
		}
		if (inquiryTitle.length() > 10) {
			inquiryTitle = inquiryTitle.substring(0, 10) + "..."; // 길이를 초과하면 '...' 추가
		}
		alarmDto.setAlarmContent("질문'" + inquiryTitle + "'에 대한 회원님의 답변'" + answerContent + "'에 추천이 달렸습니다");
		alarmDto.setAlarmType("vote");
		alarmDto.setMemberNo(answer.getMember().getMemberNo());
		alarmDto.setReferenceNo(answer.getInquiry().getInquiryNo());
		alarmDto.setReferenceType("question");
		return AlarmDto.toDto(alarmRepository.save(Alarm.toEntity(alarmDto)));
	}
	//멘토 심사 완료시 신청자에게 알림
	@Override
	public AlarmDto createAlarmByEvaluationByMentor(Long memberNo, int status) {
		AlarmDto alarmDto = new AlarmDto();
		alarmDto.setAlarmType("Evaluation");
		alarmDto.setMemberNo(memberNo);
		if(status==3) {
		alarmDto.setAlarmContent("멘토 심사 성공 : 멘토가 되었습니다.");
		}else if(status ==4) {
		alarmDto.setAlarmContent("멘토 심사 실패 : 재심사를 받아주세요.");
		}
		return AlarmDto.toDto(alarmRepository.save(Alarm.toEntity(alarmDto)));
	}
	// 멤버한명의 알림 출력
	@Override
	public List<AlarmDto> findAlarmByMember(Long memberNo) {

		List<AlarmDto> alarmDtoList = new ArrayList<>();
		List<Alarm> alarmList = alarmRepository.findByMember_MemberNo(memberNo);
		for (Alarm alarm : alarmList) {
			alarmDtoList.add(AlarmDto.toDto(alarm));
		}
		return alarmDtoList;
	}

	// 멤버 한명 알림 전체 읽음 처리
	@Override
	public List<AlarmDto> setAlarmIsReadByMember(Long memberNo) {
		List<AlarmDto> alarmDtoList = new ArrayList<>();
		List<Alarm> alarmList = alarmRepository.findByMember_MemberNo(memberNo);
		for (Alarm alarm : alarmList) {
			alarm.setIsRead(2);
			alarmRepository.save(alarm);
			alarmDtoList.add(AlarmDto.toDto(alarm));
		}
		return alarmDtoList;
	}

	// 알림 클릭시 URl전송
	@Override
	public String alarmRedirectURL(AlarmDto alarmDto) {
		// 프론트엔드 제작 시 경로 수정 必
		switch (alarmDto.getReferenceType()) {
		case "question":
			return "/inquiry/" + alarmDto.getReferenceNo();
		case "mentorBoard":
			return "/mentor-board/detail/" + alarmDto.getReferenceNo();
		case "review":
			return "/review/" + alarmDto.getReferenceNo();
		default:
			throw new IllegalArgumentException("Unknown reference type");
		}
	}

	// 알림 안읽음 갯수
	@Override
	public Long alarmIsReadCount(Long memberNo) {
		Long count = alarmRepository.countByMember_MemberNoAndIsRead(memberNo, 1);
		return count;
	}
	//알림 넘버로 알림 뽑기
	@Override
	public AlarmDto findAlarm(Long alarmNo) {
		return AlarmDto.toDto(alarmRepository.findById(alarmNo).get());
	}
	
	

}
