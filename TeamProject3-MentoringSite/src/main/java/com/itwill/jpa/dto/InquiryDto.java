package com.itwill.jpa.dto;



import java.time.LocalDate;

import com.itwill.jpa.entity.Inquiry;

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
    private String inquiryStatus;
    private int inquiryViews;
    
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