import "../css/styles.css";
import React, { useEffect, useState } from "react";
import * as inquiryApi from "../api/inquiryApi";
import InquiryItem from "./InquiryItem";
import * as categoryApi from "../api/categoryApi";
function InqiuryList() {
  const [inquirys, setInquiry] = useState([]);
  const [currentPage, setCurrentPage] = useState(1); // 현재 페이지 번호
  const [totalPages, setTotalPages] = useState(0); // 전체 페이지 수
  const [itemsPerPage] = useState(5); // 페이지당 항목 수 (예: 한 페이지에 5개 항목)
  const [sortType, setSortType] = useState("latest"); // 기본적으로 'latest'로 설정
  // API 호출로 데이터를 가져오는 함수

  const [categories, setCategories] = useState([]); // 카테고리 리스트
  const [selectedCategory, setSelectedCategory] = useState(null); // 선택된 카테고리
  const [childCategories, setChildCategories] = useState([]);
  // 카테고리 목록을 가져오는 함수
  const fetchCategories = async () => {
    try {
      const response = await categoryApi.ListCategory();
      setCategories(response.data); // 카테고리 목록을 상태에 저장
      console.log(response.data);
    } catch (error) {
      console.error("카테고리 가져오기 실패:", error);
    }
  };

  useEffect(() => {
    fetchCategories(); // 컴포넌트 로드 시 카테고리 목록 가져오기
  }, []);

  // 카테고리 버튼 클릭 시 호출되는 함수
  const handleCategoryClick = (categoryNo) => {
    setSelectedCategory(categoryNo); // 선택된 카테고리 업데이트
    // 선택된 카테고리로 필터링하거나 추가 작업을 할 수 있습니다.
    // 해당 categoryNo에 해당하는 자식 카테고리 필터링
    // const selectedCategory = categories.find((category) => category.categoryNo === categoryNo);
    // // 자식 카테고리 추출
    // const filteredChildCategories = categories.filter(
    //   (category) => category.parentCategoryNo === categoryNo
    // )
    // setSelectedCategory(selectedCategory);
    // setChildCategories(filteredChildCategories);
  };

  const fetchInquiries = async (page, size, sortButton) => {
    try {
      let responseJsonObject;
      if (sortButton === "view") {
        responseJsonObject = await inquiryApi.listInquiryView(page, size);
      } else if (sortButton === "answerCount") {
        responseJsonObject = await inquiryApi.listInquiryAnswer(page, size);
      } else if (sortButton === "latest") {
        responseJsonObject = await inquiryApi.listInquiryDate(page, size);
      }

      console.log(responseJsonObject);
      setInquiry(responseJsonObject.data.content); // 데이터가 'content' 안에 있다고 가정
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
    fetchInquiries(currentPage - 1, itemsPerPage, sortType);
  }, [currentPage, sortType]);
  // 버튼 클릭시 호출 함수
  const handleRadioChange = (e) => {
    setSortType(e.target.value); //버튼 밸류로 sorttype변경
  };
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
          {categories.filter((category) => category.categoryDepth === 1) // categoryDepth가 1인 카테고리만 필터링
          .map((category) => (
            
            <button
              key={category.categoryNo} // 각 버튼에 고유한 key 값
              onClick={() => handleCategoryClick(category.categoryNo)} // 클릭 시 카테고리 선택
              style={{ margin: "5px" }}
            >
              {category.categoryName} {/* 카테고리 이름 표시 */}
            </button>
          ))}
        </div>

        {/* 선택된 카테고리 표시 */}
        {selectedCategory && <p>선택된 카테고리 ID: {selectedCategory}</p>}
        <div>
        {childCategories.length > 0 ? (
          childCategories.map((category) => (
            <button
              key={category.categoryNo}
              onClick={() => handleCategoryClick(category.categoryNo)}
              style={{ margin: '5px' }}
            >
              {category.categoryName}
            </button>
          ))
        ) : (
          <p>자식 카테고리가 없습니다.</p>
        )}
      </div>
      </div>
      <div className="radio-container">
        {/* 라디오 버튼 */}
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
      <table border="0" cellPadding="0" cellSpacing="0">
        <tbody>
          {inquirys.map((inquiry) => {
            return <InquiryItem key={inquiry.inquiryNo} inquiry={inquiry} />;
          })}
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
    </>
  );
}

export default InqiuryList;
