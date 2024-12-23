import React, { useState } from "react";
import "../css/styles.css";
import { useNavigate } from "react-router-dom";
export default function Navigate() {
  const [searchTerm, setSearchTerm] = useState(""); // 검색어 상태
  const navigate = useNavigate();
  const menuBarStyle = {
    display: "flex",
    justifyContent: "left",
    gap: "20px",
  };
  const linkStyle = {
    fontFamily: "'Noto Sans KR', sans-serif",
    fontSize: "17px",
    color: "#555",
    textDecoration: "none",
    transition: "color 0.3s",
    fontWeight: "800",
  };
  // 검색어에 따라 목록 필터링
  const handleSearchChange = (e) => {
    const searchTerm = e.target.value;
    setSearchTerm(searchTerm);
  };
  // 검색 버튼 클릭 시 동작 함수
  const handleSearchClick = () => {
    // 검색어가 있을 경우, searchList 페이지로 이동하면서 검색어를 전달
    if (searchTerm.trim() !== "") {
      navigate(`/searchList?query=${searchTerm}`); // 검색어를 쿼리 파라미터로 전달
    }
  };
  // Enter 키 입력 시 버튼 클릭처럼 동작하도록 처리
  const handleKeyPress = (e) => {
    if (e.key === "Enter") {
      handleSearchClick();
    }
  };
  return (
    <div className="collapse navbar-collapse" id="navbarCollapse">
      <div className="headerMenuBar" style={menuBarStyle}>
        <a href="/main" className="logo">
          <img
            src="/logo.png"
            alt=""
            style={{ width: "100px", height: "50px", marginBottom: "-10px", marginRight:"5px"}}
          />
        </a>
        <a href="/inquiry" className="inquiry" style={linkStyle}>
        질문하기
        </a>
        <a href="/멘토찾기페이지" className="findMentor" style={linkStyle}>
        멘토 찾기
        </a>
        <a href="/멘토컨텐츠페이지" className="mentorContent" style={linkStyle}>
        멘토 컨텐츠
        </a>
        {/* 검색창 */}
        <div className="search-container">
          <input
            type="text"
            value={searchTerm}
            onChange={handleSearchChange}
            onKeyDown={handleKeyPress} // Enter 키를 감지
            placeholder="검색어를 입력하세요..."
            className="search-input"
          />
        </div>
        <div>
          <button onClick={handleSearchClick} className="search-button">
            <img
              className="gnb_menu_img"
              src="https://img.icons8.com/?size=100&id=132&format=png&color=ffffff"
              alt=""
              style={{
                width: "30px",
                height: "30px",
                marginRight: "5px",
                marginBottom: "-3px",
              }}
            />
          </button>
        </div>
      </div>
    </div>
  );
}