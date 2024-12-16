package com.itwill.jpa.dto.bulletin_board;

import java.time.LocalDateTime;

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
    private Long parentsCategoryNo;
    private Long categoryNo;
    private Long memberNo;
    private String memberName;
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
                .parentsCategoryNo(inquiryEntity.getCategory().getParentCategory().getCategoryNo())
                .categoryNo(inquiryEntity.getCategory().getCategoryNo())
                .memberNo(inquiryEntity.getMember().getMemberNo())
                .memberName(inquiryEntity.getMember().getMemberName())
                .build();
    }
}