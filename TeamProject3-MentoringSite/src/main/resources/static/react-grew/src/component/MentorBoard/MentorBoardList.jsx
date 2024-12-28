import "../../css/styles.css";
import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import * as mentorBoardApi from "../../api/mentorBoardApi";
import MentorBoardItem from "./MentorBoardItem";
import * as categoryApi from "../../api/categoryApi";
import { useNavigate } from "react-router-dom";
import { getCookie } from "../../util/cookieUtil";
import { faPen } from "@fortawesome/free-solid-svg-icons";


function MentorBoardList() {
  const [boards, setBoards] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const [itemsPerPage] = useState(5);
  const [sortType, setSortType] = useState("latest");
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [childCategories, setChildCategories] = useState([]);
  const navigate = useNavigate();
  const memberCookie = getCookie("member");
  const BACKEND_SERVER = "http://localhost:8080"; // 백엔드 서버 URL
  const BASE_URL = "/mentor-board";
  // 카테고리 목록을 가져오는 함수
  const fetchCategories = async () => {
    try {
      const response = await categoryApi.ListCategory();
      setCategories(response.data);
    } catch (error) {
      console.error("카테고리 가져오기 실패:", error);
    }
  };

  useEffect(() => {
    fetchCategories();
  }, []);

  // 라디오 버튼 클릭 시 정렬 방식 변경
  const handleRadioChange = async (e) => {
    const selectedSortType = e.target.value;
    setSortType(selectedSortType);
    setCurrentPage(1); // 정렬 변경 시 페이지 초기화  //////////////////////////////////////////////
    try {
      let responseJsonObject;
      if (!selectedCategory) {
          // 카테고리 선택 안 된 경우
          responseJsonObject = 
              selectedSortType === "view"
              ? await mentorBoardApi.listMentorBoardsByViews(0, itemsPerPage)
              : await mentorBoardApi.listMentorBoardsByStatus(1, 0, itemsPerPage);
      } else {
          const category = categories.find(cat => cat.categoryNo === selectedCategory);
          if (category?.categoryDepth === 2) {
              responseJsonObject =
                  selectedSortType === "view"
                  ? await mentorBoardApi.listMentorBoardByCategoryView(selectedCategory, 0, itemsPerPage)
                  : await mentorBoardApi.listMentorBoardByCategoryDate(selectedCategory, 0, itemsPerPage);
          } else if (category?.categoryDepth === 1) {
              responseJsonObject =
                  selectedSortType === "view"
                  ? await mentorBoardApi.listMentorBoardByParentCategoryView(selectedCategory, 0, itemsPerPage)
                  : await mentorBoardApi.listMentorBoardByParentCategoryDate(selectedCategory, 0, itemsPerPage);
          }
      }

      // 데이터 업데이트
      setBoards(responseJsonObject?.data?.content || []);
      setTotalPages(responseJsonObject?.data?.totalPages || 0);
  } catch (error) {
      console.error("정렬 변경 중 API 호출 실패:", error);
  }
};


  // 카테고리 버튼 클릭 시 호출되는 함수
  const handleCategoryClick = async (categoryNo) => {
    setSelectedCategory(categoryNo);
    console.log("선택된 상위 카테고리", categoryNo);

    const selectedCategory = categories.find(
      (cat) => cat.categoryNo === categoryNo
    );
    if (selectedCategory?.categoryDepth === 1) {
      try {
        const response = await categoryApi.childCategory(categoryNo);
        setChildCategories(response.data.childCategories || []);
      } catch (error) {
        console.error("하위 카테고리 로드 오류:", error);
      }
    }
  };

  // 멘토 보드 데이터를 가져오는 함수
  const fetchMentorBoards = async (page, size, sortButton, selectedCategory) => {
    try {
      let responseJsonObject;
      const selectedCat = categories.find(
        (cat) => cat.categoryNo === selectedCategory
      );


      console.log("Selected Category:", selectedCategory);
      if (!selectedCategory) {
        if (sortButton === "view") {
          responseJsonObject = await mentorBoardApi.listMentorBoardsByViews(
            page,
            size
          );
        } else if (sortButton === "latest") {
          responseJsonObject = await mentorBoardApi.listMentorBoardsByStatus(
            1,
            page,
            size
          );
        }
      } else if (selectedCat?.categoryDepth === 2) {
        if (sortButton === "view") {
          responseJsonObject =
            await mentorBoardApi.listMentorBoardByCategoryView(
              selectedCategory,
              page,
              size
            );
        } else if (sortButton === "latest") {
          responseJsonObject =
            await mentorBoardApi.listMentorBoardByCategoryDate(
              selectedCategory,
              page,
              size
            );
        }
        
      } else if (selectedCat?.categoryDepth === 1) {
        const url = `${BACKEND_SERVER}${BASE_URL}/${selectedCategory}/parent/date?page=${page}&size=${size}`;
        console.log("Request URL:", url); // 요청 URL 확인


        if (sortButton === "view") {
          responseJsonObject =
            await mentorBoardApi.listMentorBoardByParentCategoryView(
              selectedCategory,
              page,
              size
            );
        } else if (sortButton === "latest") {
          responseJsonObject =
            await mentorBoardApi.listMentorBoardByParentCategoryDate(
              selectedCategory,
              page,
              size
            );
        }
      }

      setBoards(responseJsonObject?.data?.content || []);
      setTotalPages(responseJsonObject?.data?.totalPages || 0);
    } catch (error) {
      console.error("API 호출 실패:", error);
    }
  };

  useEffect(() => {
    fetchMentorBoards(currentPage - 1, itemsPerPage, sortType, selectedCategory);
  }, [currentPage, sortType, selectedCategory]);

  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const pageNumbers = [];
  const pagesToShow = 10;
  const startPage =
    Math.floor((currentPage - 1) / pagesToShow) * pagesToShow + 1;
  const endPage = Math.min(startPage + pagesToShow - 1, totalPages);

  for (let i = startPage; i <= endPage; i++) {
    pageNumbers.push(i);
  }

  const handleWriteButton = () => {
    if (memberCookie) {
      navigate("/mentor-board/create");
    } else {
      alert("로그인이 필요한 서비스입니다");
    }
  };

  return (
    <>
      <div className="category-container mentor-board-header">
      <h1>멘토 컨텐츠</h1>

      <div className="btn-mentor-board-write-div">
        <button className="btn-mentor-board-write" onClick={handleWriteButton} >
          <FontAwesomeIcon icon={faPen} />
          <span>글쓰기</span>
        </button>
      </div>

      {/* 카테고리 버튼 */}
        <div className="category-parent">
        <button
            className="category-button"
            onClick={() => {
              setSelectedCategory(null);
              setChildCategories(null);
            }}
            style={{
              backgroundColor: selectedCategory === null ? "#28a745" : "", // 선택된 카테고리는 색상 변경
              color: selectedCategory === null ? "#f3f4f6" : "", // 선택된 카테고리 글자 색상 변경
            }}
          >
            전체
          </button>
          {categories
            .filter((category) => category.categoryDepth === 1)
            .map((category) => (
              <button
                key={category.categoryNo}
                onClick={() => handleCategoryClick(category.categoryNo)}
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

        {childCategories&&childCategories.length > 0 && (
          <div className="category-child">
            {childCategories.map((child) => (
              <button
                key={child.categoryNo}
                onClick={() => handleCategoryClick(child.categoryNo)}
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
            value="latest"
            checked={sortType === "latest"}
            onChange={handleRadioChange}
          />
          최신순
        </label>
        <label>
          <input
            type="radio"
            name="sortType"
            value="view"
            checked={sortType === "view"}
            onChange={handleRadioChange}
          />
          조회순
        </label>
      </div>

      <div className="mentor-board-list-main">
        {boards.length > 0 ? (
          boards.map((board) => (
            <MentorBoardItem key={board.mentorBoardNo} board={board} />
          ))
        ) : (
          <p>등록된 멘토 보드가 없습니다.</p>
        )}
      </div>

      {/* 페이지네이션 */}
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

export default MentorBoardList;
