package com.itwill.jpa.dto.bulletin_board;



import java.time.LocalDate;

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
    private LocalDate inquiryDate;
    private Integer inquiryStatus;
    private Integer inquiryViews;
    
    private CategoryDto category;
    private MemberDto member;

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
                .category(CategoryDto.toDto( inquiryEntity.getCategory()))
                .member(MemberDto.toDto(inquiryEntity.getMember()))
                .build();
    }
}