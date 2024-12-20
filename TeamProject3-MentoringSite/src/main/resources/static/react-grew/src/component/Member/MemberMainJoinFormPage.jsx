import React from 'react'
import { useNavigate } from 'react-router-dom';

const MemberMainJoinFormPage = () => {
  const navigate = useNavigate();

  const handleNavigate= (role) => {
    navigate(`/member/join/form?role=${role}`);

  }

  return (
    <div className="signup-container">
      <h1 className="signup-title">회원가입</h1>
      <p className="signup-subtitle">가입 유형을 선택 해 주세요.</p>
      <div className="signup-button-container">
        <button className="signup-btn signup-mentee" onClick={() => handleNavigate('mentee')}>멘티</button>
        <button className="signup-btn signup-mentor" onClick={() => handleNavigate('mentor')}>멘토</button>
      </div>
    </div>
  )
}
export default MemberMainJoinFormPage;