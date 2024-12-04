package com.itwill.jpa.dto.bulletin_board;



import java.time.LocalDateTime;

import com.itwill.jpa.dto.member_information.CategoryDto;
import com.itwill.jpa.dto.member_information.MemberDto;
import com.itwill.jpa.entity.bullentin_board.Inquiry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDto {

    private Long inquiryNo;
    private String inquiryTitle;
    private String inquiryContent;
    private LocalDateTime inquiryDate;
    private Integer inquiryStatus;
    private Integer inquiryViews;
    
    private Long category;
    private Long member;

    /*
     * Entity -> DTO 변환 메소드
     */
    public static InquiryDto toDto(Inquiry inquiryEntity) {

        return InquiryDto.builder()
                .inquiryNo(inquiryEntity.getInquiryNo())
                .inquiryTitle(inquiryEntity.getInquiryTitle())
                .inquiryContent(inquiryEntity.getInquiryContent())
                .inquiryDate(inquiryEntity.getInquiryDate())
                .inquiryStatus(inquiryEntity.getInquiryStatus())
                .inquiryViews(inquiryEntity.getInquiryViews())
                .category(inquiryEntity.getCategory().getCategoryNo())
                .member(inquiryEntity.getMember().getMemberNo())
                .build();
    }
}