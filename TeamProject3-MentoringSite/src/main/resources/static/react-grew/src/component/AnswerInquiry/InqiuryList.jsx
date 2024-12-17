import "../../css/styles.css";
import React, { useEffect, useState } from "react";
import * as inquiryApi from "../../api/inquiryApi";
import InquiryItem from "./InquiryItem";
import * as categoryApi from "../../api/categoryApi";

function InqiuryList() {
  const [inquirys, setInquiry] = useState([]);
  const [currentPage, setCurrentPage] = useState(1); // 현재 페이지 번호
  const [totalPages, setTotalPages] = useState(0); // 전체 페이지 수
  const [itemsPerPage] = useState(5); // 페이지당 항목 수 (예: 한 페이지에 5개 항목)
  const [sortType, setSortType] = useState("latest"); // 기본적으로 'latest'로 설정
  const [categories, setCategories] = useState([]); // 카테고리 리스트
  const [selectedCategory, setSelectedCategory] = useState(null); // 선택된 카테고리
  const [childCategories, setChildCategories] = useState([]); // 하위 카테고리 상태
  const [selectedChildCategory, setSelectedChildCategory] = useState(null); // 선택된 하위 카테고리
  // 카테고리 목록을 가져오는 함수
  const fetchCategories = async () => {
    try {
      const response = await categoryApi.ListCategory();
      setCategories(response.data); // 카테고리 목록을 상태에 저장
    } catch (error) {
      console.error("카테고리 가져오기 실패:", error);
    }
  };

  // 컴포넌트 로드 시 카테고리 목록 가져오기
  useEffect(() => {
    fetchCategories();
  }, []);
  // 라디오 버튼 클릭 시 정렬 방식 변경
  const handleRadioChange = (e) => {
    setSortType(e.target.value);
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
  // 문의 목록을 페이지네이션과 함께 가져오는 함수
  const fetchInquiries = async (page, size, sortButton, selectedCategory) => {
    try {
      let responseJsonObject;
      const selectedCat = categories.find(
        (cat) => cat.categoryNo === selectedCategory
      );
      console.log(selectedCat);
      if (!selectedCategory) {
        if (sortButton === "view") {
          responseJsonObject = await inquiryApi.listInquiryView(page, size);
        } else if (sortButton === "answerCount") {
          responseJsonObject = await inquiryApi.listInquiryAnswer(page, size);
        } else if (sortButton === "latest") {
          responseJsonObject = await inquiryApi.listInquiryDate(page, size);
        }
      } else if (selectedCat.categoryDepth === 2) {
        if (sortButton === "view") {
          responseJsonObject = await inquiryApi.listInquiryByCategoryView(
            selectedCategory,
            page,
            size
          );
        } else if (sortButton === "answerCount") {
          responseJsonObject = await inquiryApi.listInquiryByCategoryAnswer(
            selectedCategory,
            page,
            size
          );
        } else if (sortButton === "latest") {
          responseJsonObject = await inquiryApi.listInquiryByCategoryDate(
            selectedCategory,
            page,
            size
          );
        }
      } else if (selectedCat && selectedCat.categoryDepth === 1) {
        if (sortButton === "view") {
          responseJsonObject = await inquiryApi.listInquiryByParentCategoryView(
            selectedCategory,
            page,
            size
          );
        } else if (sortButton === "answerCount") {
          responseJsonObject =
            await inquiryApi.listInquiryByParentCategoryAnswer(
              selectedCategory,
              page,
              size
            );
        } else if (sortButton === "latest") {
          responseJsonObject = await inquiryApi.listInquiryByParentCategoryDate(
            selectedCategory,
            page,
            size
          );
        }
      }

      setInquiry(responseJsonObject.data.content); // 'content' 안에 데이터가 있다고 가정
      setTotalPages(responseJsonObject.data.totalPages); // 전체 페이지 수 설정
    } catch (error) {
      console.error("API 호출 실패:", error);
    }
  };

  // 페이지 변경 시 데이터 갱신
  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  // 페이지 로드 시 데이터 가져오기
  useEffect(() => {
    fetchInquiries(
      currentPage - 1,
      itemsPerPage,
      sortType,
      selectedCategory,
      selectedChildCategory
    );
  }, [currentPage, sortType, selectedCategory, selectedChildCategory]);

  // 페이지 번호 버튼 표시
  const pageNumbers = [];
  for (let i = 1; i <= totalPages; i++) {
    pageNumbers.push(i);
  }

  return (
    <>
      <div>
        <h1>질문게시판</h1>
        <div>
          <a
            href="/inquiry/inquiryWrite"
            style={{ textAlign: "right", display: "block" }}
          >
            질문등록
          </a>
        </div>

        {/* 카테고리 버튼들 */}
        <div>
          {categories
            .filter((category) => category.categoryDepth === 1) // categoryDepth가 1인 카테고리만 필터링
            .map((category) => (
              <button
                key={category.categoryNo}
                onClick={() => handleCategoryClick(category.categoryNo)} // 클릭 시 카테고리 선택
                style={{ margin: "5px" }}
              >
                {category.categoryName}
              </button>
            ))}
        </div>

        {/* 하위 카테고리 버튼 렌더링 */}
        {childCategories.length > 0 && (
          <div>
            {childCategories.map((child) => (
              <button
                key={child.categoryNo}
                onClick={() => handleCategoryClick(child.categoryNo)} // 하위 카테고리 선택
                style={{ margin: "5px" }}
              >
                {child.categoryName}
              </button>
            ))}
          </div>
        )}

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
          <label>
            <input
              type="radio"
              name="sortType"
              value="answerCount"
              checked={sortType === "answerCount"}
              onChange={handleRadioChange}
            />
            답변많은순
          </label>
        </div>

        {/* 문의 목록 테이블 */}
        <table border="0" cellPadding="0" cellSpacing="0">
          <tbody>
            {inquirys.map((inquiry) => (
              <InquiryItem key={inquiry.inquiryNo} inquiry={inquiry} />
            ))}
          </tbody>
        </table>

        {/* 페이지네이션 버튼 */}
        <div className="pagenation">
          {pageNumbers.map((number) => (
            <button key={number} onClick={() => paginate(number)}>
              {number}
            </button>
          ))}
        </div>
      </div>
    </>
  );
}

export default InqiuryList;
