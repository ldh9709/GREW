package com.itwill.jpa.dto;

import java.util.Date;

import com.itwill.jpa.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
	
	private Long userNo;
	private String userId;
	private String userPassword;
	private String userEmail;
	private String userName;
	private String userRole;
	private String userPoints;
	private String userStatus;
	private Date userJoinDate;
	private String userReportCount;
	
	/*
	 * Entitiy -> DTO
	 */
	public static UserDto toDto(User userEntity) {
	    
		return UserDto.builder()
	            .userNo(userEntity.getUserNo())
	            .userId(userEntity.getUserId())
	            .userPassword(userEntity.getUserPassword())
	            .userEmail(userEntity.getUserEmail())
	            .userName(userEntity.getUserName())
	            .userRole(userEntity.getUserRole())
	            .userPoints(userEntity.getUserPoints())
	            .userStatus(userEntity.getUserStatus())
	            .userJoinDate(userEntity.getUserJoinDate())
	            .userReportCount(userEntity.getUserReportCount())
	            .build();
	}
	
	
}
