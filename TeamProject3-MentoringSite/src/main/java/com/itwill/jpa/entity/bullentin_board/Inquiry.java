package com.itwill.jpa.entity.bullentin_board;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.itwill.jpa.dto.bulletin_board.InquiryDto;
import com.itwill.jpa.entity.user_information.Category;
import com.itwill.jpa.entity.user_information.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inquiry_no_SEQ")
    @SequenceGenerator(name = "inquiry_no_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "inquiry_no")
    private Long inquiryNo;

    @Column(name = "inquiry_title", nullable = false)
    private String inquiryTitle;

    @Column(name = "inquiry_content", nullable = false, length = 500)
    private String inquiryContent = "";
    
    @Column(name = "inquiry_date", nullable = false)
    private LocalDate inquiryDate = LocalDate.now();

    @Column(name = "inquiry_status", nullable = false)
    private Integer inquiryStatus = 1;  // 1 or 2

    @Column(name = "inquiry_views", nullable = false)
    private Integer inquiryViews = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no")
    private Category category;  // FK 연관 관계 (Category 엔티티)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;  // FK 연관 관계 (User 엔티티)

   
    /* 초기값 설정 */
    public void setDefaultValues() {
    	if(this.inquiryContent==null) this.inquiryContent = "";
    	if(this.inquiryDate==null) this.inquiryDate = LocalDate.now();
    	if(this.inquiryStatus == null) this.inquiryStatus = 1;
    	if(this.inquiryViews == null) this.inquiryViews = 0;
    }
    
    
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
    
    
    /* 한 개의 질문당 여러개의 답변 보유 가능 */
    @OneToMany(mappedBy = "inquiry", fetch = FetchType.LAZY)
    private List<Answer> answers; 
    
    
    
}