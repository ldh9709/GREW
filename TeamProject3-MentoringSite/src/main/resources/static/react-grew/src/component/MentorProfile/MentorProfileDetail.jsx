import React, { useEffect, useState } from "react";
import { useParams } from 'react-router-dom';
import '../../css/mentorProfile.css'; // 🔥 추가된 CSS 파일
import { getMentorProfileByNo } from '../../api/mentorProfileApi.js';

export default function MentorProfileDetail() {
  const { mentorProfileNo } = useParams();
  const [mentorProfile, setMentorProfile] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchMentorProfile = async () => {
      try {
        setLoading(true);
        const response = await getMentorProfileByNo(mentorProfileNo);
        setMentorProfile(response.data);
      } catch (error) {
        setError('멘토 프로필을 가져오는 중 오류가 발생했습니다.');
      } finally {
        setLoading(false);
      }
    };

    fetchMentorProfile();
  }, [mentorProfileNo]);

  
  if (error) return <p className="error-message">{error}</p>;

  return (
    <div className="mentor-profile-detail-container">
      <div className="mentor-header">
        {/* 좌측: 이미지와 기본 정보 */}
        <div className="mentor-image-section">
          <img
            src={mentorProfile?.mentorImage || '/default-profile.png'}
            alt="프로필 이미지"
            className="mentor-profile-image-large"
          />
          <div className="mentor-basic-info">
            <h2>{mentorProfile?.memberName || "멘토 이름"}</h2>
            <p>{mentorProfile?.mentorCareer || "멘토 경력 정보 없음"}</p>
            <div className="mentor-stats">
              <span>멘토링 성공률: 72%</span>
              <span>멘토링 횟수: {mentorProfile?.mentorMentoringCount || 0}</span>
              <span>팔로워: {mentorProfile?.mentorFollowCount || 0}</span>
            </div>
                {/* 버튼 */}
               <div className="mentor-actions">
               <button className="follow-button">+ 팔로우</button>
               <button className="question-button">멘토에게 질문하기</button>
            </div>
          </div>
        </div>

        {/* 우측: 상세 정보 */}
        <div className="mentor-details-section">
          <div className="mentor-section">
            <h3>대표 멘토링 분야</h3>
            <p>{mentorProfile?.mentorSpecialty || "대표 멘토링 분야 정보 없음"}</p>
          </div>
          <div className="mentor-section">
            <h3>멘토 소개</h3>
            <p>{mentorProfile?.mentorIntroduce || "멘토 소개 정보 없음"}</p>
          </div>
          <div className="mentor-section">
            <h3>주요 경력</h3>
            <p>{mentorProfile?.mentorCareerDetail || "주요 경력 정보 없음"}</p>
          </div>
          <div className="mentor-section">
            <h3>기타 사항</h3>
            <p>{mentorProfile?.mentorOtherInfo || "기타 사항 정보 없음"}</p>
          </div>
        </div>
      </div>

    </div>
  );
}
