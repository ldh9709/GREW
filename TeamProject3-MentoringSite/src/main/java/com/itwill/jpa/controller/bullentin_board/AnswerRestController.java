package com.itwill.jpa.controller.bullentin_board;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.auth.PrincipalDetails;
import com.itwill.jpa.dto.alarm.AlarmDto;
import com.itwill.jpa.dto.bulletin_board.AnswerDto;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.alarm.AlarmService;
import com.itwill.jpa.service.bullentin_board.AnswerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/answer")
public class AnswerRestController {

	@Autowired
	private AnswerService answerService;
	@Autowired
	private AlarmService alarmService;

	/* 답변 등록 */
	@Operation(summary = "답변 등록")
	@SecurityRequirement(name = "BearerAuth") // API 엔드포인트가 인증을 요구한다는 것을 문서화(Swagger에서 JWT인증을 명시
	@PreAuthorize("hasRole('MENTEE')") // ROLE이 MENTEE인 사람만 접근 가능 멘토로 변경해야함
	@PostMapping("{inquiryNo}")
	// @Transactional
	public ResponseEntity<Response> createAnswer(Authentication authentication, @RequestBody AnswerDto answerDto,
			@PathVariable("inquiryNo") Long inquiryNo) {
		// 1. 서비스 호출 : 답변 데이터 저장
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		answerDto.setMemberNo(principalDetails.getMemberNo());
		AnswerDto createAnswerDto = answerService.createAnswer(answerDto, inquiryNo);
		AlarmDto alarmDto = alarmService.createAlarmByAnswerToInquiry(createAnswerDto);
		// 2. 응답 데이터(Response 객체) 생성
		// - 응답객체에 코드, 메시지, 객체 설정
		Response response = new Response();
		response.setStatus(ResponseStatusCode.CREATED_ANSWER_SUCCESS);
		response.setMessage(ResponseMessage.CREATED_ANSWER_SUCCESS);
		response.setData(createAnswerDto);
		response.setAddData(alarmDto);
		// 3. 응답 헤더 설정(인코딩 타입)
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		// 4. 응답 Entity 생성(ResponseEntity)
		// - HTTP 상태 코드와 헤더, 응답 데이터를 포함한 ResponseEntity를 반환합니다.

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders,
				HttpStatus.CREATED);

		// 5. Entity 반환
		return responseEntity;

	}

	/* 답변 수정 */
	@Operation(summary = "답변 수정")
	@PutMapping("update")
	public ResponseEntity<Response> updateAnswer(@RequestBody AnswerDto answerDto) throws Exception {

		// 1. 서비스 호출 : 답변 업데이트 메소드 실행
		answerDto.setAnswerNo(answerDto.getAnswerNo());
		AnswerDto saveAnswerDto = answerService.updateAnswer(answerDto);

		// 2. 응답 데이터(Response 객체) 생성
		// - 응답객체에 코드, 메시지, 객체 설정
		Response response = new Response();
		response.setStatus(ResponseStatusCode.UPDATE_ANSWER_SUCCESS);
		response.setMessage(ResponseMessage.UPDATE_ANSWER_SUCCESS);
		response.setData(saveAnswerDto);

		// 3. 응답 헤더 설정(인코딩 타입)
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		// 4. 응답 Entity 생성(ResponseEntity)
		// - HTTP 상태 코드와 헤더, 응답 데이터를 포함한 ResponseEntity를 반환합니다.
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);

		return responseEntity;
	}

	/* 답변채택 */
	@Operation(summary = "답변 채택")
	@PutMapping("/accept/{answerNo}")
	public ResponseEntity<Response> acceptAnswer(@PathVariable(name = "answerNo") Long answerNo) throws Exception {

		AnswerDto acceptedAnswerDto = answerService.acceptAnswer(answerNo);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.ACCEPT_ANSWER_SUCCESS);
		response.setMessage(ResponseMessage.ACCEPT_ANSWER_SUCCESS);
		response.setData(acceptedAnswerDto);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		// 5. 응답 Entity 생성(ResponseEntity)
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);

		return responseEntity;
	}

	/* 답변 삭제(상태 업데이트) */
	@Operation(summary = "답변 삭제(상태 수정)")
	@PutMapping("/delete/{answerNo}")
	public ResponseEntity<Response> deleteAnswer(@PathVariable(name = "answerNo") Long answerNo) throws Exception {

		AnswerDto deleteAnswerDto = answerService.deleteAnswer(answerNo);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.DELETE_ANSWER_SUCCESS);
		response.setMessage(ResponseMessage.DELETE_ANSWER_SUCCESS);
		response.setData(deleteAnswerDto);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);

		return responseEntity;
	}

	/* 답변 상세보기 */
	@Operation(summary = "답변 상세보기")
	@GetMapping("view/{answerNo}")
	public ResponseEntity<Response> getAnswerByAnswerNo(@PathVariable(name = "answerNo") Long answerNo) {
		AnswerDto answerDto = answerService.getAnswer(answerNo);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_ANSWER_SUCCESS);
		response.setMessage(ResponseMessage.READ_ANSWER_SUCCESS);
		response.setData(answerDto);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);

		return responseEntity;

	}

	/* 질문 하나에 달린 답변 */
	/* 추천순 */
	@Operation(summary = "질문에 작성된답변조회(추천순)")
	@GetMapping("/{inquiryNo}/answer-vote")
	public ResponseEntity<Response> getByAnswerOrderByVoteDate(@PathVariable(name = "inquiryNo") Long inquiryNo,
			@RequestParam(name = "page", defaultValue = "0") int page, // 기본값은 0 페이지
			@RequestParam(name = "size", defaultValue = "10") int size) {

		Page<AnswerDto> answerDtos = answerService.getByInquiryAnswerOrderByVotes(inquiryNo, page, size);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_ANSWER_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_ANSWER_LIST_SUCCESS);
		response.setData(answerDtos);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);

		return responseEntity;

	}

	/* 질문 하나에 달린 답변 */
	/* 최신순 */
	@Operation(summary = "질문에 작성된답변조회(최신순)")
	@GetMapping("/{inquiryNo}/answer-date")
	public ResponseEntity<Response> getByInquiryAnswerOrderByDate(@PathVariable(name = "inquiryNo") Long inquiryNo,
			@RequestParam(name = "page", defaultValue = "0") int page, // 기본값은 0 페이지
			@RequestParam(name = "size", defaultValue = "10") int size) {

		Page<AnswerDto> answerDtos = answerService.getByInquiryAnswerOrderByDate(inquiryNo, page, size);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_ANSWER_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_ANSWER_LIST_SUCCESS);
		response.setData(answerDtos);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);

		return responseEntity;
	}

	/* 카테고리별 답변 리스트 */
	/* 추천순 */
	@Operation(summary = "카테고리별 답변조회(추천순)")
	@GetMapping("/{categoryNo}/category-vote")
	public ResponseEntity<Response> getByCategoryAnswerOrderByVotes(@PathVariable(name = "categoryNo") Long categoryNo,
			@RequestParam(name = "page", defaultValue = "0") int page, // 기본값은 0 페이지
			@RequestParam(name = "size", defaultValue = "10") int size) {

		Page<AnswerDto> answerDtos = answerService.getByCategoryAnswerOrderByDate(categoryNo, page, size);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_ANSWER_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_ANSWER_LIST_SUCCESS);
		response.setData(answerDtos);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);

		return responseEntity;
	}

	/* 카테고리별 답변 리스트 */
	/* 최신순 */
	@Operation(summary = "카테고리별 답변조회(최신순)")
	@GetMapping("/{categoryNo}/category-date")
	public ResponseEntity<Response> getByCategoryAnswerOrderByDate(@PathVariable(name = "categoryNo") Long categoryNo,
			@RequestParam(name = "page", defaultValue = "0") int page, // 기본값은 0 페이지
			@RequestParam(name = "size", defaultValue = "10") int size) {

		Page<AnswerDto> answerDtos = answerService.getByCategoryAnswerOrderByDate(categoryNo, page, size);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_ANSWER_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_ANSWER_LIST_SUCCESS);
		response.setData(answerDtos);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);

		return responseEntity;
	}

	/* 최근 3일동안 추천 많이 받은 답변 리스트 */
	@Operation(summary = "최근 3일간 추천 많이 받은 답변 리스트")
	@GetMapping("/recently-vote")
	public ResponseEntity<Response> getByAnswerOrderByVoteDate(
			@RequestParam(name = "page", defaultValue = "0") int page, // 기본값은 0 페이지
			@RequestParam(name = "size", defaultValue = "10") int size) {

		Page<AnswerDto> answerDtos = answerService.getByAnswerOrderByVoteDate(page, size);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_ANSWER_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_ANSWER_LIST_SUCCESS);
		response.setData(answerDtos);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);

		return responseEntity;
	}

	/* 내가 작성한 답변내역 */
	@Operation(summary = "내가 작성한 답변내역")
	@SecurityRequirement(name = "BearerAuth") // API 엔드포인트가 인증을 요구한다는 것을 문서화(Swagger에서 JWT인증을 명시
	@PreAuthorize("hasRole('MENTEE') or hasRole('MENTOR')") // ROLE이 MENTEE인 사람만 접근 가능
	@GetMapping
	public ResponseEntity<Response> getAnswerByMember(Authentication authentication,
			@RequestParam(name = "page", defaultValue = "0") int page, // 기본값은 0 페이지
			@RequestParam(name = "size", defaultValue = "10") int size) {

		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		Long memberNo = principalDetails.getMemberNo();

		Page<AnswerDto> answerDtos = answerService.getAnswerByMember(memberNo, page, size);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_ANSWER_LIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_ANSWER_LIST_SUCCESS);
		response.setData(answerDtos);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);

		return responseEntity;

	}

	// 한 질문의 답변 수
	@Operation(summary = "한 질문의 답변 수")
	@GetMapping("/count-answer/{inquiryNo}")
	public ResponseEntity<Response> getAnswerCount(@PathVariable(name = "inquiryNo") Long inquiryNo) {
		Long answerCount = answerService.getAnswerCount(inquiryNo);
		Response response = new Response();
		response.setData(answerCount);
		response.setStatus(ResponseStatusCode.READ_ANSWER_SUCCESS);
		response.setMessage(ResponseMessage.READ_ANSWER_SUCCESS);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);

		return responseEntity;
	}

}