import React, { useEffect, useState } from "react";
import { useParams } from 'react-router-dom';
import Header from "../../layout/Header";
import Footer from "../../layout/Footer";
import Navigate from "../../layout/Navigate";
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

  if (loading) return <p>로딩 중...</p>;
  if (error) return <p className="error-message">{error}</p>;

  return (
    <>
      <div className="container">
        <section className="mentor-profile-info">
          <h2>멘토 프로필 정보</h2>
          <div className="mentor-profile">
            {/* 🔥 이미지 크기 조절 및 스타일 추가 */}
            <img src={mentorProfile?.mentorImage || '/default-profile.png'} alt="프로필 이미지" />
            <div className="profile-details">
              <h3>이름: {mentorProfile?.memberName}</h3>
              <p>경력: {mentorProfile?.mentorCareer}</p>
              <p>소개: {mentorProfile?.mentorIntroduce}</p>
            </div>
          </div>
        </section>
      </div>
    </>
  );
}
