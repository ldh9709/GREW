package com.itwill.jpa.entity.user_information;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.itwill.jpa.dto.user_information.MemberDto;
import com.itwill.jpa.entity.alarm.Alarm;
import com.itwill.jpa.entity.bullentin_board.Answer;
import com.itwill.jpa.entity.bullentin_board.Inquiry;
import com.itwill.jpa.entity.bullentin_board.Vote;
import com.itwill.jpa.entity.chatting_review.ChatMessage;
import com.itwill.jpa.entity.chatting_review.ChatRoomStatus;
import com.itwill.jpa.entity.chatting_review.MentoringRequest;
import com.itwill.jpa.entity.report.Report;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Member")
public class Member {
	

	@Id//PK설정
	@SequenceGenerator(name = "member_no_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_no_seq")
	@Column(name = "member_no")
	private Long memberNo;//멤버 번호 PK
	
	@Column(name = "member_id")
	private String memberId;//Id
	
	@Column(name = "member_password")
	private String memberPassword;//비밀번호
	
	@Column(name = "member_email")
	private String memberEmail;//이메일
	
	@Column(name = "member_name")
	private String memberName;//이름
	
	@Column(name = "member_role")
	private String memberRole;//역할
	
	@Column(name = "member_points")
	private Integer memberPoints;//멤버 연필 포인트
	
	@Column(name = "member_status")
	private Integer memberStatus;//멤버의 상태(활동, 정지 등)
	
	@Column(name = "member_join_date")
	private LocalDateTime memberJoinDate;//멤버 가입 날짜
	
	@Column(name = "member_report_count")
	private Integer memberReportCount;//신고 당한 횟수
	
	 /*
     * DTO -> Entitiy
     */
	public static Member toEntity(MemberDto memberDto) {
	    return Member.builder()
	            .memberId(memberDto.getMemberId())
	            .memberPassword(memberDto.getMemberPassword())
	            .memberEmail(memberDto.getMemberEmail())
	            .memberName(memberDto.getMemberName())
	            .build();
	}
	
	/* 초기값 설정 */
	@PrePersist
	public void setDefaultValues() {
		if (this.memberRole == null) this.memberRole = "MENTI";
		if (this.memberPoints == null) this.memberPoints = 0;
		if (this.memberStatus == null) this.memberStatus = 1;
		if (this.memberJoinDate == null) this.memberJoinDate = LocalDateTime.now();
		if (this.memberReportCount == null) this.memberReportCount = 0;
		
	}
	
	/*
	 * fetch = FetchType.LAZY
	 * cascade = CascadeType.ALL
	 * 사용 여부 체크
	 */
	
	/* 한 명의 유저가 멘토 프로필은 한개 보유 가능 */
	@OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
	private MentorProfile mentorProfile;

	/* 한 명의 유저가 관심사 여러개 보유 가능 */
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Interest> interests = new ArrayList<>();
	
	/* 한 명의 유저가 멘트 게시글은 여러개 보유 가능 */
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<MentorBoard> mentorBoards = new ArrayList<>();
	
	/* (멘토)한 명의 유저가 팔로우는 여러개 보유 가능 */
	@OneToMany(mappedBy = "followerMember", fetch = FetchType.LAZY)
	private List<Follow> followers = new ArrayList<>();
	
	/* (멘티)한 명의 유저가 팔로우는 여러개 보유 가능 */
	@OneToMany(mappedBy = "followedMember", fetch = FetchType.LAZY)
	private List<Follow> followeds = new ArrayList<>();
	
	/* 한 명의 유저가 신고는 여러개 보유 가능 */
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Report> reports = new ArrayList<>();
	
	/* 한 명의 유저가 채팅방은 여러개 보유 가능 */
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<ChatMessage> chatMessages = new ArrayList<>();
	
	/***** 한 명의 유저가 채팅방의 상태는 여러개 보유 가능?? *****/
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<ChatRoomStatus> chatRoomStatus = new ArrayList<>();
	
	/* 한 명의 유저가 채팅방 신청 여러개 보유 가능 */
	@OneToMany(mappedBy = "mentee", fetch = FetchType.LAZY)
	private List<MentoringRequest> mentee = new ArrayList<>();
	
	/* 한 명의 유저가 채팅방 신청 여러개 보유 가능 */
	@OneToMany(mappedBy = "mentor", fetch = FetchType.LAZY)
	private List<MentoringRequest> mentor = new ArrayList<>();
	
	/* 한 명의 유저가 채팅방 좋아요/싫어요 여러개 보유 가능 */
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Vote> votes = new ArrayList<>();
	
	/* 한 명의 유저가 알람 여러개 보유 가능 */
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Alarm> alarms = new ArrayList<>();
	
	/* 한 명의 유저(멘티)가 질문글 여러개 보유 가능 */
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Inquiry> Inquirys = new ArrayList<>();
	
	/* 한 명의 유저(멘토)가 답변글 여러개 보유 가능 */
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Answer> answers = new ArrayList<>();
	
	
	
	
	
}
