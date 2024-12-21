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
  const navigate = useNavigate();

  useEffect(() => {
    fetchMentorProfiles();
  }, [sortType, currentPage]);

  const fetchMentorProfiles = async () => {
    try {
      setLoading(true);
      setError(null);
      let response;
      if (sortType === "follow") {
        response = await listMentorsByFollowCount(currentPage, 12);
      } else if (sortType === "mentoring") {
        response = await listMentorsByMentoringCount(currentPage, 12);
      } else if (sortType === "activity") {
        response = await listMentorsByActivityCount(currentPage, 12);
      } else {
        response = await listMentorProfiles(currentPage, 12);
      }

      if (response?.data?.content) {
        setMentorProfiles(response.data.content);
        setTotalPages(response.data.totalPages);
      } else {
        throw new Error("멘토 프로필을 불러오는 중 오류가 발생했습니다.");
      }
    } catch (err) {
      setError(err.message ?? "알 수 없는 오류가 발생했습니다.");
    } finally {
      setLoading(false);
    }
  };

  const handleRadioChange = (e) => {
    setSortType(e.target.value);
    setCurrentPage(0);
  };

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const renderPagination = () => {
    const pageNumbers = [];
    for (let i = 0; i < totalPages; i++) {
      pageNumbers.push(i);
    }

    return (
      <div className="pagenation">
        {currentPage > 0 && (
          <button onClick={() => handlePageChange(currentPage - 1)}>이전</button>
        )}
        {pageNumbers.map((number) => (
          <button
            key={number}
            onClick={() => handlePageChange(number)}
            style={{
              backgroundColor: number === currentPage ? "#4CAF50" : "",
              color: number === currentPage ? "white" : "",
            }}
          >
            {number + 1}
          </button>
        ))}
        {currentPage < totalPages - 1 && (
          <button onClick={() => handlePageChange(currentPage + 1)}>다음</button>
        )}
      </div>
    );
  };

  return (
    <div>
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
        {error && <p className="error-message">에러 발생: {error}</p>}
        <div className="profile-grid">
          {mentorProfiles?.length > 0 ? (
            mentorProfiles.map((mentor) => (
              <MentorProfileItem
                key={mentor.mentorProfileNo}
                mentor={mentor}
              />
            ))
          ) : (
            !loading && <p>멘토 프로필이 없습니다.</p>
          )}
        </div>
        {!loading && totalPages > 0 && renderPagination()}
      </div>
    </div>
  );
};

export default MentorProfileList;
