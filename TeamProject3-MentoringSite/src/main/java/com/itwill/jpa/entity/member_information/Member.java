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
    private Long memberNo;

    @Column(name = "member_id")
    @Size(min = 3, max = 15, message = "아이디는 3자 이상 15자 이하로 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "아이디는 영문자, 숫자, '-', '_'만 허용됩니다.")
    private String memberId;

    @Column(name = "member_password")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&]).*$", message = "비밀번호는 대문자, 소문자, 숫자, 특수문자 중 두 가지 이상을 포함해야 합니다.")
    private String memberPassword;

    @Column(name = "member_email")
    @Email(message = "유효하지 않은 이메일 형식입니다.")
    private String memberEmail;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_points")
    private Integer memberPoints;

    @Column(name = "member_status")
    private Integer memberStatus;

    @Column(name = "member_join_date")
    private LocalDateTime memberJoinDate;

    @Column(name = "member_report_count")
    private Integer memberReportCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role")
    private Role memberRole;

    @Column(name = "member_provider")
    private String memberProvider;

    @PrePersist
    public void setDefaultValues() {
        if (this.memberRole == null) this.memberRole = Role.ROLE_MENTEE;
        if (this.memberPoints == null) this.memberPoints = 0;
        if (this.memberStatus == null || this.memberStatus == 0) this.memberStatus = 1;
        if (this.memberJoinDate == null) this.memberJoinDate = LocalDateTime.now();
        if (this.memberReportCount == null) this.memberReportCount = 0;
        if (this.memberProvider == null) this.memberProvider = "Email";
    }

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private MentorProfile mentorProfile;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Interest> interests = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @Builder.Default
    private List<MentorBoard> mentorBoards = new ArrayList<>();

    @OneToMany(mappedBy = "menteeMember", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Follow> followMentees = new ArrayList<>();

    @OneToMany(mappedBy = "mentorMember", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Follow> followMentors = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @Builder.Default
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @Builder.Default
    private List<ChatRoomStatus> chatRoomStatus = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Vote> votes = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Alarm> alarms = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Inquiry> Inquirys = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Answer> answers = new ArrayList<>();

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

    public static Member toSecurityEntity(MemberSecurityDto memberDto) {
        return Member.builder()
                .memberId(memberDto.getMemberId())
                .memberName(memberDto.getMemberName())
                .memberEmail(memberDto.getMemberEmail())
                .memberPassword(memberDto.getMemberPassword())
                .memberProvider(memberDto.getMemberProvider())
                .build();
    }
}
