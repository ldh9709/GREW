package com.itwill.jpa.util;

import java.io.UnsupportedEncodingException;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.security.Keys;

/*
JWT(Json Web Token)란 Json 포맷을 이용하여 사용자에 대한 속성을 저장하는 Claim 기반의 Web Token이다. 
JWT는 토큰 자체를 정보로 사용하는 Self-Contained 방식으로 정보를 안전하게 전달한다. 
*/

public class JWTUtil {
	
	private static final String key = "1234567890123456789012345678901234567890"; 

    // JWT 생성 메서드(클레임,유효시간)
    public static String generateToken(Map<String, Object> valueMap, int min) {
    	System.out.println(">>>>>generateToken valueMap : " + valueMap);
    	SecretKey key = null;
    	
    	try {
    		key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
    	
    	
        String jwtStr = Jwts.builder()
        		//헤더에 "typ": "JWT"를 설정하여 이 토큰이 JWT임을 명시
                .setHeader(Map.of("typ","JWT"))
                //사용자 정의 데이터를 클레임으로 설정
                .setClaims(valueMap)
                //현재 시간을 발급 시간으로 설정
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                //만료 시간을 현재 시간 + min 분으로 설정(임시로 60분 설정)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                //비밀 키를 사용해 서명을 추가
                .signWith(key)
                //JWT 문자열을 생성
                .compact();
        //JWT객체 반환
        return jwtStr;
    }
    /*
     * JWT토큰 검증
     * token: 클라이언트로부터 전달받은 JWT 문자열
     * 리턴값: 검증된 토큰에서 추출한 클레임 데이터(Map 형태)
     */
    public static Map<String, Object> validateToken(String token, HttpServletRequest request, HttpServletResponse response){
    	System.out.println("validateToken token : >>>>>" + token);
    	Map<String, Object> claim = null;
    	
    	try {
    		 //generateToken 메서드에서 사용한 것과 동일한 방식으로 비밀 키를 생성
    		SecretKey key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));
    		
    		//JWT를 파싱하는 빌더 객체를 반환
    		claim = Jwts.parserBuilder()
    				//서명 검증에 사용할 비밀 키를 설정
    				.setSigningKey(key)
    				.build()
    				//토큰을 파싱하고 서명을 검증, 실패 시 에러
    				//parseClaimsJwt : 서명이 없는 토큰 파싱이라 수정
    				.parseClaimsJws(token)
    				//파싱된 클레임 데이터를 반환
    				.getBody();
    		
    		System.out.println("JWT 토큰 검증 완료: " + claim);
    		
        }catch(MalformedJwtException malformedJwtException){
        	//토큰의 구조가 잘못된 경우
            throw new CustomJWTException("MalFormed");
            
        }catch(ExpiredJwtException expiredJwtException){
	        /**** 토큰이 만료된 경우 ****/
        	
	    	// 요청에서 리프레시 토큰 추출
	        String refreshToken = extractRefreshTokenFromCookie(request);
	        System.out.println(">>>>>리프레시 토큰 추출 : " + refreshToken); 
	        System.out.println("Extracted Refresh Token: " + refreshToken);
	        
	        if (refreshToken != null && !refreshToken.contains(".")) {
	            System.out.println("리프레시 토큰이 JWT 형식이 아닙니다: " + refreshToken);
	        }
	        
	        // 리프레시 토큰 검증
	        if (refreshToken != null && isValidRefreshToken(refreshToken)) {
	            // 유효한 리프레시 토큰으로 새로운 액세스 토큰 발급
	            Map<String, Object> refreshClaims = expiredJwtException.getClaims();
	            String newAccessToken = generateToken(refreshClaims, 60);
	
	            // 응답에 새로운 액세스 토큰 추가
	            response.setHeader("Authorization", "Bearer " + newAccessToken);
	            
	            return refreshClaims;
	        } else {
	            // 리프레시 토큰이 유효하지 않으면 예외 처리
	            throw new CustomJWTException("RefreshTokenInvalid");
	        	}
	        
	        }catch(InvalidClaimException invalidClaimException){
	        	//클레임이 유효하지 않은 경우
	            throw new CustomJWTException("Invalid");
	        }catch(JwtException jwtException){
	        	//기타 JWT 관련 에러
	            throw new CustomJWTException("JWTError");
	        }catch(Exception e){
	            throw new CustomJWTException("Error");
	        }
	    	return claim;
    	}
    
    /* 쿠키에서 토큰 가져오기 */
    private static String extractRefreshTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("member".equals(cookie.getName())) {
                    try {
                        // Base64 디코딩
                        String decodedCookie = new String(Base64.getDecoder().decode(cookie.getValue()), "UTF-8");
                        // JSON 파싱
                        ObjectMapper mapper = new ObjectMapper();
                        Map<String, String> cookieData = mapper.readValue(decodedCookie, Map.class);
                        return cookieData.get("refreshToken");
                    } catch (Exception e) {
                        throw new RuntimeException("쿠키 디코딩 실패", e);
                    }
                }
            }
        }
        return null;
    }
    
    /* 리프레시 토큰 유효성 검증 */
    public static boolean isValidRefreshToken(String refreshToken) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(refreshToken);
            return true;
        } catch (JwtException | UnsupportedEncodingException e) {
            System.out.println("리프레시 토큰 검증 실패: " + e.getMessage());
            return false;
        }
    }
}
