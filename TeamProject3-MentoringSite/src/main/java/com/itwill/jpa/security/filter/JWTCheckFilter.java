package com.itwill.jpa.security.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.itwill.jpa.auth.PrincipalDetails;
import com.itwill.jpa.dto.member_information.MemberSecurityDto;
import com.itwill.jpa.entity.role.Role;
import com.itwill.jpa.util.JWTUtil;
import com.nimbusds.jose.shaded.gson.Gson;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
/* JWT 토큰을 확인하고 인증정보를 설정하는 역할
 * JWT토큰을 추출하여 검증하고 인증된 사용자 정보를 SecurityContext에 설정 하여 이후 요청을 처리하는
 * 다른 필터 인증된 사용자 정보를 사용할 수 있게 만듬
 * */
@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {

  //필터가 적용되지 않아야 할 요청들 정의
  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	 
	  
    // Preflight요청은 체크하지 않음
    if (request.getMethod().equals("OPTIONS")) {
      return true;
    }
    
    String path = request.getRequestURI();
    
    log.info("check uri.............." + path);
    if (path.startsWith("/swagger-ui") 
		|| path.startsWith("/category") 
		|| path.startsWith("/alarm") 
//		|| path.startsWith("/review")
//		|| path.equals("/inquiry") 
//		|| path.startsWith("/inquiry/list")
		|| path.startsWith("/inquiry/update")
		|| path.startsWith("/inquiry/increase")
		|| path.startsWith("/inquiry/delete")
		|| path.startsWith("/inquiry/view-count")
		|| path.startsWith("/inquiry/search")
		|| path.startsWith("/inquiry/find")
		|| path.startsWith("/inquiry/date")
		|| path.startsWith("/inquiry/view")
		|| path.endsWith("-count")
		|| path.endsWith("date")
		|| path.endsWith("-vote")
		|| path.endsWith("votes")
		|| path.startsWith("/inquiry/answer-count")
		|| path.startsWith("/answer/delete") 
		|| path.startsWith("/answer/accept") 
		|| path.startsWith("/answer/update") 
		|| path.startsWith("/answer/view") 
		|| path.startsWith("/answer/re") 

		|| path.startsWith("/mentor-profile") 
		|| path.startsWith("/mentor-board") 
		

		|| path.startsWith("/answer/count") 

		
		|| path.startsWith("/chatroom/rejected")
		|| path.startsWith("/chatroom/completed")
		|| path.startsWith("/chatroom/closed")
		|| path.startsWith("/chatroom/canceled")
		|| path.startsWith("/chatroom/active")
		|| path.startsWith("/chatroom/messages")

		|| path.startsWith("/member/sendJoinCode")
		|| path.startsWith("/member/createMember")
		|| path.startsWith("/member/findId")
		|| path.startsWith("/member/profile/edit")
		|| path.startsWith("/login")
		|| path.startsWith("/logout")
		|| path.startsWith("/main")
    	|| path.startsWith("/v3/api-docs") 
    	|| path.startsWith("/favicon.ico")
    	|| path.startsWith("/mentor-profile")
    	|| path.startsWith("/mentor-board")
    	|| path.startsWith("/mentorprofile")
    	) {
      return true;
    }
    
    
    //나머지 경로는 필터 적용됨
    return false;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
	  
	  // 요청 헤더에서 Authorization 추출
	  System.out.println(">>>>>>>>>>>>request : " + request);
	  System.out.println(">>>>>>>>>>>>response : " + response);
	  System.out.println(">>>>>>>>>>>>filterChain : " + filterChain);
	  
        String authHeaderStr = request.getHeader("Authorization");
        
        if (authHeaderStr == null || !authHeaderStr.startsWith("Bearer ")) {
            // Authorization 헤더가 없거나 형식이 올바르지 않으면 필터 체인 진행
            filterChain.doFilter(request, response);
            return;
        }
        
        System.out.println(">>>>>>>>>>>>authHeaderStr(JWT필터 적용되고 있음) : " + authHeaderStr);
        
        try {
          // Authorization 헤더에서 'Bearer ' 부분을 제외한 토큰만 추출
          String accessToken = authHeaderStr.substring(7);
          // JWTUtil.validateToken() 메서드를 사용하여 토큰을 검증하고 클레임을 추출
          Map<String, Object> claims = JWTUtil.validateToken(accessToken);
          log.info("JWT claims: " + claims); // 토큰에서 추출한 클레임 로깅
          
          // 클레임에서 사용자 정보를 추출
          //filterChain.doFilter(request, response); //이하 추가
          Long memberNo = ((Number) claims.get("memberNo")).longValue(); // Integer -> Long 변환
          String memberId = (String) claims.get("memberId");
          String memberPassword = (String) claims.get("memberPassword");
          String memberEmail = (String) claims.get("memberEmail");
          String memberName = (String) claims.get("memberName");
          Integer memberStatus = (Integer) claims.get("memberStatus");
          String memberProvider = (String) claims.get("memberProvider");
          Long mentorProfileNo = ((Number) claims.get("mentorProfileNo")).longValue();
          
          //memberRole을 처리하기 위해 String으로 받고 Role로 변환
          String roleName = (String) claims.get("memberRole");
          Role memberRole = Role.valueOf(roleName);
          
          //사용자 정보를 담은 DTO 생성
          MemberSecurityDto memberDto = MemberSecurityDto.builder()
                  .memberNo(memberNo)
                  .memberId(memberId)
                  .memberPassword(memberPassword)
                  .memberEmail(memberEmail)
                  .memberName(memberName)
                  .memberStatus(memberStatus)
                  .memberRole(memberRole)
                  .memberProvider(memberProvider)
                  .mentorProfileNo(mentorProfileNo)
                  .build();
          
          System.out.println(">>>>>JWTCheckFilter memberDTO : " + memberDto);
          // PrincipalDetails 객체 생성: 일반 로그인 또는 SNS 로그인 처리
          PrincipalDetails principalDetails = new PrincipalDetails(memberDto);
          System.out.println(">>>>>JWTCheckFilter principalDetails : " + principalDetails);
          
          log.info("-----------------------------------");
          log.info(memberDto);
          log.info(principalDetails.getAuthorities());
          // 인증 객체 생성
          UsernamePasswordAuthenticationToken authenticationToken
          = new UsernamePasswordAuthenticationToken(principalDetails, memberPassword, principalDetails.getAuthorities());
          // Spring Security의 SecurityContext에 인증 객체 설정
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          
          // 필터 체인 진행
          filterChain.doFilter(request, response);
        }catch(Exception e){
          e.printStackTrace();
          log.error("JWT Check Error..............");
          log.error(e.getMessage());
      	  // 오류 메시지 반환
          Gson gson = new Gson();
          String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));
          response.setContentType("application/json");
          PrintWriter printWriter = response.getWriter();
          printWriter.println(msg);
          printWriter.close();
    
        }
      }
    
    

}
