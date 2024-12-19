import React, { useState, useEffect } from 'react';
import '../../css/mentorProfile.css'; // ✅ CSS 경로
import { listMentorProfiles, getMentorProfile } from '../../api/MentorProfileApi.js'; // ✅ API 경로

const MentorProfileList = () => {
  const [mentorProfiles, setMentorProfiles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [activePanel, setActivePanel] = useState('list');
  const [selectedMentor, setSelectedMentor] = useState(null);

  useEffect(() => {
    fetchMentorProfiles();
  }, []);

  const fetchMentorProfiles = async () => {
    try {
      setLoading(true);
      setError(null);
      const response = await listMentorProfiles();
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

  return (
    <div>
      <div className={`mentor-profile-panel ${activePanel ? 'open' : ''}`}>
        {activePanel === 'list' && (
          <div className="mentor-profile-list">
            <h2>멘토 프로필 목록</h2>

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
