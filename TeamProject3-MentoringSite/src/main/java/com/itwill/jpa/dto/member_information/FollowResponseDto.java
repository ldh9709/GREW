package com.itwill.jpa.dto.member_information;

import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Follow;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.entity.member_information.MentorProfile;

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
	private Long followNo;
	private String mentorName;
	private String mentorImage;
	private String primaryCategory;
	private String subCategory;
	
	public static FollowResponseDto toDto(Follow entity) {
		
		return FollowResponseDto.builder()
				.followNo(entity.getFollowNo())
				.mentorName(entity.getMentorMember().getMemberName())
				.mentorImage(entity.getMentorMember().getMentorProfile().getMentorImage())
				.primaryCategory(entity.getMentorMember().getMentorProfile().getCategory().getParentCategory().getCategoryName())
				.subCategory(entity.getMentorMember().getMentorProfile().getCategory().getCategoryName())
				.build();
	}
	
}
