import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import * as inquiryApi from "../api/inquiryApi";
import * as mentorProfileApi from "../api/mentorProfileApi";
import * as mentorBoardApi from "../api/mentorBoardApi";
import InquiryItem from "./AnswerInquiry/InquiryItem";
import MentorProfileItem from "./MentorProfile/MentorProfileItem";
import MentorBoardItem from "./MentorBoard/MentorBoardItem";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleChevronRight } from "@fortawesome/free-solid-svg-icons";

function SearchList() {
  const [searchResults, setSearchResults] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [mentorBoards, setMentorBoards] = useState([]);
  const [mentorProfiles, setMentorProfiles] = useState([]);
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const query = queryParams.get("query");

  // 멘토 프로필 검색 함수
  const fetchMentorProfiles = async (query) => {
    setLoading(true);
    try {
      const response = await mentorProfileApi.searchMentorProfiles(query, 0, 4);
      setMentorProfiles(response.data.content);
      setError(null);
    } catch (err) {
      setError("멘토 프로필 검색 중 오류가 발생했습니다.");
    } finally {
      setLoading(false);
    }
  };

  // 멘토 보드 검색 함수
  const fetchMentorBoards = async (query) => {
    setLoading(true); // 멘토 보드 검색에 대한 로딩 상태
    try {
      const response = await mentorBoardApi.searchMentorBoards(query, 0, 4);
      console.log("멘토 보드 API 응답 데이터:", response.data); // 여기서 API 응답 확인
      setMentorBoards(response.data.content); // 응답 데이터에서 content만 저장
      setError(null); // 에러 상태 초기화
    } catch (err) {
      setError("멘토 보드 검색 중 오류가 발생했습니다."); // 명확한 에러 메시지
    } finally {
      setLoading(false); // 로딩 상태 해제
    }
  };

  // 질문 검색
  useEffect(() => {
    if (query) {
      const fetchSearchResults = async () => {
        setLoading(true);
        try {
          const response = await inquiryApi.searchInquiry(query, 0, 3);
          setSearchResults(response.data.content);
          setError(null);
        } catch (err) {
          setError("검색 중 오류가 발생했습니다.");
        } finally {
          setLoading(false);
        }
      };
      fetchSearchResults();
    }
  }, [query]);

  // 멘토 프로필 검색
  useEffect(() => {
    if (query) {
      fetchMentorProfiles(query);
    }
  }, [query]);

  // 멘토 보드 검색 useEffect
  useEffect(() => {
    if (query) {
      fetchMentorBoards(query); // 멘토 보드 검색 함수 호출
    }
  }, [query]);

  const handleViewMoreInquiry = () => {
    navigate(`/inquiry/search?query=${query}`);
  };

  const handleViewMoreMentorProfiles = () => {
    navigate(`/mentor-profile/search?query=${query}`);
  };

  const handleViewMoreMentorBoards = () => {
    navigate(`/mentor-board/search?query=${query}`);
  };

  return (
    <>
      <link
        href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
        rel="stylesheet"
      ></link>
      <div>
        <h1>검색 결과</h1>

        {/* 질문 검색 결과 */}
        <h2>질문 검색 결과</h2>
        {loading && <div>로딩 중...</div>}
        {error && <div>{error}</div>}
        {searchResults.length > 0 ? (
          <div>
            {searchResults.map((inquiry) => (
              <InquiryItem key={inquiry.inquiryNo} inquiry={inquiry} />
            ))}
            <div className="view-more">
              <a href="" onClick={handleViewMoreInquiry}>
                질문 검색 내용 더 보기 <FontAwesomeIcon icon={faCircleChevronRight} />
              </a>
            </div>
          </div>
        ) : (
          <p>검색 결과가 없습니다.</p>
        )}

        {/* 멘토 프로필 검색 결과 */}
        <h2>멘토 프로필 검색 결과</h2>
        {loading && <div>로딩 중...</div>}
        {error && <div>{error}</div>}
        {mentorProfiles.length > 0 ? (
          <div className="profile-container">
            {mentorProfiles.map((profile) => (
              <MentorProfileItem
                key={profile.mentorProfileNo}
                mentor={profile}
              />
            ))}
            <div className="view-more">
              <a className="view-more-button" href="" onClick={handleViewMoreMentorProfiles}>
                멘토 프로필 검색 내용 더 보기{" "}
                <FontAwesomeIcon icon={faCircleChevronRight} />
              </a>
            </div>
          </div>
        ) : (
          <p>검색 결과가 없습니다.</p>
        )}

        {/* 멘토 보드 검색 결과 */}
        <h2>멘토 컨텐츠 검색 결과</h2>
        {loading && <div>로딩 중...</div>}
        {error && <div>{error}</div>}
        {mentorBoards.length > 0 ? (

          <div className="mentor-board-list-main">
            {mentorBoards.map((board) => (
              <MentorBoardItem key={board.mentorBoardNo} board={board} />
            ))}
            <div className="view-more">
              <a className="view-more-button" href="" onClick={handleViewMoreMentorBoards}>
                멘토 컨텐츠 검색 내용 더 보기{" "}
                <FontAwesomeIcon icon={faCircleChevronRight} />
              </a>
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
