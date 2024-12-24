import "../../css/styles.css";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../../css/mentorProfile.css";
import {
  listMentorProfiles,
  listMentorsByFollowCount,
  listMentorsByMentoringCount,
  listMentorsByActivityCount,
} from "../../api/mentorProfileApi.js";
import MentorProfileItem from "./MentorProfileItem";

import * as categoryApi from "../../api/categoryApi"; //카테고리 

const MentorProfileList = () => {
  const [mentorProfiles, setMentorProfiles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [sortType, setSortType] = useState("follow");
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
      const [categories, setCategories] = useState([]); // 카테고리 리스트
      const [selectedCategory, setSelectedCategory] = useState(null); // 선택된 카테고리
      const [childCategories, setChildCategories] = useState([]); // 하위 카테고리 상태


// 카테고리 목록을 가져오는 함수
  const fetchCategories = async () => {
    try {
      const response = await categoryApi.ListCategory();
      setCategories(response.data); // 카테고리 목록을 상태에 저장
    } catch (error) {
      console.error("카테고리 가져오기 실패:", error);
    }
  };

  useEffect(() => {
    fetchCategories();
  }, []); //카테고리

// 카테고리 버튼 클릭 시 호출되는 함수
  const handleCategoryClick = async (categoryNo) => {
    setSelectedCategory(categoryNo); // 새 카테고리 선택
    console.log("선택된 상위 카테고리", categoryNo);

    const selectedCategory = categories.find(
      (cat) => cat.categoryNo === categoryNo
    );
    if (selectedCategory && selectedCategory.categoryDepth === 1) {
      try {
        // 하위 카테고리 API 호출
        const response = await categoryApi.childCategory(categoryNo); // categoryNo를 API 요청에 포함
        setChildCategories(response.data.childCategories); // 하위 카테고리 상태에 저장
      } catch (error) {
        console.error("하위 카테고리 로드 오류:", error);
      }
    }
  };



  useEffect(() => {
    fetchMentorProfiles();
  }, [sortType, currentPage]);

  const fetchMentorProfiles = async () => {
    try {
      setLoading(true);
      setError(null);
      let fetchFunction;

      switch (sortType) {
        case "follow":
          fetchFunction = listMentorsByFollowCount;
          break;
        case "mentoring":
          fetchFunction = listMentorsByMentoringCount;
          break;
        case "activity":
          fetchFunction = listMentorsByActivityCount;
          break;
        default:
          fetchFunction = listMentorProfiles;
      }

      const response = await fetchFunction(currentPage, 12);

      if (response?.data?.content) {
        setMentorProfiles(response.data.content);
        setTotalPages(response.data.totalPages);
      } else {
        throw new Error("멘토 프로필을 불러오는 중 오류가 발생했습니다.");
      }
    } catch (err) {
      setError(err.message || "알 수 없는 오류가 발생했습니다.");
    } finally {
      setLoading(false);
    }
  };

  const handleRadioChange = (e) => {
    setSortType(e.target.value);
    setCurrentPage(0); // 정렬 기준 변경 시 첫 페이지로 이동
  };

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const renderPagination = () => {
    const pageNumbers = Array.from({ length: totalPages }, (_, i) => i);

    return (
      
      
      <div className="pagination">
        <button
          onClick={() => handlePageChange(currentPage - 1)}
          disabled={currentPage === 0}
        >
          이전
        </button>
        {pageNumbers.map((number) => (
          <button
            key={number}
            onClick={() => handlePageChange(number)}
            className={number === currentPage ? "active-page" : ""}
          >
            {number + 1}
          </button>
        ))}
        <button
          onClick={() => handlePageChange(currentPage + 1)}
          disabled={currentPage === totalPages - 1}
        >
          다음
        </button>
      </div>
    );
  };

  return (
    
    <div className="mentor-list">
      <div className="mentor-profile-list">
        <h1>멘토 프로필 목록</h1>
        {/* 카테고리 버튼들 */}
      <div className="category-container">
        <div className="category-parent">
          {categories
            .filter((category) => category.categoryDepth === 1) // categoryDepth가 1인 카테고리만 필터링
            .map((category) => (
              <button
                key={category.categoryNo}
                onClick={() => handleCategoryClick(category.categoryNo)} // 클릭 시 카테고리 선택
                className="category-button"
                style={{
                  backgroundColor:
                    selectedCategory === category.categoryNo ? "#4CAF50" : "", // 선택된 카테고리는 색상 변경
                  color:
                    selectedCategory === category.categoryNo ? "white" : "", // 선택된 카테고리 글자 색상 변경
                }}
              >
                {category.categoryName}
              </button>
            ))}
        </div>

        {/* 하위 카테고리 버튼 렌더링 */}
        {childCategories.length > 0 && (
          <div className="category-child">
            {childCategories.map((child) => (
              <button
                key={child.categoryNo}
                onClick={() => handleCategoryClick(child.categoryNo)} // 하위 카테고리 선택
                className="category-button"
                style={{
                  backgroundColor:
                    selectedCategory === child.categoryNo ? "#4CAF50" : "", // 선택된 카테고리는 색상 변경
                  color: selectedCategory === child.categoryNo ? "white" : "", // 선택된 카테고리 글자 색상 변경
                }}
              >
                {child.categoryName}
              </button>
            ))}
          </div>
        )}
      </div>
        <div className="radio-container">
          <label>
            <input
              type="radio"
              name="sortType"
              value="follow"
              checked={sortType === "follow"}
              onChange={handleRadioChange}
            />
            팔로우 순
          </label>
          <label>
            <input
              type="radio"
              name="sortType"
              value="mentoring"
              checked={sortType === "mentoring"}
              onChange={handleRadioChange}
            />
            멘토링 횟수 순
          </label>
          <label>
            <input
              type="radio"
              name="sortType"
              value="activity"
              checked={sortType === "activity"}
              onChange={handleRadioChange}
            />
            활동 수 순
          </label>
        </div>
        {loading && <p className="loading-spinner">로딩 중입니다...</p>}
        {error && <p className="error-message">에러 발생: {error}</p>}
        <div className="profile-grid">
          {!loading && mentorProfiles.length > 0
            ? mentorProfiles.map((mentor) => (
                <MentorProfileItem
                  key={mentor.mentorProfileNo}
                  mentor={mentor}
                />
              ))
            : !loading && <p>멘토 프로필이 없습니다.</p>}
        </div>
        <div className="mentor-profile-pagenation">
          {!loading && totalPages > 0 && renderPagination()}
        </div>
      </div>
    </div>
  );
};

export default MentorProfileList;