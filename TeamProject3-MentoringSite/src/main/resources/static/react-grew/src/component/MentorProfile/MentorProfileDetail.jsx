import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "../../css/mentorProfile.css"; // CSS 파일
import * as mentorProfileApi from "../../api/mentorProfileApi";
import * as categoryApi from "../../api/categoryApi"; // categoryApi 가져오기

export default function MentorProfileDetail() {
  const { mentorProfileNo } = useParams();
  const [mentorProfile, setMentorProfile] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [categoryName, setCategoryName] = useState("카테고리 정보 없음"); // 매칭되는 카테고리 이름

  useEffect(() => {
    const fetchMentorProfile = async () => {
      try {
        setLoading(true);
        const response = await mentorProfileApi.getMentorProfileByNo(mentorProfileNo);
        setMentorProfile(response.data);

        // 멘토 프로필 데이터에서 categoryNo 가져오기
        if (response.data.categoryNo) {
          fetchCategoryName(response.data.categoryNo);
        }
      } catch (error) {
        setError("멘토 프로필을 가져오는 중 오류가 발생했습니다.");
      } finally {
        setLoading(false);
      }
    };

    fetchMentorProfile();
  }, [mentorProfileNo]);

  const fetchCategoryName = async (categoryNo) => {
    try {
      // 모든 카테고리 데이터 가져오기
      const response = await categoryApi.ListCategory(); // 모든 카테고리 API 호출
      const allCategories = response.data || [];

      // 현재 mentorProfile의 categoryNo와 매칭되는 categoryName 찾기
      const matchingCategory = allCategories.find((cat) => cat.categoryNo === categoryNo);
      setCategoryName(matchingCategory ? matchingCategory.categoryName : "카테고리 정보 없음");
    } catch (error) {
      console.error("카테고리 정보를 가져오는 중 오류 발생:", error);
      setCategoryName("카테고리 정보 없음");
    }
  };

  if (error) return <p className="error-message">{error}</p>;

  return (
    <div className="mentor-profile-detail-container">
      <div className="mentor-header">
        {/* 좌측: 이미지와 기본 정보 */}
        <div className="mentor-image-section">
          <img
            src={mentorProfile?.mentorImage || "/default-profile.png"}
            alt="프로필 이미지"
            className="mentor-profile-image-large"
          />
          <div className="mentor-basic-info">
            <h2>{mentorProfile?.memberName || "회원 이름 없음"}</h2>
            <p>{mentorProfile?.mentorCareer || "멘토 경력 정보 없음"}</p>
            <div className="mentor-stats">
              <span>멘토링 성공률: 72%</span>
              <span>멘토링 횟수: {mentorProfile?.mentorMentoringCount || 0}</span>
              <span>팔로워: {mentorProfile?.mentorFollowCount || 0}</span>
            </div>
          </div>
        </div>

        {/* 우측: 상세 정보 */}
        <div className="mentor-details-section">
          <div className="mentor-section">
            <h3>대표 멘토링 분야</h3>
            <p>{categoryName}</p> {/* 매칭된 카테고리 이름 표시 */}
          </div>
          <div className="mentor-section">
            <h3>멘토 소개</h3>
            <p>{mentorProfile?.mentorIntroduce || "멘토 소개 정보 없음"}</p>
          </div>
          <div className="mentor-section">
            <h3>주요 경력</h3>
            <p>{mentorProfile?.mentorCareer || "멘토 경력 정보 없음"}</p>
          </div>
        </div>
      </div>
    </div>
  );
}
