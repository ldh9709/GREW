import { useMemberAuth } from "../../util/AuthContext";
import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faXmark } from '@fortawesome/free-solid-svg-icons'
import * as adminApi from '../../api/adminApi';
export default function AdminMemberDetail({mentor, onClose}) {

    console.log(mentor)
    const handleCancel = () => {    
        onClose();
    }

  return (
        <div className="mentor-detail-container">
            <h2 className="mentor-title">멘토 신청 내역</h2>
            <div className="mentor-details">
                <div className="mentor-row">
                    <span className="mentor-label">전문 분야</span>
                    <span className="mentor-value">직무상담 &gt; 인사/총무/노무</span>
                </div>
                <div className="mentor-row">
                    <span className="mentor-label">프로필 사진</span>
                    <div className="mentor-profile-pic"></div>
                </div>
                <div className="mentor-row">
                    <span className="mentor-label">한줄 소개</span>
                    <span className="mentor-value">안녕하세요 직장에서 힘든 모두 해결해드릴게요!</span>
                </div>
                <div className="mentor-row">
                    <span className="mentor-label">소개글</span>
                    <textarea className="mentor-textarea" value="안녕하세요 직장에서 힘든 모두 해결해드릴게요!" readOnly></textarea>
                </div>
                <div className="mentor-row">
                    <span className="mentor-label">경력</span>
                    <div className="mentor-experience">
                        <p>2021.08 ~ 2021.11</p>
                        <p>캐논메디칼시스템즈</p>
                    </div>
                </div>
            </div>
            <div className="mentor-actions">
                <button className="approve-btn">승인</button>
                <button className="reject-btn">거절</button>
            </div>
        </div>

  )
}
