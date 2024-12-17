import React from 'react'

export default function navigate() {
    const menuBarStyle = {
        display: "flex",
        justifyContent: "left",
        gap: "20px",
      };
      const linkStyle = {
        fontFamily: "'Noto Sans KR', sans-serif",
        fontSize: "14px",
        color: "#555",
        textDecoration: "none",
        transition: "color 0.3s",
        fontWeight: "800",
      };
    
      // const linkHoverStyle = {
      //   color: "#007bff",
      //   textDecoration: "underline",
      // };
  return (
    <div className="collapse navbar-collapse" id="navbarCollapse">
    <div className="headerMenuBar" style={menuBarStyle}>
      <a href="/main" className="logo">
        <img
          src="/logo.png"
          style={{ width: "100px", height: "30px", marginBottom: "-10px" }}
        />
      </a>
      <a href="/inquiry" className="inquiry" style={linkStyle}>
        <img
          className="gnb_menu_img"
          src="https://img.icons8.com/?size=100&id=EtlPmfgnN7sW&format=png&color=000000"
          style={{
            width: "20px",
            height: "20px",
            marginRight: "0px",
            marginBottom: "-3px",
          }}
        />
        질문하기
      </a>
      <a href="/멘토찾기페이지" className="findMentor" style={linkStyle}>
        <img
          className="gnb_menu_img"
          src="https://img.icons8.com/?size=100&id=p3miLroKw4iR&format=png&color=000000"
          style={{
            width: "20px",
            height: "20px",
            marginRight: "5px",
            marginBottom: "-3px",
          }}
        />
        멘토 찾기
      </a>
      <a href="/멘토컨텐츠페이지" className="mentorContent" style={linkStyle}>
        <img
          className="gnb_menu_img"
          src="https://img.icons8.com/?size=100&id=94504&format=png&color=000000"
          style={{
            width: "20px",
            height: "20px",
            marginRight: "5px",
            marginBottom: "-3px",
          }}
        />
        멘토 컨텐츠
      </a>
    </div>
  </div>
  )
}
