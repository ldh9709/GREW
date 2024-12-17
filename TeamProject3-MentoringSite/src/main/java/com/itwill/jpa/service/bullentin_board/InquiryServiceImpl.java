package com.itwill.jpa.service.bullentin_board;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.dto.bulletin_board.InquiryIpViewDto;
import com.itwill.jpa.entity.bullentin_board.Inquiry;
import com.itwill.jpa.entity.bullentin_board.InquiryIpView;
import com.itwill.jpa.exception.CustomException;
import com.itwill.jpa.repository.bullentin_board.InquiryIpViewRepository;
import com.itwill.jpa.repository.bullentin_board.InquiryRepository;
import com.itwill.jpa.response.ResponseMessage;
import com.itwill.jpa.response.ResponseStatusCode;
import com.itwill.jpa.util.ClientIp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class InquiryServiceImpl implements InquiryService {
	@Autowired
	private InquiryRepository inquiryRepository;
	@Autowired
	private InquiryIpViewRepository inquiryIpViewRepository; // Repository를 통해 조회 기록을 관리

	private final ClientIp clientIpUtil = new ClientIp();// ip
	
	// 질문등록
	@Override
	public InquiryDto createInquiry(InquiryDto inquiryDto) {
	    try {
	    	Inquiry inquiry = Inquiry.toEntity(inquiryDto);
	        return InquiryDto.toDto(inquiryRepository.save(inquiry));
	    } catch (Exception e) {
	        throw new CustomException(ResponseStatusCode.CREATED_INQUIRY_FAIL, ResponseMessage.CREATED_INQUIRY_FAIL, e);
	    }
	}

	// 질문수정
	@Override
	public InquiryDto updateInquiry(InquiryDto inquiryDto) {
		try {
			//질문이 존재하는지 확인
			Inquiry inquiry = inquiryRepository.findById(inquiryDto.getInquiryNo()).get();
			
			inquiry.setInquiryTitle(inquiryDto.getInquiryTitle());
			inquiry.setInquiryContent(inquiryDto.getInquiryContent());
			inquiry.setInquiryDate(LocalDateTime.now());
			return InquiryDto.toDto(inquiryRepository.save(inquiry));
		}catch (Exception e) {
			throw new CustomException(ResponseStatusCode.UPDATE_INQUIRY_FAIL, ResponseMessage.UPDATE_INQUIRY_FAIL, e);
		}
	}

	// 질문삭제
	@Override
	public InquiryDto deleteInquiry(Long inquiryNo) {
		try {
			//질문이 존재하는지 확인
			Inquiry inquiry = inquiryRepository.findById(inquiryNo).get();
			
			inquiry.setInquiryStatus(2); // 삭제 상태로 변경
			return InquiryDto.toDto(inquiryRepository.save(inquiry));
		}catch (Exception e) {
			throw new CustomException(ResponseStatusCode.DELETE_INQUIRY_FAIL, ResponseMessage.DELETE_INQUIRY_FAIL, e);
		}
	}

	// 질문보기
	@Override
	public InquiryDto getInquiry(Long InquiryNo) {
		try {
			return InquiryDto.toDto(inquiryRepository.findById(InquiryNo).get());
		}catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_INQUIRY_FAIL, ResponseMessage.READ_INQUIRY_FAIL, e);
		}
	}

	// 조회수 증가 제한: IP별로 일정 시간 내에 조회수 증가 제한
	@Override
	public InquiryDto increaseViewInquiry(Long inquiryNo, String ipAddress) {
		try {
			// Inquiry 조회 질문이 없으면 예외처리
			Inquiry inquiry = inquiryRepository.findById(inquiryNo).get();
	
			// IP 조회 기록을 DB에서 확인
			InquiryIpView lastView = inquiryIpViewRepository.findByIpAddressAndInquiry_InquiryNo(ipAddress, inquiryNo);
	
			long currentTime = System.currentTimeMillis();
			long lastViewTime = lastView != null
					? lastView.getLastViewed().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
					: 0;
	
			// 조회수 증가 제한 시간 (1시간)
			if (lastViewTime != 0 && currentTime - lastViewTime < 30 * 60 * 1000) {
				System.out.println("제한시간이 지나지않음");
			} else {
				// 조회수 증가
				inquiry.setInquiryViews(inquiry.getInquiryViews() + 1);
				inquiryRepository.save(inquiry);
	
				// IP 조회 기록 업데이트
				InquiryIpViewDto updatedIpView = new InquiryIpViewDto(null, ipAddress, inquiryNo, LocalDateTime.now());
				inquiryIpViewRepository.save(InquiryIpView.toEntity(updatedIpView));
			}
	
			// DTO로 변환하여 반환
			return InquiryDto.toDto(inquiry);
		}catch (Exception e) {
			throw new CustomException(ResponseStatusCode.INCREASE_VIEW_INQUIRY_FAIL, ResponseMessage.INCREASE_VIEW_INQUIRY_FAIL, e);
		}
	}

	/** 카테고리별 **/
	// 답변갯수순
	@Override
	public Page<InquiryDto> getByCategoryInquiryOrderByAnswer(Long categoryNo, int pageNumber, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<Inquiry> inquiryEntityList = inquiryRepository.findByCategoryInquiryOrderByAnswer(categoryNo, pageable);
			List<InquiryDto> inquiryDtoList = new ArrayList<>();
			for (Inquiry inquiryEntity : inquiryEntityList) {
				inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
			}
			return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_INQUIRY_LIST_FAIL, ResponseMessage.READ_INQUIRY_LIST_FAIL, e);
		}
	}
	// 답변갯수순(대분류)
	@Override
	public Page<InquiryDto> getByParentCategoryInquiryOrderByAnswer(Long categoryNo, int pageNumber, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<Inquiry> inquiryEntityList = inquiryRepository.findByParentCategoryInquiryOrderByAnswer(categoryNo, pageable);
			List<InquiryDto> inquiryDtoList = new ArrayList<>();
			for (Inquiry inquiryEntity : inquiryEntityList) {
				inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
			}
			return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_INQUIRY_LIST_FAIL, ResponseMessage.READ_INQUIRY_LIST_FAIL, e);
		}
	}

	// 조회순
	@Override
	public Page<InquiryDto> getByCategoryInquiryOrderByView(Long categoryNo, int pageNumber, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<Inquiry> inquiryEntityList = inquiryRepository.findByCategoryInquiryOrderByView(categoryNo, pageable);
			List<InquiryDto> inquiryDtoList = new ArrayList<>();
			for (Inquiry inquiryEntity : inquiryEntityList) {
				inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
			}
			return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
		}catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_INQUIRY_LIST_FAIL, ResponseMessage.READ_INQUIRY_LIST_FAIL, e);
		}
	}
	// 조회순(카테고리대분류)
	@Override
	public Page<InquiryDto> getByParentCategoryInquiryOrderByView(Long categoryNo, int pageNumber, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<Inquiry> inquiryEntityList = inquiryRepository.findByParentCategoryInquiryOrderByView(categoryNo, pageable);
			List<InquiryDto> inquiryDtoList = new ArrayList<>();
			for (Inquiry inquiryEntity : inquiryEntityList) {
				inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
			}
			return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_INQUIRY_LIST_FAIL, ResponseMessage.READ_INQUIRY_LIST_FAIL, e);
		}
	}

	// 최신순
	@Override
	public Page<InquiryDto> getByCategoryInquiryOrderByDate(Long categoryNo, int pageNumber, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<Inquiry> inquiryEntityList = inquiryRepository.findByCategoryInquiryOrderByDate(categoryNo, pageable);
			List<InquiryDto> inquiryDtoList = new ArrayList<>();
			for (Inquiry inquiryEntity : inquiryEntityList) {
				inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
			}
			return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
		}catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_INQUIRY_LIST_FAIL, ResponseMessage.READ_INQUIRY_LIST_FAIL, e);
		}
	}
	
	// 최신순(대분류)
	@Override
	public Page<InquiryDto> getByParentCategoryInquiryOrderByDate(Long categoryNo, int pageNumber, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<Inquiry> inquiryEntityList = inquiryRepository.findByParentCategoryInquiryOrderByDate(categoryNo, pageable);
			List<InquiryDto> inquiryDtoList = new ArrayList<>();
			for (Inquiry inquiryEntity : inquiryEntityList) {
				inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
			}
			return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
		} catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_INQUIRY_LIST_FAIL, ResponseMessage.READ_INQUIRY_LIST_FAIL, e);
		}
	}

	/** 전체질문 **/
	// 답변갯수순
	@Override
	public Page<InquiryDto> getByAllInquiryOrderByAnswer(int pageNumber, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<Inquiry> inquiryEntityList = inquiryRepository.findAllInquiriOrderByAnswer(pageable);
			List<InquiryDto> inquiryDtoList = new ArrayList<>();
			for (Inquiry inquiryEntity : inquiryEntityList) {
				inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
			}
			return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
		}catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_INQUIRY_LIST_FAIL, ResponseMessage.READ_INQUIRY_LIST_FAIL, e);
		}
	}

	// 조회순
	@Override
	public Page<InquiryDto> getByAllInquiryOrderByView(int pageNumber, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<Inquiry> inquiryEntityList = inquiryRepository.findAllInquiryOrderByView(pageable);
			List<InquiryDto> inquiryDtoList = new ArrayList<>();
			for (Inquiry inquiryEntity : inquiryEntityList) {
				inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
			}
	
			// PageImpl을 사용해 Page<InquiryDto> 반환
			return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
		}catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_INQUIRY_LIST_FAIL, ResponseMessage.READ_INQUIRY_LIST_FAIL, e);
		}
	}

	// 최신순
	@Override
	public Page<InquiryDto> getByAllInquiryOrderByDate(int pageNumber, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<Inquiry> inquiryEntityList = inquiryRepository.findAllInquiryOrderByDate(pageable);
			List<InquiryDto> inquiryDtoList = new ArrayList<>();
			for (Inquiry inquiryEntity : inquiryEntityList) {
				inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
			}
	
			// PageImpl을 사용해 Page<InquiryDto> 반환
			return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
		}catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_INQUIRY_LIST_FAIL, ResponseMessage.READ_INQUIRY_LIST_FAIL, e);
		} 
	}

	// 검색기능
	@Override
	public Page<InquiryDto> getInquiryBySearch(String search, int pageNumber, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<Inquiry> inquiryEntityList = inquiryRepository.findInquiryBySearch(search, pageable);
			List<InquiryDto> inquiryDtoList = new ArrayList<>();
			for (Inquiry inquiryEntity : inquiryEntityList) {
				inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
			}
			return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
		}catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_INQUIRY_LIST_FAIL, ResponseMessage.READ_INQUIRY_LIST_FAIL, e);
		}
	}

	// ip중복제거
	@Override
	public String ipcheck(HttpServletRequest httpServletRequest) {
		try {
			String clientIp = clientIpUtil.getClientIp(httpServletRequest);
			return clientIp;
		}catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_IPCHECK_FAIL, ResponseMessage.READ_IPCHECK_FAIL, e);
		}
	}

	// 내가 쓴 질문리스트 출력
	@Override
	public Page<InquiryDto> getInquiryByMember(Long MemberNo, int pageNumber, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<Inquiry> inquiryEntityList = inquiryRepository.findByMemberMemberNoOrderByInquiryDateDesc(MemberNo,
					pageable);
			List<InquiryDto> inquiryDtoList = new ArrayList<>();
			for (Inquiry inquiryEntity : inquiryEntityList) {
				inquiryDtoList.add(InquiryDto.toDto(inquiryEntity));
			}
			return new PageImpl<>(inquiryDtoList, pageable, inquiryEntityList.getTotalElements());
		}catch (Exception e) {
			throw new CustomException(ResponseStatusCode.READ_INQUIRY_LIST_FAIL, ResponseMessage.READ_INQUIRY_LIST_FAIL, e);
		}
	}
	//번호로 질문 DTO 찾기
	@Override
	public InquiryDto getInquiryByInquiryNo(Long inquiryNo) {
		InquiryDto inquiryDto = InquiryDto.toDto(inquiryRepository.findById(inquiryNo).get()); 
		return inquiryDto;
	}

}
