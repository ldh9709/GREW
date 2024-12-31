import { useMemberAuth } from "../../util/AuthContext";
import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faXmark } from '@fortawesome/free-solid-svg-icons'
import * as adminApi from '../../api/adminApi';
export default function AdminMemberDetail({mentor, onClose}) {

    const handleCancel = () => {    
        onClose();
    }

  return (
    <div className="mentor-detail-container">
        <div className='cancel-button' onClick={handleCancel}>
            <FontAwesomeIcon icon={faXmark}/>
        </div>
        <h2 className="mentor-title">멘토 접수 내용</h2>
        <div className="mentor-details">
            <div className="mentor-row">
                <span className="mentor-label">상태</span>
                <span className="mentor-value status"></span>
            </div>
            <div className="mentor-row">
                <span className="mentor-label">신고일자</span>
                <span className="mentor-value"></span>
            </div>
            <div className="mentor-row">
                <span className="mentor-label">처리일자</span>
                <span className="mentor-value"></span>
            </div>
            <div className="mentor-row">
                <span className="mentor-label">유형</span>
                <span className="mentor-value"></span>
            </div>
            <div className="mentor-row">
                <span className="mentor-label">사유</span>
                <span className="mentor-value"></span>
            </div>
            <div className="mentor-detail-content">
                <span className="mentor-label">내용</span>
                <textarea
                className="admin-member-textarea"
                readOnly
                />
            </div>
        </div>
        <div className='mentor-detail-button'>
            <button className="to-resolved"
            >승인</button>
            <button className="false-report"
            >거절</button>
        </div>
  </div>
  )
}
