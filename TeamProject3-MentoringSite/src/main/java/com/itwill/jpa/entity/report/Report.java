package com.itwill.jpa.entity.report;

import java.time.LocalDateTime;

import com.itwill.jpa.dto.report.ReportDto;
import com.itwill.jpa.entity.user_information.Member;

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
@Table(name = "report")
public class Report {
	@Id
	@SequenceGenerator(name = "report_no_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_no_SEQ")
	@Column(name="report_no")
	private Long reportNo;
	
	@Column(name="report_type")
	private String reportType;
	
	@Column(name="report_target")
	private Integer reportTarget;
	
	@Column(name="report_reason")
	private Integer reportReason;
	
	@Column(name = "report_content", length = 300)
	private String reportContent;
	
	@Column(name="report_date")
	private LocalDateTime reportDate;
	
	@Column(name="resolved_date")
	private LocalDateTime resolvedDate;
	
	@Column(name="report_status")
	private Integer reportStatus;

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
	
	
	public static ReportDto toDto(Report entity) {
		return ReportDto.builder()
				.reportNo(entity.getReportNo())
				.reportType(entity.getReportType())
				.reportTarget(entity.getReportTarget())
				.reportReason(entity.getReportReason())
				.reportContent(entity.getReportContent())
				.reportDate(entity.getReportDate())
				.resolveDate(entity.getResolvedDate())
				.reportStatus(entity.getReportStatus())
				.memberNo(entity.member.getMemberNo())
				.build();
	}
}

