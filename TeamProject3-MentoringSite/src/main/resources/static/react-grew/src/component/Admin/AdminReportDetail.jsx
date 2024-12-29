import React from 'react'
import * as reportUtil from '../../util/reportUtil'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faXmark } from '@fortawesome/free-solid-svg-icons'
export default function AdminReportDetail({report, onClose}) {

    const handleStatusUpdate = () => {

    }

    const handleCancel = () => {    
        onClose();
    }

  return (
    <div className="report-detail-container">
        <div className='cancel-button' onClick={handleCancel}>
            <FontAwesomeIcon icon={faXmark}/>
        </div>
        <h2 className="report-title">신고 내용</h2>
        <div className="report-details">
            <div className="report-row">
                <span className="report-label">상태</span>
                <span className="report-value status">{reportUtil.reportStatus(report.reportStatus)}</span>
            </div>
            <div className="report-row">
                <span className="report-label">신고일자</span>
                <span className="report-value">{report.reportDate.substring(0,10)}</span>
            </div>
            <div className="report-row">
                <span className="report-label">처리일자</span>
                <span className="report-value">{report.reportDate===report.resolvedDate ? " - " : report.resolvedDate.substring(0,10)}</span>
            </div>
            <div className="report-row">
                <span className="report-label">유형</span>
                <span className="report-value">{reportUtil.typeName(report.reportType)}</span>
            </div>
            <div className="report-row">
                <span className="report-label">사유</span>
                <span className="report-value">{reportUtil.reasonName(report.reportReason)}</span>
            </div>
            <div className="report-detail-content">
                <span className="report-label">내용</span>
                <textarea
                className="report-textarea"
                value={report.reportContent}
                readOnly
                />
            </div>
        </div>
        <div className='report-detail-button'>
            <button
            className='in-progress'
            onClick={() => handleStatusUpdate(report.id, "IN_PROGRESS")}
            >처리</button>
            <button
            className='false-report'
            onClick={() => handleStatusUpdate(report.id, "IN_PROGRESS")}
            >무고</button>
        </div>
  </div>
  )
}
