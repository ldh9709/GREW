import React, { useState } from "react";
import "../css/styles.css";
import { useLocation, useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';


export default function Navigate() {
  const [searchTerm, setSearchTerm] = useState(""); // 검색어 상태
  const navigate = useNavigate();
  const location = useLocation();// 문 추가된메서드
  
  // 검색어에 따라 목록 필터링
  const handleSearchChange = (e) => {
    const searchTerm = e.target.value;
    setSearchTerm(searchTerm);
  };
  // 검색 버튼 클릭 시 동작 함수
  const handleSearchClick = () => {
    // 검색어가 있을 경우, searchList 페이지로 이동하면서 검색어를 전달
    if (searchTerm.trim() !== "") {
      if(location.pathname.includes("/mentor-profile/list")) {    //추가된 메서드 문준형
        //멘토리스트 레이지의 경우
        navigate(`/mentor-profile/search?query=${searchTerm}`); // 검색어를 쿼리 파라미터로 전달
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
    <div className="headerMenuBar">
      <a href="/main" className="logo">
        <img
          src="/logo.png"
          alt=""
          style={{ width: "100px", height: "50px", marginBottom: "-10px", marginRight: "5px" }}
        />
      </a>
      <a href="/inquiry" className="header-link">
        질문하기
      </a>

      <a href="/mentor-profile/list" className="header-link">
        멘토 찾기
      </a>

      <a href="/mentor-board/list" className="header-link">
        멘토 컨텐츠
      </a>
      <div className="search-container">
        <input
          type="text"
          value={searchTerm}
          onChange={handleSearchChange}
          onKeyDown={handleKeyPress}
          placeholder="궁금한 내용을 검색해보세요!"
          className="search-input"
        />
        <FontAwesomeIcon icon={faSearch} className="search-icon" />
      </div>
    </div>

    </div>
  );
}