package com.itwill.jpa.entity.report;

import java.time.LocalDateTime;

import com.itwill.jpa.dto.report.ReportDto;
import com.itwill.jpa.entity.member_information.Member;

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
	
	@Column(name="report_type", nullable = false)
	private String reportType;
	
	@Column(name="report_target", nullable = false)
	private Long reportTarget;
	
	@Column(name="report_reason", nullable = false)
	private Integer reportReason;
	
	@Column(name = "report_content", length = 300, nullable = false)
	private String reportContent;
	
	@Column(name="report_date", nullable = false)
	private LocalDateTime reportDate;
	
	@Column(name="resolved_date", nullable = false)
	private LocalDateTime resolvedDate;
	
	@Column(name="report_status", nullable = false)
	private Integer reportStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_no", nullable = false)
	private Member member;
	
	/* 초기값 설정 */
    @PrePersist
    public void setDefaultValues() {
    	if (this.reportContent == null) this.reportContent ="";
        if (this.reportDate == null) this.reportDate = LocalDateTime.now();
        if (this.resolvedDate == null) this.resolvedDate = LocalDateTime.now();
        if (this.reportStatus == 0 || this.reportStatus == null) this.reportStatus = 1;
    }
	
	
	public static Report toEntity(ReportDto dto) {
		Member member = Member.builder()
				.memberNo(dto.getMemberNo())
				.build();
		
		return Report.builder()
				.reportNo(dto.getReportNo())
				.reportType(dto.getReportType())
				.reportTarget(dto.getReportTarget())
				.reportReason(dto.getReportReason())
				.reportContent(dto.getReportContent())
				.reportDate(dto.getReportDate())
				.resolvedDate(dto.getResolvedDate())
				.reportStatus(dto.getReportStatus())
				.member(member)
				.build();
	}
}

