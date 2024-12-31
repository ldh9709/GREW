import React, { useEffect, useState } from "react";
import "../../css/styles.css";
import { useLocation } from "react-router-dom";
import * as mentorProfileApi from "../../api/mentorProfileApi";
import MentorProfileItem from "./MentorProfileItem"; // 🔥 MentorProfileItem import 추가
import PagenationItem from "../PagenationItem";

const MentorSearchList = () => {
  const [searchResults, setSearchResults] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const [itemsPerPage] = useState(8);
  const [error, setError] = useState(null);
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const search = queryParams.get("query"); // ✅ query로 수정

  const fetchMentorProfiles = async (query, page, size) => {
    try {
      setError(null);
      const response = await mentorProfileApi.searchMentorProfiles(
        query,
        page,
        size
      );
      console.log("API 응답 데이터:", response.data);
      setSearchResults(response.data.content || response.data);
      setTotalPages(response.data.totalPages);
      console.log(response);
    } catch (error) {
      console.error("검색 중 오류가 발생했습니다.", error);
      setError("검색 중 오류가 발생했습니다.");
    }
  };

  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  useEffect(() => {
    if (!search) {
      console.warn("검색어가 비어 있습니다.");
      return;
    }
    fetchMentorProfiles(search, currentPage - 1, itemsPerPage);
  }, [search, currentPage, itemsPerPage]);

  const pageNumbers = [];
  const pagesToShow = 5;
  const startPage =
    Math.floor((currentPage - 1) / pagesToShow) * pagesToShow + 1;
  const endPage =
    totalPages > 0 ? Math.min(startPage + pagesToShow - 1, totalPages) : 0;

  for (let i = startPage; i <= endPage; i++) {
    pageNumbers.push(i);
  }

  return (
    <>
      <div>
        <h2>멘토 검색 결과</h2>
        {error && <p style={{ color: "red" }}>{error}</p>}

        {searchResults.length > 0 ? (
          <div className="profile-container">
            {searchResults.map((mentor) => (
              <MentorProfileItem key={mentor} mentor={mentor} />
            ))}
          </div>
        ) : (
          <p>검색 결과가 없습니다.</p>
        )}

        <PagenationItem
          currentPage={currentPage}
          totalPages={totalPages}
          paginate={paginate}
        />
      </div>
    </>
  );
};

export default MentorSearchList;
