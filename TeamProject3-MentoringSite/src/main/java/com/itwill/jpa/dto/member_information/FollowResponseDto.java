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
	/*출력 데이터 : 멘토이름, 멘토카테고리 1 이름, 2 이름 */
	private String mentorName;
	private CategoryResponseDto category;
	
	public static FollowResponseDto toDto(Follow entity) {
		CategoryResponseDto categoryResponseDto = CategoryResponseDto.toDto(entity.getFollowedMember().getMentorProfile().getCategory());
		
		return FollowResponseDto.builder()
				.mentorName(entity.getFollowedMember().getMemberName())
				.category(categoryResponseDto)
				.build();
	}
	
	
}
