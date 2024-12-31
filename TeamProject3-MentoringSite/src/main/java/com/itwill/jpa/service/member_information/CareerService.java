package com.itwill.jpa.service.member_information;

import java.util.List;

import com.itwill.jpa.dto.member_information.CareerDto;

public interface CareerService {
	List<CareerDto> getCareerByMentorProfileNo(Long mentorProfileNo);
	List<CareerDto> updateCareer(List<CareerDto> careerDtos);
}
