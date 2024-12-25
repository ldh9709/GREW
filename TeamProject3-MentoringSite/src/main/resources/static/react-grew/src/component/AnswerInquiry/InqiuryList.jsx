import "../../css/styles.css";
import React, { useEffect, useState } from "react";
import * as inquiryApi from "../../api/inquiryApi";
import InquiryItem from "./InquiryItem";
import BestAnswerItem from "./BestAnswerItem";
import * as categoryApi from "../../api/categoryApi";
import { useNavigate } from "react-router-dom";
import { useMemberAuth } from "../../util/AuthContext";

function InqiuryList() {
  const {token, member} = useMemberAuth();
  const [inquirys, setInquiry] = useState([]);
  const [currentPage, setCurrentPage] = useState(1); // 현재 페이지 번호
  const [totalPages, setTotalPages] = useState(0); // 전체 페이지 수
  const [itemsPerPage] = useState(5); // 페이지당 항목 수 (예: 한 페이지에 5개 항목)
  const [sortType, setSortType] = useState("latest"); // 기본적으로 'latest'로 설정
  const [categories, setCategories] = useState([]); // 카테고리 리스트
  const [selectedCategory, setSelectedCategory] = useState(null); // 선택된 카테고리
  const [childCategories, setChildCategories] = useState([]); // 하위 카테고리 상태
  const [bestAnswer, setBestAnswer] = useState([]);
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
  useEffect(() => {
    fetchBestAnswer();
  }, []);
  const fetchBestAnswer = async () => {
    const responseJsonObject = await inquiryApi.bestAnswerList();
    console.log(responseJsonObject.data);
    setBestAnswer(responseJsonObject.data.content);
  };
  // 문의 목록을 페이지네이션과 함께 가져오는 함수
  const fetchInquiries = async (page, size, sortButton, selectedCategory) => {
    try {
      let responseJsonObject;
      const selectedCat = categories.find(
        (cat) => cat.categoryNo === selectedCategory
      );
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

      setInquiry(responseJsonObject.data.content);
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
    fetchInquiries(currentPage - 1, 8, sortType, selectedCategory);
  }, [currentPage, sortType, selectedCategory, childCategories]);

  // 페이지네이션 버튼 표시 (10개씩 끊어서 표시)
  const pageNumbers = [];
  const pagesToShow = 10; // 한 번에 보여줄 페이지 수
  const startPage =
    Math.floor((currentPage - 1) / pagesToShow) * pagesToShow + 1;
  const endPage = Math.min(startPage + pagesToShow - 1, totalPages);

  for (let i = startPage; i <= endPage; i++) {
    pageNumbers.push(i);
  }

  const handleWriteButton = () => {
    if (member.memberRole== 'ROLE_MENTEE') {
      navigate("/inquiry/inquiryWrite");
    } else if(member.memberRole =='ROLE_MENTOR'){
      alert('멘티만 질문을 등록할 수 있습니다.')
      return;
    }else{
      alert("로그인이 필요한 서비스입니다");
      return;
    }
  };
  return (
    <>
      

     {/* 카테고리 버튼들 */}
    <div className="category-container">
    <h1>질문하기</h1>
      <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
        {/* Parent 카테고리 */}
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
        {/* 질문하기 버튼 */}
        <div className="btn-inquiry-write-div">
          <button className="btn-inquiry-write" onClick={handleWriteButton}>
            <img
              src="https://img.icons8.com/?size=100&id=98973&format=png&color=000000"
              style={{
                width: "20px",
                height: "20px",
                marginRight: "5px",
                marginLeft: "-5px",
                marginBottom: "-3px",
              }}
            />
            질문등록
          </button>
        </div>
      </div>
      {/* 하위 카테고리 버튼 */}
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
      {/* 정렬 라디오 버튼 */}
      <div className="inquiry-radio-container">
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

      <div className="inquiry-list-main">
        <div className="inquiry-list-sort">
          {/* 문의 목록 테이블 */}
          {/*<div>
             선택된 카테고리가 존재할 때만 카테고리 이름을 표시 
            {selectedCategory != null && (
              <div className="inquiry-category-name">
                {
                  // 선택된 카테고리 객체가 존재할 때만 categoryName을 출력
                  categories.find(
                    (category) => category.categoryNo === selectedCategory
                  )?.categoryName || "선택된 카테고리가 없습니다"
                }
              </div>
            )}
          </div>*/}
          {inquirys && inquirys.length > 0 ? (
            inquirys.map((inquiry) => (
              <InquiryItem key={inquiry.inquiryNo} inquiry={inquiry} />
            ))
          ) : (
            <div className="inquiry-write-btn">
              <div>아직 등록된 질문이 없습니다.</div>
            </div>
          )}
        </div>
        {/* 왼쪽 작은 리스트 */}
        <div className="small-list">
          <div>
            최근 추천 많이 받은 답변
            <div>
              {bestAnswer.map((answer, index) => (
                <BestAnswerItem
                  key={answer.answerNo}
                  answer={answer}
                  index={index}
                />
              ))}
            </div>
          </div>
        </div>
      </div>
      {/* 페이지네이션 버튼 */}
      <div className="pagenation">
        {startPage > 1 && (
          <button onClick={() => paginate(startPage - 1)}>이전</button>
        )}{" "}
        {/* 이전 그룹 */}
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
          <button onClick={() => paginate(endPage + 1)}>다음</button> // 다음 그룹
        )}
      </div>
    </>
  );
}

export default InqiuryList;
