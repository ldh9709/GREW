package com.itwill.jpa.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

import com.itwill.jpa.dto.MemberDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Member {
	.
	@Id//PK설정
	@SequenceGenerator(name = "member_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
	private Long memberNo;
	
	private String memberId;
	private String memberPassword;
	private String memberEmail;
	private String memberName;
	private String memberRole;
	private String memberPoints;
	private String memberStatus;
	private LocalDate memberJoinDate;
	private String memberReportCount;
	
	 /*
     * DTO -> Entitiy
     */
	public static Member toEntity(MemberDto userDto) {
	    return Member.builder()
	            .memberNo(userDto.getMemberNo())
	            .memberId(userDto.getMemberId())
	            .memberPassword(userDto.getMemberPassword())
	            .memberEmail(userDto.getMemberEmail())
	            .memberName(userDto.getMemberName())
	            .memberRole(userDto.getMemberRole())
	            .memberPoints(userDto.getMemberPoints())
	            .memberStatus(userDto.getMemberStatus())
	            .memberJoinDate(userDto.getMemberJoinDate())
	            .memberReportCount(userDto.getMemberReportCount())
	            .build();
	}
	
	
	/*
	 * N(PRODUCT):1(CATEGORY): 다대일 관계. 
	 * 하나의 Category가 여러 Product에 연결될 수 있다.
	 * 이 관계에서 Product가 연관 관계의 소유자(Owner)다. 즉, Product 테이블에 외래 키가 존재한다
	 * 
	 * @ManyToOne: 다대일 관계를 나타내며, Product에서 Category로의 참조가 있음을 의미
	 * cascade = CascadeType.ALL: Product 엔티티에서의 모든 영속성 작업이 Category에도 전파된다. 
	 * 예를 들어, Product가 저장되거나 삭제되면 해당하는 Category에도 같은 작업이 수행된다.
	 * 
	 * fetch = FetchType.LAZY: 지연 로딩을 의미하며, Product가 조회될 때 Category는 즉시 로딩되지 않는다.
	 * Category가 실제로 접근될 때 데이터베이스에서 로드된다.
	 * => fetch = FetchType.LAZY는 지연 로딩(Lazy Loading) 방식으로 엔티티를 로드하겠다는 설정
	 * Product가 데이터베이스에서 조회될 때, 연관된 Category는 바로 조회하지 않고, 필요할 때까지 조회를 미룬다
	 * 예시)Product product = productRepository.findById(1L).get(); 
	 * 위 코드를 실행하면 Product 엔티티만 조회되고, Category는 조회되지 않는다. 
	 * 하지만 product.getCategory()를 호출하는 순간에야 Category 엔티티가 실제로 데이터베이스에서 조회된다
	 * 장점 : 메모리 절약에 유리.
	 *	
	 * mappedBy 속성은 JPA에서 연관 관계의 소유자가 아님을 의미하고, 
	 * 외래 키가 다른 엔티티 테이블(ProductDetail 테이블)에 있음을 나타낸다.
	 * mappedBy가 있으면 현재 엔티티(Product)는 외래 키를 가지지 않으므로, 
	 * 연관 관계의 주도권은 반대쪽(ProductDetail)에 있다.
	 * 
	 * cascade = CascadeType.ALL: Product에 대한 모든 영속성 작업이 ProductDetail에도 전파된다. 
	 * 
	 */
	
	
	
	/*
	 * fetch = FetchType.LAZY
	 * cascade = CascadeType.ALL
	 * 사용 여부 체크
	 */
	
	/* 한 명의 유저가 멘토 프로필은 한개 보유 가능 */
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	private MentorProfile mentorProfile;

	/* 한 명의 유저가 관심사 여러개 보유 가능 */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Interest> interests = new ArrayList<>();
	
	/* 한 명의 유저가 멘트 게시글은 여러개 보유 가능 */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<MentorBoard> mentorBoards = new ArrayList<>();
	
	/* 한 명의 유저가 팔로우는 여러개 보유 가능 */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Follow> follows = new ArrayList<>();
	
	/* 한 명의 유저가 신고는 여러개 보유 가능 */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Report> reports = new ArrayList<>();
	
	/* 한 명의 유저가 채팅방은 여러개 보유 가능 */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<ChatMessage> chatMessages = new ArrayList<>();
	
	/***** 한 명의 유저가 채팅방의 상태는 여러개 보유 가능?? *****/
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<ChatRoomStatus> chatRoomStatus = new ArrayList<>();
	
	/* 한 명의 유저가 채팅방 신청 여러개 보유 가능 */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<MentoringRequest> mentoringRequests = new ArrayList<>();
	
	/* 한 명의 유저가 채팅방 좋아요/싫어요 여러개 보유 가능 */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Vote> votes = new ArrayList<>();
	
	/* 한 명의 유저가 알람 여러개 보유 가능 */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Alarm> alarms = new ArrayList<>();
	
	/* 한 명의 유저(멘티)가 질문글 여러개 보유 가능 */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Question> questions = new ArrayList<>();
	
	/* 한 명의 유저(멘토)가 답변글 여러개 보유 가능 */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Answer> answers = new ArrayList<>();
	
	
	
	
	
}
