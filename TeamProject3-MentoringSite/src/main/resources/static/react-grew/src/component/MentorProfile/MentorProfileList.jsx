import "../../css/styles.css";
import React, { useEffect, useState } from "react";
import * as mentorProfileApi from "../../api/mentorProfileApi";
import MentorProfileItem from "./MentorProfileItem";
import * as categoryApi from "../../api/categoryApi";
import { useNavigate } from "react-router-dom";

function MentorProfileList() {
  const [mentorProfiles, setMentorProfiles] = useState([]);
  const [currentPage, setCurrentPage] = useState(1); // 현재 페이지 번호
  const [totalPages, setTotalPages] = useState(0); // 전체 페이지 수
  const [itemsPerPage] = useState(12); // 페이지당 항목 수
  const [sortType, setSortType] = useState("follow"); // 기본적으로 'follow'로 설정
  const [categories, setCategories] = useState([]); // 카테고리 리스트
  const [selectedCategory, setSelectedCategory] = useState(null); // 선택된 카테고리
  const [childCategories, setChildCategories] = useState([]); // 하위 카테고리 상태
  const navigate = useNavigate();

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
  }, []);

  // 라디오 버튼 클릭 시 정렬 방식 변경
  const handleRadioChange = (e) => {
    setSortType(e.target.value);
    setCurrentPage(1); // 정렬 기준 변경 시 첫 페이지로 이동
  };

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

  // 멘토 프로필 데이터를 가져오는 함수
  const fetchMentorProfiles = async (page, size, sortButton, selectedCategory) => {
    try {
      let responseJsonObject;
      const selectedCat = categories.find(
        (cat) => cat.categoryNo === selectedCategory
      );

      if (!selectedCategory) {
        if (sortButton === "follow") {
          responseJsonObject = await mentorProfileApi.listMentorsByFollowCount(page, size);
        } else if (sortButton === "mentoring") {
          responseJsonObject = await mentorProfileApi.listMentorsByMentoringCount(page, size);
        } else if (sortButton === "activity") {
          responseJsonObject = await mentorProfileApi.listMentorsByActivityCount(page, size);
        }
      } else if (selectedCat?.categoryDepth === 2) {
        if (sortButton === "follow") {
          responseJsonObject = await mentorProfileApi.listMentorsByCategoryNoFollow(
            selectedCategory,
            page,
            size
          );
        } else if (sortButton === "mentoring") {
          responseJsonObject = await mentorProfileApi.listMentorsByCategoryNoMentoring(
            selectedCategory,
            page,
            size
          );
        } else if (sortButton === "activity") {
          responseJsonObject = await mentorProfileApi.listMentorsByCategoryNoActivity(
            selectedCategory,
            page,
            size
          );
        }
      } else if (selectedCat?.categoryDepth === 1) {
        if (sortButton === "follow") {
          responseJsonObject = await mentorProfileApi.listMentorsByParentCategoryFollowCount(
            selectedCategory,
            page,
            size
          );
        } else if (sortButton === "mentoring") {
          responseJsonObject = await mentorProfileApi.listMentorsByParentCategoryMentoringCount(
            selectedCategory,
            page,
            size
          );
        } else if (sortButton === "activity") {
          responseJsonObject = await mentorProfileApi.listMentorsByParentCategoryActivityCount(
            selectedCategory,
            page,
            size
          );
        }
      }

      setMentorProfiles(responseJsonObject?.data?.content || []);
      setTotalPages(responseJsonObject?.data?.totalPages || 0);
    } catch (error) {
      console.error("API 호출 실패:", error);
    }
  };

  // 페이지 변경 시 데이터 갱신
  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  useEffect(() => {
    fetchMentorProfiles(currentPage - 1, itemsPerPage, sortType, selectedCategory);
  }, [currentPage, sortType, selectedCategory]);

  // 페이지네이션 버튼 표시 (10개씩 끊어서 표시)
  const pageNumbers = [];
  const pagesToShow = 10; // 한 번에 보여줄 페이지 수
  const startPage = Math.floor((currentPage - 1) / pagesToShow) * pagesToShow + 1;
  const endPage = Math.min(startPage + pagesToShow - 1, totalPages);

  for (let i = startPage; i <= endPage; i++) {
    pageNumbers.push(i);
  }

  return (
    <>
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
                    selectedCategory === category.categoryNo ? "#4CAF50" : "",
                  color:
                    selectedCategory === category.categoryNo ? "white" : "",
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
                    selectedCategory === child.categoryNo ? "#4CAF50" : "",
                  color: selectedCategory === child.categoryNo ? "white" : "",
                }}
              >
                {child.categoryName}
              </button>
            ))}
          </div>
        )}
      </div>
      {/* 정렬 라디오 버튼 */}
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
          멘토링 순
        </label>
        <label>
          <input
            type="radio"
            name="sortType"
            value="activity"
            checked={sortType === "activity"}
            onChange={handleRadioChange}
          />
          활동 순
        </label>
      </div>
      <div className="profile-grid">
        {mentorProfiles.length > 0 ? (
          mentorProfiles.map((mentor) => (
            <MentorProfileItem key={mentor.mentorProfileNo} mentor={mentor} />
          ))
        ) : (
          <p>멘토 프로필이 없습니다.</p>
        )}
      </div>
      <div className="pagination">
        {startPage > 1 && (
          <button onClick={() => paginate(startPage - 1)}>이전</button>
        )}
        {pageNumbers.map((number) => (
          <button
            key={number}
            onClick={() => paginate(number)}
            style={{
              backgroundColor: number === currentPage ? "#006618" : "",
              color: number === currentPage ? "white" : "",
            }}
          >
            {number}
          </button>
        ))}
        {endPage < totalPages && (
          <button onClick={() => paginate(endPage + 1)}>다음</button>
        )}
      </div>
    </>
  );
}

export default MentorProfileList;