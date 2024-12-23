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

const MentorProfileList = () => {
  const [mentorProfiles, setMentorProfiles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [sortType, setSortType] = useState("follow");
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

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
    <div className="mentor-profile-list">
      <h1>멘토 프로필 목록</h1>
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
        {!loading && mentorProfiles.length > 0 ? (
          mentorProfiles.map((mentor) => (
            <MentorProfileItem key={mentor.mentorProfileNo} mentor={mentor} />
          ))
        ) : (
          !loading && <p>멘토 프로필이 없습니다.</p>
        )}
      </div>
      {!loading && totalPages > 0 && renderPagination()}
    </div>
  );
};

export default MentorProfileList;