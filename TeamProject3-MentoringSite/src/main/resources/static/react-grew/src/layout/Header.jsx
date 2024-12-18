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
        <a href="로그인폼" className="login">
          로그인
        </a>
        <a href="/member/mypage/1" className="mypage">
          마이페이지
        </a>
        <a href="회원가입폼" className="signup">
          회원가입
        </a>
      </div>
    </div>
  );
}
