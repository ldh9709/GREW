import React from "react";
import { useLocation, useNavigate } from "react-router-dom";

const MemberFindIdCheck = () => {
  const location = useLocation();
  const navigate = useNavigate();

  // navigate로 전달받은 state에서 memberId 추출
  const { memberId } = location.state || {};

  // 만약 상태가 없을 경우 홈으로 리다이렉트
  if (!memberId) {
    alert("유효하지 않은 접근입니다.");
    navigate("/");
    return null;
  }

  return (
    <div className="find-id-check-container">
      <h1>아이디 찾기 결과</h1>
      <p className="description">회원님의 아이디는 다음과 같습니다:</p>
      <div className="member-id-display">
        <strong>{memberId}</strong>
      </div>
      <button className="go-to-login-button" onClick={() => navigate("/member/login")}>
        로그인 페이지로 이동
      </button>
    </div>
  );
};

export default MemberFindIdCheck;
