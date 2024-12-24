import React, { useState } from 'react'
import { useMemberAuth } from "../../util/AuthContext"
import "../../css/report.css"

const ReportModal = ({ onClose = () => { }, onSubmit, report }) => {
    const { token, member } = useMemberAuth();
    const [reportData, setReportData] = useState({
        type: report.type,
        target: report.target,
        reason: '',
        content: '',
        memberNo: member.memberNo
    }) //신고 데이터 
  
    const handleReasonChange = (e) => {
        setReportData(() => ({
            reason:e.target.value
      }))
    };
  
    const handleContentChange = (e) => {
        setReportData(() => ({
            content: e.target.value
        }))
    };
  
    const handleSubmit = () => {
      if (!reportData.reason || !reportData.content.trim()) {
        alert("신고 사유와 내용을 모두 입력해주세요.");
        return;
      }
      onSubmit({ reportData });
      onClose(); // 모달 닫기
    };

    return (
      <div className="report-modal-backdrop">
        <div className="report-modal">
          <h2 className="report-title">신고하기</h2>
          <div className="report-form">
            <select
              className="report-select"
              value={reportData.reason}
              onChange={handleReasonChange}
            >
              <option value="">사유를 선택하세요</option>
              <option value="1">욕설/비속어</option>
              <option value="2">스팸 및 광고</option>
              <option value="3">성적인 내용</option>
              <option value="4">폭력적인 내용</option>
              <option value="5">개인정보 유출</option>
              <option value="6">기타</option>
            </select>
            <textarea
              className="report-textarea"
              placeholder="신고 내용을 작성해주세요..."
              value={reportData.content}
              onChange={handleContentChange}
            />
            <div className="report-buttons">
              <button className="cancel-button" onClick={onClose}>
                취소하기
              </button>
              <button className="submit-button" onClick={handleSubmit}>
                신고하기
              </button>
            </div>
          </div>
        </div>
      </div>
    );
  };
  
  export default ReportModal;