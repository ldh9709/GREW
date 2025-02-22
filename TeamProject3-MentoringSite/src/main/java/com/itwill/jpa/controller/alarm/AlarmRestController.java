package com.itwill.jpa.controller.alarm;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.entity.alarm.Alarm;
import com.itwill.jpa.repository.alarm.AlarmRepository;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.service.alarm.AlarmService;

import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/alarms")
@RestController
public class AlarmRestController {

	@Autowired
	private AlarmService alarmService;
	@Autowired
	private AlarmRepository alarmRepository;

	// 알림으로 페이지이동
	@Operation(summary = "알림 URL")
	@GetMapping("/{alarmNo}/redirect")
	public ResponseEntity<Response> redirectToPage(@PathVariable(name = "alarmNo") Long alarmNo) {
		AlarmDto alarmDto = AlarmDto.toDto(alarmRepository.findById(alarmNo).get());
		Response response = new Response();

		String redirectUrl = alarmService.alarmRedirectURL(alarmDto);
		response.setData(redirectUrl);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
		return responseEntity;
	}

	@Operation(summary = "멤버한명의 알림 출력")
	@GetMapping("/alarms")
	public ResponseEntity<Response> findByAlarmByMember(@RequestParam(name = "memberNo") Long memberNo) {

		List<AlarmDto> alarmDtoList = alarmService.findAlarmByMember(memberNo);
		Response response = new Response();
		response.setData(alarmDtoList);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);
		return responseEntity;

	}

	@Operation(summary = "멤버한명의 알림 전체 읽음")
	@PutMapping("/all/isread")
	public ResponseEntity<Response> setByAlarmIsReadByMember(@RequestParam(name = "memberNo") Long memberNo) {
		List<AlarmDto> alarmDtoList = alarmService.setAlarmIsReadByMember(memberNo);
		Response response = new Response();
		response.setData(alarmDtoList);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);
		return responseEntity;
	}

	@Operation(summary = "선택 알림 삭제")
	@DeleteMapping("/delete")
	public void deleteAlarm(@RequestParam(name = "alarmNo") Long AlarmNo) {
		alarmService.deleteAlarm(AlarmNo);
	}

	@Operation(summary = "멤버의 알림 전체삭제")
	@DeleteMapping("/delete/all")
	public void deleteAlarmByMemberNo(@RequestParam(name = "memberNo") Long memberNo) {
		alarmService.deleteAlarmByMemberNo(memberNo);
	}

	@Operation(summary = "알림 읽음표시")
	@PutMapping("/is-read/{alarmNo}")
	public void isReadAlarm(@PathVariable(name = "alarmNo") Long alarmNo) {
		alarmService.isReadAlarm(alarmNo);
	}

	@Operation(summary = "안읽은 알림 갯수")
	@PutMapping("/is-read/count")
	public ResponseEntity<Response> isReadAlarmCount(@RequestParam(name = "memberNo") Long memberNo) {
		Long count = alarmService.alarmIsReadCount(memberNo);
		Response response = new Response();
		response.setData(count);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);
		return responseEntity;
	}
	@Operation(summary = "알람 찾기")
	@GetMapping("find-alarm")
	public ResponseEntity<Response> findByAlarm(@RequestParam(name = "alarmNo") Long alarmNo) {
		AlarmDto alarmDto = alarmService.findAlarm(alarmNo);
		Response response = new Response();
		response.setData(alarmDto);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);
		return responseEntity;
	}
}
