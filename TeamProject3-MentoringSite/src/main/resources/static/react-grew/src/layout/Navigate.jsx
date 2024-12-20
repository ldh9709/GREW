import React, { useState } from "react";
import "../css/styles.css";
import { useLocation, useNavigate } from "react-router-dom";


export default function Navigate() {
  const [searchTerm, setSearchTerm] = useState(""); // 검색어 상태
  const navigate = useNavigate();
  const location = useLocation();// 문 추가된메서드

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
      if(location.pathname.includes("/mentorprofile/list")) {    //추가된 메서드 문준형
        //멘토리스트 레이지의 경우
        navigate(`/mentorprofile/search?query=${searchTerm}`); // 검색어를 쿼리 파라미터로 전달
      }else{  //추가됨 문준형
        // 디폴드 질문 검색으로 이동
        navigate(`/searchList?query=${searchTerm}`); // 검색어를 쿼리 파라미터로 전달
      
      } //추가됨  문준형
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
            style={{ width: "100px", height: "28px", marginBottom: "-10px" }}
          />
        </a>
        <a href="/inquiry" className="inquiry" style={linkStyle}>
          <img
            className="gnb_menu_img"
            src="https://img.icons8.com/?size=100&id=EtlPmfgnN7sW&format=png&color=000000"
            alt=""
            style={{
              width: "25px",
              height: "25px",
              marginRight: "0px",
              marginBottom: "-3px",
            }}
          />
          질문하기
        </a>
        <a href="/mentorprofile/list" className="findMentor" style={linkStyle}>
          <img
            className="gnb_menu_img"
            src="https://img.icons8.com/?size=100&id=p3miLroKw4iR&format=png&color=000000"
            alt=""
            style={{
              width: "20px",
              height: "20px",
              marginRight: "5px",
              marginBottom: "-3px",
            }}
          />
          멘토 찾기
        </a>
        <a href="/멘토 컨텐츠" className="mentorContent" style={linkStyle}>
          <img
            className="gnb_menu_img"
            src="https://img.icons8.com/?size=100&id=94504&format=png&color=000000"
            alt=""
            style={{
              width: "20px",
              height: "20px",
              marginRight: "5px",
              marginBottom: "-3px",
            }}
          />
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