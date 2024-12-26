// SearchList.jsx
import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom"; // URL 파라미터를 가져오기 위한 useLocation
import * as inquiryApi from "../api/inquiryApi"; // API 호출
import InquiryItem from "./AnswerInquiry/InquiryItem";
import MentorProfileItem from "./MentorProfile/MentorProfileItem";
import * as mentorProfileApi from "../api/mentorProfileApi";
function SearchList() {
  const [searchResults, setSearchResults] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [mentor, setMentor] = useState([]);
  const navigate = useNavigate();
  const location = useLocation(); // 현재 위치 정보 얻기
  const queryParams = new URLSearchParams(location.search); // 쿼리 파라미터를 파싱
  const query = queryParams.get("query"); // 'query' 파라미터 값 추출

  const fetchMentorProfiles = async (query, page, size) => {
    try {
      setError(null);
      const response = await mentorProfileApi.searchMentorProfiles(
        query,
        page,
        size
      );
      console.log("API 응답 데이터:", response.data);
      setMentor(response.data.content || response.data);
    } catch (error) {
      console.error("검색 중 오류가 발생했습니다.", error);
      setError("검색 중 오류가 발생했습니다.");
    }
  };

  useEffect(() => {
    if (query) {
      const fetchSearchResults = async () => {
        setLoading(true);
        try {
          const response = await inquiryApi.searchInquiry(query, 0, 3); // API 호출
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

  useEffect(() => {
    if (!query) {
      console.warn("검색어가 비어 있습니다.");
      return;
    }
    fetchMentorProfiles(query, 1, 3);
  }, [query]);
  const handleViewMore = () => {
    // "더보기" 클릭 시 InquirySearch 페이지로 이동
    navigate(`/inquirySearchList?query=${query}`); // 쿼리 파라미터를 그대로 전달하여 이동
  };
  const handleMentorProfileViewMore = () => {
    // "더보기" 클릭 시 InquirySearch 페이지로 이동
    navigate(`/mentorprofile/search?query=${query}`); // 쿼리 파라미터를 그대로 전달하여 이동
  };
  return (
    <>
      <link
        href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
        rel="stylesheet"
      ></link>
      <div>
        <h1>검색 결과</h1>
        <h2>질문 검색 결과</h2>
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
              <button onClick={handleViewMore}>질문 검색 내용 더보기</button>
            </div>
          </div>
        ) : (
          <div>검색 결과가 없습니다.</div>
        )}
      </div>
      <div>
        <h2>멘토 검색 결과</h2>
        {error && <p style={{ color: "red" }}>{error}</p>}

        {mentor.length > 0 ? (
          <div>
            {mentor.slice(0, 3).map((mentor) => (
              <MentorProfileItem key={mentor.MentorProfileNo} mentor={mentor} />
            ))}
            <div className="view-more">
              <button onClick={handleMentorProfileViewMore}>더보기</button>
            </div>
          </div>
        ) : (
          <p>검색 결과가 없습니다.</p>
        )}
      </div>
    </>
  );
}

export default SearchList;
