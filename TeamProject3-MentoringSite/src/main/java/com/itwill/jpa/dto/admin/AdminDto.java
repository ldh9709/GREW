package com.itwill.jpa.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
	
	private Long adminNo;
	private String adminId;
	private String adminPassword;
	
	//public static AdminDto toDto(Admin adminEntity ) {
		
	//}

}
