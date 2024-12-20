import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../../css/mentorProfile.css'; // CSS 스타일 임포트

export default function MentorProfileItem({ mentor }) {
  const navigate = useNavigate();

  // 🔥 멘토 프로필 클릭 시 상세 페이지로 이동하는 함수
  const viewMentorProfile = () => {
    navigate(`/mentor-profile/${mentor.mentorProfileNo}`);
  };

  return (
    <div 
      className="mentor-profile-card" 
      onClick={viewMentorProfile} // 클릭하면 MentorProfileDetail 페이지로 이동
    >
      <img
        src={mentor.mentorImage || '/default-profile.png'} 
        alt="멘토 이미지" 
        className="mentor-profile-image" 
      />
      <h3 className="mentor-profile-name">{mentor.mentorCareer}</h3>
    </div>
  );
}
