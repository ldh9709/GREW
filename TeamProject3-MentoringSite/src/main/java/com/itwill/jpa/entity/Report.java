package com.itwill.jpa.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.itwill.jpa.dto.ReportRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Report")
public class Report {
	@Id
	@SequenceGenerator(name = "report_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_seq")
	private Long reportNo;
	
	private String reportType;
	private Integer reportTarget;
	private Integer reportReason;
	@Column(length = 300)
	private String reportContent = "";
	private LocalDateTime reportDate = LocalDateTime.now();
	private LocalDateTime resolvedDate;
	private Integer reportStatus = 1;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_no")
	private Member member;
	
	/* 초기값 설정 */
    @PrePersist
    public void setDefaultValues() {
    	if (this.reportContent == null) this.reportContent ="";
        if (this.reportDate == null) this.reportDate = LocalDateTime.now();
        if (this.resolvedDate == null) this.resolvedDate = LocalDateTime.now();
        if (this.reportStatus == null) this.reportStatus = 1;
    }
	
	
	public static ReportRequestDto toDto(Report entity) {
		return ReportRequestDto.builder()
				.reportNo(entity.getReportNo())
				.reportType(entity.getReportType())
				.reportTarget(entity.getReportTarget())
				.reportReason(entity.getReportReason())
				.reportContent(entity.getReportContent())
				.memberNo(entity.member.getMemberNo())
				.build();
	}
}

