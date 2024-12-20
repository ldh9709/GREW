import React, { useEffect, useState } from "react";
import "../../css/styles.css"; 
import { useLocation } from "react-router-dom"; 
import { searchMentorProfile } from "../../api/mentorProfileApi"; 

const MentorSearchList = () => {
  const [searchResults, setSearchResults] = useState([]); 
  const [currentPage, setCurrentPage] = useState(1); 
  const [totalPages, setTotalPages] = useState(0); 
  const [itemsPerPage] = useState(5); 
  const [error, setError] = useState(null); 
  const location = useLocation(); 
  const queryParams = new URLSearchParams(location.search); 
  const search = queryParams.get("query"); // ✅ query로 수정

  const fetchMentorProfiles = async (query, page, size) => {
    try {
      setError(null); 
      const response = await searchMentorProfile(query, page, size); 
      console.log("API 응답 데이터:", response.data); 
      setSearchResults(response.data.content || response.data); 
      setTotalPages(response.data.totalPages); 
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
  const startPage = Math.floor((currentPage - 1) / pagesToShow) * pagesToShow + 1;
  const endPage = totalPages > 0 ? Math.min(startPage + pagesToShow - 1, totalPages) : 0;

  for (let i = startPage; i <= endPage; i++) {
    pageNumbers.push(i);
  }

  return (
    <>
      <div>
        <h2>멘토 검색 결과</h2>
        {error && <p style={{ color: 'red' }}>{error}</p>}

        {searchResults.length > 0 ? (
          <div>
            {searchResults.map((mentor) => (
              <div key={mentor.mentorProfileNo} className="mentor-card">
                <img 
                  src={mentor.mentorImage || '/default-profile.png'} 
                  alt="멘토 이미지" 
                  className="mentor-profile-image" 
                />
                <h3>{mentor.mentorCareer}</h3>
                <p>{mentor.mentorIntroduce}</p>
                <p>평점: {mentor.mentorRating ?? '평점 없음'}</p>
              </div>
            ))}
          </div>
        ) : (
          <p>검색 결과가 없습니다.</p>
        )}

        <div className="pagination">
          {pageNumbers.map((number) => (
            <button key={number} onClick={() => paginate(number)}>
              {number}
            </button>
          ))}
        </div>
      </div>
    </>
  );
};

export default MentorSearchList;
