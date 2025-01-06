package com.itwill.jpa.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.itwill.jpa.auth.PrincipalDetailsService;
import com.itwill.jpa.auth.PrincipalOauth2UserService;
import com.itwill.jpa.security.filter.JWTCheckFilter;
import com.itwill.jpa.security.handler.APIAuthLoginSuccessHandler;
import com.itwill.jpa.security.handler.APILoginFailHandler;
import com.itwill.jpa.security.handler.APILoginSuccessHandler;

@Configuration//이 클래스를 읽고 빈으로 등록한다
@EnableWebSecurity(debug = true)//Spring Security의 설정을 활성화
@EnableMethodSecurity
public class SecurityConfig {
	
	//OAuth2 로그인 시(SNS) 사용자 정보를 처리하는 서비스
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserSerivce;
    
	//폼 로그인 시 사용자 인증 정보를 처리하는 서비스
	@Autowired
	private PrincipalDetailsService principalDetailsService;
	
//	public static final String apiMemberPattern = "/member/**";
//	  
//	public static final String[] 
//			SwaggerPatterns = {"/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs/**","/v3/api-docs", "/swagger-ui.html", "/login", apiMemberPattern };
	
	//인증 없이 접근 가능한 경로 정의
	private final String[] whitelist = {
		"/**", //모든 경로
		/***** MEMBER *****/
		"/login", //로그인
		"/oauth2/**", //SNS로그인
		"/member/check-memberId", //아이디 중복 검사
		"/member/check-memberEmail", //이메일 중복 검사
		"/membe/sendJoinCode", //인증번호 발송
		"/member/createMember", //회원가입/관심사 입력
		"/member/createMember/mento", //회원가입/관심사 입력/멘토더미데이터
		"/member/member-info", //회원 정보 보기
		
		/***** MENTOR PROFILE *****/
		"/mentor-profile/*", // 멘토 프로필 상세보기
		"/mentor-profile/status/*", // 특정 상태의 멘토 목록 조회 페이징
		"/mentor-profile/search/*", // 멘토 검색 기능 페이징
		"/mentor-profile/*/member_no", // 멘토의 멤버번호 조회
		"/mentor-profile/category/*", // 카테고리별 멘토 리스트 조회
		"/mentor-profile/category/*/follow", // 팔로우 순으로 CATEGORY_NO별 멘토 리스트 조회
		"/mentor-profile/category/*/mentoring", // 멘토링 횟수 순으로 CATEGORY_NO별 멘토 리스트 조회
		"/mentor-profile/category/*/activity", // 활동 수 순으로 CATEGORY_NO별 멘토 리스트 조회
		"/mentor-profile/*/parent/follow", // 팔로우 순으로 대분류 카테고리의 멘토 리스트 조회
		"/mentor-profile/*/parent/mentoring", // 멘토링 횟수 순으로 대분류 카테고리의 멘토 리스트 조회
		"/mentor-profile/*/parent/activity", // 활동 순으로 대분류 카테고리의 멘토 리스트 조회
		"/mentor-profile/follow-count", // 팔로우 수 순으로 멘토 목록 조회
		"/mentor-profile/mentoring-count", // 멘토링 횟수 순으로 멘토 목록 조회
		"/mentor-profile/activity-count", // 활동 수 순으로 멘토 목록 조회
		"/mentor-profile/*/mentoring-count", // 멘토의 멘토링 횟수 조회
		"/mentor-profile/*/follow-count", // 멘토의 팔로우 수 조회
		"/mentor-profile/*/activity-count", // 멘토의 활동 수 조회
		"/mentor-profile/rating", // 별점 순 멘토 순위
		"/mentor-profile/career/*", // 경력 데이터 가져오기
		"/mentor-profile/*/image-url", // 멘토 프로필 이미지 URL 조회

		
		/***** MENTOR BOARD *****/
	    "/mentor-board/sorted/*", // 멘토 보드 리스트 - sorted/{status}
	    "/mentor-board/*", // 멘토 보드 상세 조회 - {mentorBoardNo}
	    "/mentor-board/*/views", // 멘토 보드 조회수 증가 - {mentorBoardNo}/views
	    "/mentor-board/sorted/views", // 멘토 보드 조회수 기준 페이징
	    "/mentor-board/search/*", // 멘토 보드 검색 기능 페이징 - /search/{search}
	    "/mentor-board/sorted/date/other", // 멘토 보드 날짜 기준 페이징
	    "/mentor-board/list/*", // 멘토 작성 콘텐츠 (프로필 페이지 용도) - {mentorProfileNo}
	    "/mentor-board/*/upload-image", // 이미지 업로드 엔드포인트 - {mentorBoardNo}/upload-image
	    "/mentor-board/*/image-url", // 이미지 URL 가져오기 엔드포인트 - {mentorBoardNo}/image-url
	    "/mentor-board/*/view-count", // 카테고리별 멘토 콘텐츠 리스트 출력(조회수 기준) - {categoryNo}/view-count
	    "/mentor-board/*/parent/view-count", // 카테고리별(대분류) 멘토 콘텐츠 리스트 출력(조회수 기준) - {categoryNo}/parent/view-count
	    "/mentor-board/*/date", // 카테고리별 멘토 콘텐츠 리스트 출력(최신순) - {categoryNo}/date
	    "/mentor-board/*/parent/date", // 카테고리별(대분류) 멘토 콘텐츠 리스트 출력(최신순) - {categoryNo}/parent/date
	    "/mentor-board/sorted/views/status", // 멘토 보드 리스트 상태값, 조회수 기준 출력 - 출처 불명? 미사용?
		
	    /***** Answer *****/
	    "/answer/accept/*", // 답변 채택
	    "/answer/*/answer-vote", // 질문에 작성된 답변 조회(추천)
	    "/answer/*/answer-date", // 질문에 작성된 답변 조회(최신)
	    "/answer/*/category-vote", // 카테고리별 답변 조회(추천)
	    "/answer/*/category-date", // 카테고리별 답변 조회(최신)
	    "/answer/view/*", // 답변 상세보기
	    "/answer/recently-vote", // 최근 3일간 추천 베스트
	    "/answer/isAnswer", // 본인 답변 유무
	    "/answer/count-answer/*", // 한 질문의 답변 수
	    
	    /***** INQUIRY *****/
	    "/inquiry/view/*", // 질문 보기
	    "/inquiry/increase/*", // 질문 조회수 증가
	    "/inquiry/*/answer-count", // 답변 수 많은 순 카테고리별 질문 출력
	    "/inquiry/*/parent/answer-count", // 답변 수 많은 순 카테고리별(대분류) 질문 출력
	    "/inquiry/*/view-count", // 조회수 순 카테고리별 질문 출력
	    "/inquiry/*/parent/view-count", // 조회수 순 카테고리별(대분류) 질문 출력
	    "/inquiry/*/date", // 최신순 카테고리별 질문 출력
	    "/inquiry/*/parent/date", // 최신순 카테고리별(대분류) 질문 출력
	    "/inquiry/answer-count", // 답변 수 순으로 전체 질문 출력
	    "/inquiry/view-count", // 조회수 순으로 전체 질문 출력
	    "/inquiry/date", // 최신순으로 전체 질문 출력
	    "/inquiry/search/*", // 검색 기능
	    "/inquiry/find/*", // 질문 번호로 객체 찾기
	    
	    /***** VOTE *****/
	    "/*/votes", // 추천 - 비추천 값
		
	    /***** ALARMS *****/
	    "/alarms/*/redirect", // 알림 클릭 시 URL 전송
	    "/alarms/alarms", // 한 명의 알림 리스트 출력
	    "/alarms/all/isread", // 한 명의 알림 전체 읽음 처리
	    "/alarms/delete", // 선택 알림 삭제
	    "/alarms/delete/all", // 멤버 알림 전체 삭제
	    "/alarms/is-read/*", // 알림 한 개 읽음 표시
	    "/alarms/is-read/count", // 안 읽은 알림 개수
	    "/alarms/find-alarm", // 알람 객체 찾기	
	    
	    /***** FOLLOW *****/
	    "/follow/mentor/*", // 팔로워 수 조회
	    "/follow/is-exist/*", // 팔로우 등록 여부 체크
	    
	    /***** CHATROOM *****/
	    "/chatroom/create/*", // 채팅방 신청
	    "/chatroom/active/*", // 채팅방 활성화
	    "/chatroom/completed/*", // 활동 종료
	    "/chatroom/rejected/*", // 멘토가 요청을 수락하지 않음
	    "/chatroom/canceled/*", // 멘티가 요청을 철회함
	    "/chatroom/closed/*", // 관리자가 비정상적인 요청 종료
	    "/chatroom/messages/*", // 채팅방 대화 목록
	    "/chatmessage/update/*", // 읽음 상태 변경
	    "/chatmessage/*", // 특정 메시지 선택
	    
	    /***** REVIEW *****/
	    "/review/detail/*" //리뷰 보기
	};
	
	//인증이 필요한 경로 정의
	private static final String[] AUTHENTICATED = {
		/***** MEMBER *****/
	    "/member/profile", //회원(본인) 정보 보기
	    "/member/profile/modify", //회원 정보 수정
	    "/member/profile/delete", //회원 탈퇴
	    "/member/status/*", //멤버 상태 변경
	    
	    /***** MENTOR PROFILE *****/
	    "/mentor-profile/my-profile/mentor-rating", // 자신의 멘토 프로필 mentor_rating 조회
	    "/mentor-profile/create-profile", // 멘토 프로필 생성
	    "/mentor-profile/*/create-dumy-profile", // 멘토 더미 프로필 생성
	    "/mentor-profile/status/*", // 멘토 프로필 상태변경
	    "/mentor-profile/*", // 멘토 프로필 수정
	    "/mentor-profile/*/upload-image", // 멘토 프로필 이미지 업로드
	    
	    /***** MENTOR BOARD *****/
	    "/mentor-board", // 멘토 보드 등록
	    "/mentor-board/*", // 멘토 보드 수정 - {mentorBoardNo}
	    "/mentor-board/*/status", // 멘토 보드 삭제(상태변경) - {mentorBoardNo}/status
	    "/mentor-board/list/member", // 멘토 작성 콘텐츠 (마이페이지 용도)
	    
	    /***** VOTE *****/
	    "/vote/*/upvote", // 추천
	    "/vote/*/downvote", // 비추천
	    
	    /***** FOLLOW *****/
	    "/follow", // 팔로우 신청
	    "/follow/*", // 팔로우 멘토 멤버 번호로 조회
	    "/follow/followList", // 팔로우 리스트 조회
	    "/follow/cancel/*", // 팔로우 번호로 취소	 
	    
	    /***** REPORT *****/
	    "/report", // 신고 등록
	    
	    /***** CHATROOM *****/
	    "/chatroom/leave/*", // 채팅방 나감
	    "/chatroom/list", // 채팅방 리스트 (멘토, 멘티 구분)
	    "/chatroom/list/active", // 채팅방 활동 리스트
	    "/chatroom/list/wait", // 채팅방 대기 리스트
	    "/chatroom/name/*", // 채팅방 제목 변경
	    "/chatmessage/count/message", // 안 읽은 메시지 갯수
	    
	    /***** REVIEW *****/
	    "/review/create" //리뷰 작성
	};
	
	//멘티 인증이 필요한 경로 정의
	private static final String[] MENTEE_AUTHENTICATED = {
		/***** MEMBER *****/
		"/member/mentee-summary", //멘티 활동 내역 요약	
		
	    /***** INQUIRY *****/
	    "/inquiry", // 질문 등록
	    "/inquiry/update/*", // 질문 수정
	    "/inquiry/delete/*", // 질문 삭제
	    "/inquiry/list/member" // 내가 작성한 질문 내역		
	};
	
	//멘토 인증이 필요한 경로 정의
	private static final String[] MENTOR_AUTHENTICATED = {
		/***** MEMBER *****/
		"/member/mentor-summary", //멘토 활동 내역 요약	
		
		/***** Answer *****/
	    "/answer/*", // 답변 등록
	    "/answer/delete/*", // 답변 삭제
	    "/answer/update/*" // 답변 수정	
	    
	};
	
	//관리자 인증이 필요한 경로 정의
	private static final String[] ADMIN_AUTHENTICATED = {
		"/admin/**" //어드민 전체
	};
	
	//멘티 혹은 멘토 인증이 필요한 경로 정의
	private static final String[] MENTEE_MENTOR_AUTHENTICATED = {
		/***** Answer *****/
		"/answer" //내가작성한답변내역

	};
	
	//모든 인증이 가능한 경로 정의
	private static final String[] MENTEE_MENTOR_ADMIN_AUTHENTICATED = {
		"/member/update-role/**", //회원 권한 수정
	};
	
	/* Spring Security에서 HTTP 요청에 대한 보안 설정을 구성 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		
		//개발 단계에서 CSRF 보호를 비활성화
		httpSecurity.csrf((config) -> {config.disable();});
		
		// 세션 관리 설정: Stateless 설정, 서버는 세션을 생성하지 않음
		httpSecurity.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		
		
		 //CORS 설정: 외부에서 이 API를 호출할 수 있도록 CORS 설정을 정의
		 httpSecurity.cors(httpSecurityCorsConfigurer -> {
		      httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());  // CORS 설정을 적용
		    });
		
		
		/*
		 * Spring Security에 UserDetailsService 구현체를 등록하는 역할
		 * 폼 기반 로그인에서 사용자가 입력한 username(여기서는 email)으로 사용자 정보를 가져오도록 Spring Security 인증 흐름을 설정
		 * principalDetailsService : userDetailService인터페이스를 구현한 구현체
		 */
		httpSecurity.userDetailsService(principalDetailsService);
		 
		//SNS로그인
		httpSecurity.oauth2Login((config) -> {
			config.loginPage("/login")//로그인 페이지 경로
			 .userInfoEndpoint((userInfoEndpointConfig) -> {
			 /***
			  * Spring Security의 OAuth2 사용자 정보 처리는 userInfoEndpoint를 통해 수행된다.
			  * 1. 사용자가 Google, Kakao, 또는 Naver 버튼을 클릭 → Spring Security가 OAuth2 인증을 시작.
			  * 2. 사용자는 SNS 로그인 화면으로 리다이렉트되어 인증 진행
			  * 3. SNS 인증이 성공하면, Spring Security가 access token과 사용자 정보를 받아온다.
			  * 4. principalOauth2UserService.loadUser()가 호출되어 사용자 정보를 처리
			  * 5. principalOauth2UserService는 데이터베이스에서 해당 이메일을 가진 사용자를 조회하거나 새로 등록
			  * 6. 인증된 사용자 정보는 PrincipalDetails 객체로 반환되어 Spring Security 세션에 저장
			  */
				userInfoEndpointConfig.userService(principalOauth2UserSerivce);
				config.successHandler(new APIAuthLoginSuccessHandler());
				config.failureHandler(new APILoginFailHandler());
			 });
		});
		
		//폼 기반 로그인 구성
		httpSecurity.formLogin((config) -> {
			config.loginPage("/login");
			config.successHandler(new APILoginSuccessHandler());
			config.failureHandler(new APILoginFailHandler());
		});
		
		httpSecurity.addFilterBefore(new JWTCheckFilter(), UsernamePasswordAuthenticationFilter.class);
				
		//로그아웃
		httpSecurity.logout((t) -> {
			t.logoutUrl("/logout")//로그아웃
			.deleteCookies("member")//쿠키삭제
			.addLogoutHandler(new SecurityContextLogoutHandler()); // SecurityContext 초기화
		});

		/***** 페이지 접근 경로 *****/
		httpSecurity.authorizeHttpRequests((authorizeHttpRequestsConfig) -> {
		      // swagger설정
		      authorizeHttpRequestsConfig
		      
		      	  .requestMatchers(AUTHENTICATED).authenticated()//인증된 사용자만 접근 가능
		      	  
		      	  .requestMatchers(MENTEE_AUTHENTICATED).hasRole("MENTEE") //MENTEE만 접근 가능
		      	  
		      	  .requestMatchers(MENTOR_AUTHENTICATED).hasRole("MENTOR") //MENTEE만 접근 가능
		      	  
		      	  .requestMatchers(MENTOR_AUTHENTICATED).hasRole("ADMIN") //MENTEE만 접근 가능
		      	  
		      	  .requestMatchers(MENTEE_MENTOR_AUTHENTICATED).hasAnyRole("MENTEE" , "MENTOR") //MENTEE, MENTOR만 접근 가능
		      	  
		      	  .requestMatchers(MENTEE_MENTOR_ADMIN_AUTHENTICATED).hasAnyRole("MENTEE", "MENTOR", "ADMIN") //MENTEE, MENTOR만 접근 가능
		      	  
			      .requestMatchers(whitelist).permitAll() //인증 없이 접근 가능
			      
			      .anyRequest().authenticated();
		    });
		
		return httpSecurity.build();
		
		
	}
	
	/* CORS 객체 설정 */
	 @Bean
	  public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();  // CORS 설정 객체 생성

	    // CORS 설정
	    configuration.setAllowedOriginPatterns(Arrays.asList("*"));  // 모든 출처에서 요청을 허용
	    configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));  // 허용할 HTTP 메소드 설정
	    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));  // 허용할 HTTP 헤더 설정
	    configuration.setAllowCredentials(true);  // 쿠키를 허용

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  // CORS 설정을 경로 기반으로 적용
	    source.registerCorsConfiguration("/**", configuration);  // 모든 경로에 대해 CORS 설정 적용

	    return source;  // CORS 설정을 반환
	  }
	
	
}
