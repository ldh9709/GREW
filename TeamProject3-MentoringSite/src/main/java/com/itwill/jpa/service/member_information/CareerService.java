package com.itwill.jpa.service.member_information;

import java.util.List;

import com.itwill.jpa.dto.member_information.CareerDto;
import com.itwill.jpa.entity.member_information.Career;

public interface CareerService {
	List<CareerDto> getCareerByMentorProfileNo(Long mentorProfileNo);
	List<CareerDto> save_updateCareer(List<CareerDto> careerDtos, Long mentorProfileNo);
	List<Career> saveDummyCareer(Long mentorProfileNo);
}
