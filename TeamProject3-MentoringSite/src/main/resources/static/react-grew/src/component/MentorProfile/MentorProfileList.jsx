import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../../css/mentorProfile.css';
import { 
  listMentorProfiles, 
  listMentorsByFollowCount, 
  listMentorsByMentoringCount, 
  listMentorsByActivityCount 
} from '../../api/mentorProfileApi.js';
import MentorProfileItem from './MentorProfileItem'; // 🔥 MentorProfileItem 임포트

const MentorProfileList = () => {
  const [mentorProfiles, setMentorProfiles] = useState([]); // 멘토 프로필 목록
  const [loading, setLoading] = useState(true); // 로딩 상태
  const [error, setError] = useState(null); // 에러 상태
  const [sortType, setSortType] = useState('follow'); // 🔥 정렬 타입 추가
  const navigate = useNavigate(); 

  // 🔥 sortType이 변경될 때마다 fetchMentorProfiles() 호출
  useEffect(() => {
    fetchMentorProfiles();
  }, [sortType]);

  const fetchMentorProfiles = async () => {
    try {
      setLoading(true);
      setError(null);

      let response;

      // 🔥 정렬 타입에 따라 API 호출
      if (sortType === 'follow') {
        response = await listMentorsByFollowCount(0, 10); 
      } else if (sortType === 'mentoring') {
        response = await listMentorsByMentoringCount(0, 10); 
      } else if (sortType === 'activity') {
        response = await listMentorsByActivityCount(0, 10); 
      } else {
        response = await listMentorProfiles(); 
      }

      if (response?.data?.content) {
        setMentorProfiles(response.data.content);
      } else {
        throw new Error('멘토 프로필을 불러오는 중 오류가 발생했습니다.');
      }
    } catch (err) {
      setError(err.message ?? '알 수 없는 오류가 발생했습니다.');
    } finally {
      setLoading(false);
    }
  };

  // 🔥 라디오 버튼의 정렬 변경 이벤트 핸들러
  const handleRadioChange = (e) => {
    setSortType(e.target.value);
  };

  return (
    <div>
      <div className="mentor-profile-list">
        <h2>멘토 프로필 목록</h2>

        {/* 🔥 정렬 버튼 */}
        <div style={{ marginTop: "20px", marginBottom: "20px" }}>
          <div className="radio-container">
            <label style={{ marginRight: "10px" }}>
              <input
                type="radio"
                name="sortType"
                value="follow"
                checked={sortType === 'follow'}
                onChange={handleRadioChange}
              />
              팔로우 순
            </label>

            <label style={{ marginRight: "10px" }}>
              <input
                type="radio"
                name="sortType"
                value="mentoring"
                checked={sortType === 'mentoring'}
                onChange={handleRadioChange}
              />
              멘토링 횟수 순
            </label>

            <label>
              <input
                type="radio"
                name="sortType"
                value="activity"
                checked={sortType === 'activity'}
                onChange={handleRadioChange}
              />
              활동 수 순
            </label>
          </div>
        </div>

        {loading && <p>로딩 중...</p>}
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
      </div>
    </div>
  );
};

export default MentorProfileList;
