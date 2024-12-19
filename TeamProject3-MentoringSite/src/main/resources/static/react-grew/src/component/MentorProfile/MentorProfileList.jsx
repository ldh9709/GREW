import React, { useState, useEffect } from 'react';
import '../../css/mentorProfile.css'; // ✅ CSS 경로
import { listMentorProfiles, listMentorsByFollowCount, listMentorsByMentoringCount, listMentorsByActivityCount } from '../../api/mentorProfileApi.js'; // ✅ API 경로

const MentorProfileList = () => {
  const [mentorProfiles, setMentorProfiles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [activePanel, setActivePanel] = useState('list');
  const [selectedMentor, setSelectedMentor] = useState(null);
  const [sortType, setSortType] = useState('follow'); // 🔥 정렬 타입 추가

  // 🔥 sortType이 변경될 때마다 fetchMentorProfiles()를 호출
  useEffect(() => {
    fetchMentorProfiles();
  }, [sortType]);

  const fetchMentorProfiles = async () => {
    try {
      setLoading(true);
      setError(null);

      let response;

      // 🔥 정렬 타입에 따라 API를 호출
      if (sortType === 'follow') {
        response = await listMentorsByFollowCount(0, 10); // 팔로우 순으로 정렬된 멘토 목록 가져오기
      } else if (sortType === 'mentoring') {
        response = await listMentorsByMentoringCount(0, 10); // 멘토링 횟수 순으로 정렬된 멘토 목록 가져오기
      } else if (sortType === 'activity') {
        response = await listMentorsByActivityCount(0, 10); // 활동 수 순으로 정렬된 멘토 목록 가져오기
      } else {
        response = await listMentorProfiles(); // 기본 목록 가져오기
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

  const viewMentorProfile = (profile) => {
    setSelectedMentor(profile);
    setActivePanel('profile');
  };

  const handleBackToList = () => {
    setActivePanel('list');
    setSelectedMentor(null);
  };

  // 🔥 라디오 버튼의 정렬 변경 이벤트 핸들러
  const handleRadioChange = (e) => {
    setSortType(e.target.value); // 정렬 기준을 업데이트하면 useEffect가 트리거됨
  };

  return (
    <div>
      <div className={`mentor-profile-panel ${activePanel ? 'open' : ''}`}>
        
        {activePanel === 'list' && (
          <div className="mentor-profile-list">
            <h2>멘토 프로필 목록</h2>

            {/* 🔥 라디오 버튼 추가 영역 */}
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
            {/* 🔥 라디오 버튼 추가 영역 끝 */}

            {loading && <p>로딩 중...</p>}
            {error && <p className="error-message">에러 발생: {error}</p>}

            <div className="profile-grid">
              {mentorProfiles?.length > 0 ? (
                mentorProfiles.map((mentor) => (
                  <div 
                    key={mentor.mentorProfileNo} 
                    className="mentor-profile-card" 
                    onClick={() => viewMentorProfile(mentor)}
                  >
                    <img 
                      src={mentor.mentorImage || '/default-profile.png'} 
                      alt="멘토 이미지" 
                      className="mentor-profile-image" 
                    />
                    <h3 className="mentor-profile-name">{mentor.mentorCareer}</h3>
                  </div>
                ))
              ) : (
                !loading && <p>멘토 프로필이 없습니다.</p>
              )}
            </div>
          </div>
        )}

        {activePanel === 'profile' && selectedMentor && (
          <div className="mentor-profile-detail">
            <button onClick={handleBackToList} className="back-button">목록으로</button>

            <img 
              src={selectedMentor.mentorImage || '/default-profile.png'} 
              alt="멘토 이미지" 
              className="mentor-profile-image-large" 
            />

            <h3>{selectedMentor.mentorIntroduce ?? '소개 정보 없음'}</h3>
            <p>경력: {selectedMentor.mentorCareer ?? '경력 정보 없음'}</p>
            <p>평점: {selectedMentor.mentorRating ?? '평점 없음'}</p>
          </div>
        )}
      </div>

      <div 
        className={`list-button ${activePanel === 'list' ? 'panel-open' : ''}`} 
        onClick={handleBackToList}
      >
        목록
      </div>
    </div>
  );
};

export default MentorProfileList;
