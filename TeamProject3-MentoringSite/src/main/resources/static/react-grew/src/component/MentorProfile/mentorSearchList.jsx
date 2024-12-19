import React, { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom"; // 🔥 URL의 query 파라미터 가져오기
import { searchMentorProfile } from "../../api/MentorProfileApi"; // 🔥 멘토 검색 API 메서드

const MentorSearchList = () => {
  const [searchParams] = useSearchParams();
  const [searchResults, setSearchResults] = useState([]);
  const query = searchParams.get("query"); // 🔥 query 파라미터 가져오기

  useEffect(() => {
    if (!query) return;

    const fetchMentorProfiles = async () => {
      try {
        const response = await searchMentorProfile(query, 0, 10); // 🔥 API 호출
        setSearchResults(response.data.content);
      } catch (error) {
        console.error("검색 중 오류가 발생했습니다.", error);
      }
    };

    fetchMentorProfiles();
  }, [query]);

  return (
    <div>
      <h2>멘토 검색 결과</h2>
      {searchResults.length > 0 ? (
        <ul>
          {searchResults.map((mentor) => (
            <li key={mentor.mentorProfileNo}>{mentor.mentorCareer}</li>
          ))}
        </ul>
      ) : (
        <p>검색 결과가 없습니다.</p>
      )}
    </div>
  );
};

export default MentorSearchList;
