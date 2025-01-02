// SearchList.jsx
import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom"; // URL 파라미터를 가져오기 위한 useLocation
import * as inquiryApi from "../../api/inquiryApi"; // API 호출
import InquiryItem from "./InquiryItem";
import PagenationItem from "../PagenationItem";

function InquirySearchList() {
  const [searchResults, setSearchResults] = useState([]);
  const [currentPage, setCurrentPage] = useState(1); // 현재 페이지 번호
  const [totalPages, setTotalPages] = useState(0); // 전체 페이지 수
  const [itemsPerPage] = useState(5); // 페이지당 항목 수 (예: 한 페이지에 5개 항목)
  const location = useLocation(); // 현재 위치 정보 얻기
  const queryParams = new URLSearchParams(location.search); // 쿼리 파라미터를 파싱
  const query = queryParams.get("query"); // 'query' 파라미터 값 추출

  // 검색 결과 가져오는 함수
  const fetchSearchResults = async (query, page, size) => {
    const response = await inquiryApi.searchInquiry(query, page, size); // API 호출
    console.log(response.data);
    setSearchResults(response.data.content); // 검색 결과 저장
    setTotalPages(response.data.totalPages); // 전체 페이지 수 저장 (API 응답에 포함된 totalPages 값)
  };
  // 페이지 변경 시 데이터 갱신
  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  // 페이지 로드 시 데이터 가져오기
  useEffect(() => {
    if (query) {
      fetchSearchResults(query, currentPage - 1, itemsPerPage); // API에서 페이지 번호는 0부터 시작하므로 currentPage - 1을 전달
    }
  }, [currentPage, query, itemsPerPage]); // currentPage나 query가 변경되면 다시 호출
  // 페이지네이션 버튼 표시 (10개씩 끊어서 표시)
  const pageNumbers = [];
  const pagesToShow = 5; // 한 번에 보여줄 페이지 수
  const startPage =
    Math.floor((currentPage - 1) / pagesToShow) * pagesToShow + 1;
  const endPage = Math.min(startPage + pagesToShow - 1, totalPages);

  for (let i = startPage; i <= endPage; i++) {
    pageNumbers.push(i);
  }
  return (
    <>
      <div>
        <h1>검색 결과</h1>

        {searchResults.length > 0 ? (
          <div>
            {searchResults.map((inquiry) => (
              <InquiryItem key={inquiry.inquiryNo} inquiry={inquiry} />
            ))}
          </div>
        ) : (
          <div>검색 결과가 없습니다.</div>
        )}
        {/* 페이지네이션 버튼 */}
        <div>
          <PagenationItem
            currentPage={currentPage}
            totalPages={totalPages}
            paginate={paginate}
          />
        </div>
      </div>
    </>
  );
}

export default InquirySearchList;
