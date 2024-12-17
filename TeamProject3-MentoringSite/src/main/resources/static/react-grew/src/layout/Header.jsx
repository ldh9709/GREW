import React from "react";

export default function HeaderMenu() {
  const navStyle = {
    fontFamily: "'Noto Sans KR', sans-serif",
    backgroundColor: "#ffffff",
    padding: "10px 20px",
    boxShadow: "0 2px 5px rgba(0, 0, 0, 0.1)",
  };

  
  const rightMenuBarStyle = {
    display: "flex",
    justifyContent: "flex-end", // 오른쪽 정렬
    gap: "20px",
  };
  
  return (
    <nav className="header" style={navStyle}>
      <div className="rightMenu" style={rightMenuBarStyle}>
        <a href="로그인폼" className="login">
          로그인
        </a>
        <a href="회원가입폼" className="signup">
          회원가입
        </a>
      </div>
      
    </nav>
  );
}
