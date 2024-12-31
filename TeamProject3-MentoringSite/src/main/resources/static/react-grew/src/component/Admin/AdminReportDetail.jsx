import { useMemberAuth } from "../../util/AuthContext";
import React from 'react'
import * as reportUtil from '../../util/reportUtil'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faXmark } from '@fortawesome/free-solid-svg-icons'
import * as adminApi from '../../api/adminApi';
export default function AdminReportDetail({report, onClose, fetchReports}) {
    const { token } = useMemberAuth();

    // 신고 상태 업데이트 핸들러
    const handleStatusUpdate = async (reportNo, status) => {
        try {
            let confirmation;
            status === 2 ? ( confirmation = window.confirm('신고 처리 하시겠습니까?')) : (confirmation = window.confirm('무고 처리 하시겠습니까?'))
            if(confirmation){
                const response = await adminApi.updateReportStatusForAdmin(token, reportNo, status);
                alert(`신고 상태가 '${reportUtil.reportStatus(status)}'로 변경되었습니다.`);
                fetchReports();
                onClose();
            }
        } catch (error) {
            console.error("신고 상태 업데이트 실패", error);
            alert("상태 업데이트에 실패했습니다.");
        }
    };

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
                className="admin-report-textarea"
                value={report.reportContent}
                readOnly
                />
            </div>
        </div>
        <div className='report-detail-button'>
        {report.reportStatus !== 1 ? (
            <div>
                <button className="checked"
                >처리</button>
                <button className="checked"
                >무고</button>
            </div>
            ) : (
            <div>
                <button className="to-resolved"
                onClick={() => handleStatusUpdate(report.reportNo, 2)}
                >처리</button>
                <button className="false-report"
                onClick={() => handleStatusUpdate(report.reportNo, 3)}
                >무고</button>
            </div>
            )}
        </div>
  </div>
  )
}
