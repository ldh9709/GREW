package com.itwill.jpa.repository.bullentin_board;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.bullentin_board.InquiryIpView;

public interface InquiryIpViewRepository extends JpaRepository<InquiryIpView,Long>{

	InquiryIpView findByIpAddressAndInquiry_InquiryNo(String ipAddress, Long inquiryNo);

}
