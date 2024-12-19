// SearchList.jsx
import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom"; // URL 파라미터를 가져오기 위한 useLocation
import * as inquiryApi from "../api/inquiryApi"; // API 호출
import InquiryItem from "./AnswerInquiry/InquiryItem";

function SearchList() {
  const [searchResults, setSearchResults] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const location = useLocation(); // 현재 위치 정보 얻기
  const queryParams = new URLSearchParams(location.search); // 쿼리 파라미터를 파싱
  const query = queryParams.get("query"); // 'query' 파라미터 값 추출

  useEffect(() => {
    if (query) {
      const fetchSearchResults = async () => {
        setLoading(true);
        try {
          const response = await inquiryApi.searchInquiry(query, 1, 3); // API 호출
          console.log(response);
          console.log(query);
          setSearchResults(response.data.content); // 검색 결과 저장
        } catch (err) {
          setError("검색 중 오류가 발생했습니다.");
        } finally {
          setLoading(false);
        }
      };

      fetchSearchResults(); // 검색 결과 가져오기
    }
  }, [query]); // query가 변경될 때마다 다시 호출
  const handleViewMore = () => {
    // "더보기" 클릭 시 InquirySearch 페이지로 이동
    navigate(`/inquirySearchList?query=${query}`); // 쿼리 파라미터를 그대로 전달하여 이동
  };
  return (
    <>
      <link
        href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
        rel="stylesheet"
      ></link>
      <div>
        <h1>검색 결과</h1>

        {loading && <div>로딩 중...</div>}
        {error && <div>{error}</div>}

        {searchResults.length > 0 ? (
          <div>
            {searchResults.slice(0, 3).map(
              (
                inquiry //슬라이스로 3개만 표출
              ) => (
                <InquiryItem key={inquiry.inquiryNo} inquiry={inquiry} />
              )
            )}
            <div className="view-more">
              <button onClick={handleViewMore}>더보기</button>
            </div>
          </div>
        ) : (
          <div>검색 결과가 없습니다.</div>
        )}
      </div>
    </>
  );
}

export default SearchList;
