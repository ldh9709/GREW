package com.itwill.jpa.dto.member_information;

import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Follow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowResponseDto {
	/*출력 데이터 : 멘토이름, 대분류 카테고리이름, 소분류 카테고리이름 */
	private String mentorName;
	private String primaryCategory;
	private String subCategory;
	
}
