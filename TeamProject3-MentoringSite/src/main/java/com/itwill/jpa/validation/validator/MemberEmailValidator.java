package com.itwill.jpa.validation.validator;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.stereotype.Component;

import com.itwill.jpa.validation.annotation.Email;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


@Component//빈으로 등록
//ConstraintValidator 인터페이스를 구현, Email(사용할 어노테이션 이름), String 검증할 데이타
public class MemberEmailValidator implements ConstraintValidator<Email, String> {
	
	/*
	 * regexp : 이메일 형식을 검증하는 정규 표현식
	 * ^[0-9a-zA-Z]: 이메일은 숫자 또는 영문자로 시작해야 함.
	 * ([-_.]?[0-9a-zA-Z])*: 이메일의 로컬 파트는 알파벳, 숫자, 하이픈(-), 밑줄(_), 점(.)이 반복될 수 있음.
	 * 로컬 파트 :
	 * @: 반드시 @가 포함되어야 함.
	 * [0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*: 도메인 이름의 유효성 검사.
	 * \\.[a-zA-Z]{2,3}$: 최상위 도메인은 . 뒤에 2~3자리 영문자가 와야 함.
	 */
    String regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

    @Override
    /*
     * ConstrainValidator가 실행될 때 한 번 호출
     * 어노테이션의 속성을 초기화하거나 필요 시 기본 설정을 처리
     * 현재 비어있지만, 필요한 경우 커스터마이징 가능
     */
    public void initialize(Email constraintAnnotation) {
    	//어노테이션 입력 시 파라미터로 들어온 값 초기화
    }
	
    
	@Override
	/*
	 * 사용자가 입력한 값(Value)을 검증하는 핵심 메소드
	 * value = URLDecoder.decode(value, "UTF-8") :
	 * 입력값이 URL 인코딩 된 경우에도 검증이 정상적으로 수행되도록 처리
	 */
	public boolean isValid(String value, ConstraintValidatorContext context) {
		//기본메시지 비활성화
		context.disableDefaultConstraintViolation();
		
		try {
			/*
			 * 인코딩된 문자열을 디코딩(복호화)
			 * value라는 문자열이 URL 인코딩된 상태라면, 
			 * 이를 UTF-8 방식으로 디코딩하여 원래의 값으로 복원
			 */
			value = URLDecoder.decode(value, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//regexp와 value가 일치하지 않으면
		if(!value.matches(regexp)) {
			/*
			 * 제약 조건 위반 메시지를 설정
			 * 지정한 템플릿 메시지("이메일 형식과 맞지 않습니다.")를 기반으로 제약 조건 위반을 생성
			 * 실제로 검증 컨텍스트(ConstraintValidatorContext)에 제약 조건 위반을 추가
			 * 검증 실패 시 클라이언트(혹은 사용자)에게 반환
			 */
			context.buildConstraintViolationWithTemplate("이메일 형식과 맞지 않습니다.").addConstraintViolation();
			return false;
		}
		
		
		return true;
	}

}
