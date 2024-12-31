package com.itwill.jpa.entity.member_information;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.dto.member_information.MemberSecurityDto;
import com.itwill.jpa.entity.alarm.Alarm;
import com.itwill.jpa.entity.bullentin_board.Answer;
import com.itwill.jpa.entity.bullentin_board.Inquiry;
import com.itwill.jpa.entity.bullentin_board.Vote;
import com.itwill.jpa.entity.chatting_review.ChatMessage;
import com.itwill.jpa.entity.chatting_review.ChatRoomStatus;
import com.itwill.jpa.entity.report.Report;
import com.itwill.jpa.entity.role.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.jdbc.proxy.annotation.Signature;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "member")
public class Member {
	

	@Id
	@SequenceGenerator(name = "member_no_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_no_SEQ")
	@Column(name = "member_no")
	private Long memberNo;//멤버 번호 PK
	
	@Column(name = "member_id")
	@Size(min = 3, max = 15, message ="아이디는 3자 이상 15자 이하로 입력해주세요.")
	@Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "아이디는 영문자, 숫자, '-', '_'만 허용됩니다.")
	private String memberId;//Id
	
	@Column(name = "member_password")
	@Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&]).*$", message = "비밀번호는 대문자, 소문자, 숫자, 특수문자 중 두 가지 이상을 포함해야 합니다.")
	private String memberPassword;//비밀번호
	
	@Column(name = "member_email")
	@Email(message = "유효하지 않은 이메일 형식입니다.")
	private String memberEmail;//이메일
	
	@Column(name = "member_name")
	private String memberName;//이름
	
	@Column(name = "member_points")
	private Integer memberPoints;//멤버 연필 포인트
	
	@Column(name = "member_status")
	private Integer memberStatus;//멤버의 상태(활동, 정지 등)
	
	@Column(name = "member_join_date")
	private LocalDateTime memberJoinDate;//멤버 가입 날짜
	
	@Column(name = "member_report_count")
	private Integer memberReportCount;//신고 당한 횟수
	
	/***********************시큐리티를 위한 멤버 필드*****************************/
	@Enumerated(EnumType.STRING)
	@Column(name = "member_role")
	private Role memberRole;//역할
	
	@Column(name = "member_provider")
	private String memberProvider;//인증 제공자(일반 로그인이면 Null, 아니면 Google 등)
	
	
	
	
	
	/* 초기값 설정 */
	@PrePersist
	public void setDefaultValues() {
		if (this.memberRole == null) this.memberRole = Role.ROLE_MENTEE;
		if (this.memberPoints == null) this.memberPoints = 0;
		if (this.memberStatus == null || this.memberStatus == 0) this.memberStatus = 1;
		if (this.memberJoinDate == null) this.memberJoinDate = LocalDateTime.now();
		if (this.memberReportCount == null) this.memberReportCount = 0;
		if (this.memberProvider == null) this.memberProvider = "Email";
		if (this.interests == null || this.interests.isEmpty()) {
		this.interests = new ArrayList<>();
		this.addInterests(Interest.builder()
				                .category(Category.builder().categoryNo(19L).build())
				                .build());
        this.addInterests(Interest.builder()
				                .category(Category.builder().categoryNo(20L).build())
				                .build());
        this.addInterests(Interest.builder()
				                .category(Category.builder().categoryNo(21L).build())
				                .build());
		 }
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
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = {	CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
	@Size(min = 3, max = 3, message = "3개의 관심사를 선택해야 합니다.")
	@Builder.Default
	private List<Interest> interests = new ArrayList<>();
	
	/* 한 명의 유저가 멘트 게시글은 여러개 보유 가능 */
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<MentorBoard> mentorBoards = new ArrayList<>();
	
	/* (멘토)한 명의 유저가 팔로우는 여러개 보유 가능 */
	@OneToMany(mappedBy = "menteeMember", fetch = FetchType.LAZY)
	private List<Follow> followMentees = new ArrayList<>();
	
	/* (멘티)한 명의 유저가 팔로우는 여러개 보유 가능 */
	@OneToMany(mappedBy = "mentorMember", fetch = FetchType.LAZY)
	private List<Follow> followMentors = new ArrayList<>();
	
	/* 한 명의 유저가 신고는 여러개 보유 가능 */
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Report> reports = new ArrayList<>();
	
	/* 한 명의 유저가 채팅방은 여러개 보유 가능 */
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<ChatMessage> chatMessages = new ArrayList<>();
	
	/***** 한 명의 유저가 채팅방의 상태는 여러개 보유 가능?? *****/
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<ChatRoomStatus> chatRoomStatus = new ArrayList<>();
	
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
	

	/*
    * DTO -> Entitiy
    */
	public static Member toEntity(MemberDto memberDto) {
	    return Member.builder()
	    		.memberNo(memberDto.getMemberNo())
	            .memberId(memberDto.getMemberId())
	            .memberPassword(memberDto.getMemberPassword())
	            .memberEmail(memberDto.getMemberEmail())
	            .memberStatus(memberDto.getMemberStatus())
	            .memberName(memberDto.getMemberName())
	            .build();
	}
	
	/*
	 * SecurityDTO -> Entitiy
	 */
	public static Member toSecurityEntity(MemberSecurityDto memberDto) {
		return Member.builder()
				.memberId(memberDto.getMemberId())
				.memberName(memberDto.getMemberName())
				.memberEmail(memberDto.getMemberEmail())
				.memberPassword(memberDto.getMemberPassword())
				.memberProvider(memberDto.getMemberProvider())
				.build();
	}
	
	
	//흥미 추가
	public void addInterests(Interest interest) {
		interests.add(interest);
		interest.setMember(this);
		System.out.println(">>>>>>>>>>addInterests : " + interest);
	}
	
	//비밀번호 변경
	public void changePassword(String newPassword) {
		System.out.println("this.memberPassword(1) : "+ this.memberPassword);
		this.memberPassword = newPassword;
		System.out.println("this.memberPassword(2) : "+ this.memberPassword);
		System.out.println("newPassword : "+ newPassword);
	}
		
}
