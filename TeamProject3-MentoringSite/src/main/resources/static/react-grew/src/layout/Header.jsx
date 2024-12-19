import React from "react";

export default function HeaderMenu() {
  const navStyle = {
    fontFamily: "'Noto Sans KR', sans-serif",
    padding: "10px 20px",
  };

  const rightMenuBarStyle = {
    display: "flex",
    justifyContent: "flex-end", // 오른쪽 정렬
    gap: "20px",
  };

  return (
    <div className="header" style={navStyle}>
      <div className="rightMenu" style={rightMenuBarStyle}>
        <a href="/member/login" className="login">
          로그인
        </a>
        <a href="/member/profile" className="mypage">
          마이페이지
        </a>
        <a href="/member/join" className="signup">
          회원가입
        </a>
      </div>
    </div>
  );
}
