package com.itwill.jpa.controller.member_information;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.member_information.CategoryRequestDto;
import com.itwill.jpa.dto.member_information.CategoryResponseDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.response.Response;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.service.member_information.CategoryService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/category")
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;

	@Operation(summary = "카테고리 등록")
	@PostMapping
	public ResponseEntity<Response> createCategory(@RequestBody CategoryRequestDto categoryDto) {
		categoryService.createCategory(categoryDto);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.CREATE_CATEGORY_SUCCESS);
		response.setMessage(ResponseMessage.CREATE_CATEGORY_SUCCESS);
		response.setData(categoryDto);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		return new ResponseEntity<Response>(response, headers, HttpStatus.CREATED);
	}

	@Operation(summary = "카테고리 수정")
	@PutMapping
	public ResponseEntity<Response> updateCategory(@RequestBody CategoryRequestDto categoryDto) {
		categoryService.updateCategory(categoryDto);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.UPDATE_CATEGORY_SUCCESS);
		response.setMessage(ResponseMessage.UPDATE_CATEGORY_SUCCESS);
		response.setData(categoryDto);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
	}

	@Operation(summary = "카테고리별 항목 출력")
	@GetMapping("/{categoryNo}")
	public ResponseEntity<Response> getCategoriesBycategoryNo(@PathVariable(name = "categoryNo") Long categoryNo) {
		CategoryResponseDto category = categoryService.getCategoriesBycategoryNo(categoryNo);

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_CATEGORYLIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_CATEGORYLIST_SUCCESS);
		response.setData(category);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
	}

	@Operation(summary = "카테고리 리스트 출력")
	@GetMapping()
	public ResponseEntity<Response> getCategories() {
		List<CategoryResponseDto> categories = categoryService.getCategories();

		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_CATEGORYLIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_CATEGORYLIST_SUCCESS);
		response.setData(categories);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
	}
	@Operation(summary = "카테고리 넘버로 카테고리 뽑기")
	@GetMapping("get/{categoryNo}")
	public ResponseEntity<Response> getCategory(@PathVariable(name = "categoryNo") Long categoryNo) {
		CategoryResponseDto category = categoryService.getCategroyNo(categoryNo);
		Response response = new Response();
		response.setStatus(ResponseStatusCode.READ_CATEGORYLIST_SUCCESS);
		response.setMessage(ResponseMessage.READ_CATEGORYLIST_SUCCESS);
		response.setData(category);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

		return new ResponseEntity<Response>(response, headers, HttpStatus.OK);
	}
}