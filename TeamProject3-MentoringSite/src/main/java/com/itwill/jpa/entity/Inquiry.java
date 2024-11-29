package com.itwill.jpa.entity;

import java.time.LocalDate;import com.itwill.jpa.dto.CategoryDto;
import com.itwill.jpa.dto.InquiryDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inquiry")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inquiry_seq")
    @SequenceGenerator(name = "inquiry_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "inquiry_no")
    private Long inquiryNo;

    @Column(name = "inquiry_title", nullable = false)
    private String inquiryTitle;

    @Column(name = "inquiry_content", nullable = false)
    private String inquiryContent;

    @Column(name = "inquiry_date", nullable = false)
    private LocalDate inquiryDate;

    @Column(name = "inquiry_status", nullable = false)
    private String inquiryStatus;  // "active" or "inactive"

    @Column(name = "inquiry_views", nullable = false)
    private int inquiryViews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no")
    private Category category;  // FK 연관 관계 (Category 엔티티)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;  // FK 연관 관계 (User 엔티티)

    // getters and setters
    
    /*
     * DTO -> Entitiy
     */
    public static Inquiry toEntity(InquiryDto inquiryDto) {
	    return Inquiry.builder()
	            .inquiryNo(inquiryDto.getInquiryNo())
	            .inquiryTitle(inquiryDto.getInquiryTitle())
	            .inquiryContent(inquiryDto.getInquiryContent())
	            .inquiryDate(inquiryDto.getInquiryDate())
	            .inquiryStatus(inquiryDto.getInquiryStatus())
	            .inquiryViews(inquiryDto.getInquiryViews())
	            .category(Category.toEntity(inquiryDto.getCategory()))
	            .member(Member.toEntity(inquiryDto.getMember()))
	            .build();
	}
    
}