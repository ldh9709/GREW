package com.itwill.jpa.entity.bullentin_board;

import java.time.LocalDateTime;
import java.util.List;


import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.entity.member_information.Category;
import com.itwill.jpa.entity.member_information.Member;
import com.itwill.jpa.repository.member_information.CategoryRepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
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
	
	static CategoryRepository categoryRepository;
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inquiry_no_SEQ")
    @SequenceGenerator(name = "inquiry_no_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "inquiry_no")
    private Long inquiryNo;

    
    @Column(name = "inquiry_title")
    private String inquiryTitle;
    
    @Column(name = "inquiry_content" ,length = 4000)
    private String inquiryContent;
    
    @Column(name = "inquiry_date")
    private LocalDateTime inquiryDate;
    
    @Column(name = "inquiry_status")
    private Integer inquiryStatus;  // 1 or 2

    @Column(name = "inquiry_views")
    private Integer inquiryViews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no")
    private Category category;  // FK 연관 관계 (Category 엔티티)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;  // FK 연관 관계 (User 엔티티)

    /* 한 개의 질문당 여러개의 답변 보유 가능 */
    @OneToMany(mappedBy = "inquiry", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> answers; 
    
    
    /*
     * DTO -> Entity
     */
    public static Inquiry toEntity(InquiryDto inquiryDto) {
    	
	    return Inquiry.builder()
	            .inquiryNo(inquiryDto.getInquiryNo())
	            .inquiryTitle(inquiryDto.getInquiryTitle())
	            .inquiryContent(inquiryDto.getInquiryContent())
	            .inquiryDate(inquiryDto.getInquiryDate())
	            .inquiryStatus(inquiryDto.getInquiryStatus())
	            .inquiryViews(inquiryDto.getInquiryViews())
	            .category(Category.builder()
	            		.categoryNo(inquiryDto.getCategoryNo())
	            		.categoryName(inquiryDto.getCategoryName())
	            		.build())
	            .member(Member.builder().memberNo(inquiryDto.getMemberNo()).build())
	            .build();
	}
    /* 초기값 설정 */
    @PrePersist
    public void setDefaultValues() {
    	if(this.inquiryContent==null) this.inquiryContent = "";
    	if(this.inquiryDate==null) this.inquiryDate = LocalDateTime.now();
    	if(this.inquiryStatus == null||this.inquiryStatus == 0) this.inquiryStatus = 1;
    	if(this.inquiryViews == null) this.inquiryViews = 0;
    }
    
    
    
   
    
    
    
}