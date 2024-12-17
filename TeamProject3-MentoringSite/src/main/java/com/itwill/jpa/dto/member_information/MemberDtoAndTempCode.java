package com.itwill.jpa.dto.member_information;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itwill.jpa.entity.member_information.Interest;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.role.Role;
import com.itwill.jpa.validation.annotation.Email;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MemberDtoAndTempCode {
	
	private MemberDto memberDto;
	
	private Integer tempCode;
	
}
