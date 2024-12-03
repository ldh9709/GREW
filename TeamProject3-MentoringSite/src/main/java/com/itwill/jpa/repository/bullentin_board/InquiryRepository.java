package com.itwill.jpa.repository.bullentin_board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.bullentin_board.Inquiry;
@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long>{

}
