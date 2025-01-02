package com.itwill.jpa.service.member_information;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.member_information.CareerDto;
import com.itwill.jpa.entity.member_information.Career;
import com.itwill.jpa.repository.member_information.CareerRepository;

@Service
public class CareerServiceImpl implements CareerService{
	@Autowired
	private CareerRepository careerRepository;
	
	@Override
	public List<CareerDto> getCareerByMentorProfileNo(Long mentorProfileNo) {
		List<Career> careers = careerRepository.findByMentorProfile_MentorProfileNo(mentorProfileNo);
    	List<CareerDto> careerDtos = new ArrayList<>();
    	for (int i = 0; i < careers.size(); i++) {
    		careerDtos.add(CareerDto.toDto(careers.get(i)));
		}
    	return careerDtos;
	}
	@Override
	public List<CareerDto> save_updateCareer(List<CareerDto> careerDtos, Long mentorProfileNo) {
		for (int i = 0; i < careerDtos.size(); i++) {
        	careerDtos.get(i).setMentorProfileNo(mentorProfileNo);
			careerRepository.save(Career.toEntity(careerDtos.get(i)));
		}
		return careerDtos;
	}
	@Override
	public List<Career> saveDummyCareer(Long mentorProfileNo) {
		CareerDto careerDto = CareerDto.builder()
				.careerCompanyName("유령 회사")
				.careerJobTitle("유령 직책")
				.careerStartDate(null)
				.careerEndDate(null)
				.mentorProfileNo(mentorProfileNo)
				.build();
    	Career career = careerRepository.save(Career.toEntity(careerDto));
		List<Career> careers = new ArrayList<>();
		careers.add(career);
		return careers;
	}
}
