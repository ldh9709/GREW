package com.itwill.jpa.dto.member_information;

import java.time.LocalDate;

import com.itwill.jpa.entity.member_information.Career;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareerDto {
	private Long careerNo;
	private String careerCompanyName;
	private String careerJobTitle;
	private LocalDate careerStartDate;
	private LocalDate careerEndDate;
	
	private Long mentorProfileNo;

	public static CareerDto toDto(Career career) {
		return CareerDto.builder()
						.careerNo(career.getCareerNo())
						.careerCompanyName(career.getCareerCompanyName())
						.careerJobTitle(career.getCareerJobTitle())
						.careerStartDate(career.getCareerStartDate())
						.careerEndDate(career.getCareerEndDate())
						.mentorProfileNo(career.getMentorProfile().getMentorProfileNo())
						.build();
	}
}
