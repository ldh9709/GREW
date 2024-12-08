package com.itwill.jpa.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.itwill.jpa.validation.validator.MemberEmailValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented //문서화를 위한 메타 애노테이션                                                                     //애노테이션 정보를 문서에 같이 보여줌
@Constraint(validatedBy = MemberEmailValidator.class)                              //하단 설명 참고
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })   //해당 어노테이션이 어디에 사용될 수 있는지
@Retention(RUNTIME)//애노테이션이 런타임 동안 유지됨을 지정      
public @interface Email {
	
	//유효성 검증 실패 시 반환되는 기본 메시지를 정의
	String message() default "Email is not allow";
	
	/*
	 * Bean Validation에서 그룹 기반 검증을 지원하기 위한 속성
	 * 
	 */
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
