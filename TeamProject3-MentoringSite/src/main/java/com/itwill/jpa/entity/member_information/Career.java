package com.itwill.jpa.entity.member_information;

import java.time.LocalDate;

import com.itwill.jpa.dto.member_information.CareerDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "career")
public class Career {
	
	@Id
	@SequenceGenerator(name = "career_no_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "career_no_SEQ")
    @Column(name = "career_no")
	private Long careerNo;
	
	@Column(name = "career_company_name")
    private String careerCompanyName;

    @Column(name = "career_job_title")
    private String careerJobTitle;

    @Column(name = "career_start_date")
    private LocalDate careerStartDate;

    @Column(name = "career_end_date")
    private LocalDate careerEndDate;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_profile_no")
    private MentorProfile mentorProfile;
	
	 public static Career toEntity(CareerDto careerDto) {
		 return Career.builder()
				 	  .careerNo(careerDto.getCareerNo())
				 	  .careerCompanyName(careerDto.getCareerCompanyName())
				 	  .careerJobTitle(careerDto.getCareerJobTitle())
				 	  .careerStartDate(careerDto.getCareerStartDate())
				 	  .careerEndDate(careerDto.getCareerEndDate())
				 	  .mentorProfile(MentorProfile.builder().mentorProfileNo(careerDto.getMentorProfileNo()).build())
				 	  .build();
	 }

}
