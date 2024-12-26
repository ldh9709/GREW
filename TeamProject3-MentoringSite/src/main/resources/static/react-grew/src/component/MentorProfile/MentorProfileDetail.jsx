import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "../../css/mentorProfile.css"; // CSS 파일
import * as mentorProfileApi from "../../api/mentorProfileApi";
import * as categoryApi from "../../api/categoryApi"; 
import * as memberApi from "../../api/memberApi";

export default function MentorProfileDetail() {
  const { mentorProfileNo } = useParams();
  const [mentorProfile, setMentorProfile] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [categoryName, setCategoryName] = useState("카테고리 정보 없음");
  const [memberName, setMemberName] = useState(""); // 멤버 이름 상태 추가

  useEffect(() => {
    const fetchMentorProfile = async () => {
      try {
        setLoading(true);
        const response = await mentorProfileApi.getMentorProfileByNo(mentorProfileNo);
        setMentorProfile(response.data);

        // 멘토 프로필 데이터에서 categoryNo와 memberNo 가져오기
        if (response.data.categoryNo) {
          fetchCategoryName(response.data.categoryNo);
        }
        if (response.data.memberNo) {
          fetchMemberName(response.data.memberNo); // 멤버 이름 가져오기
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
      const response = await categoryApi.ListCategory(); 
      const allCategories = response.data || [];
      const matchingCategory = allCategories.find((cat) => cat.categoryNo === categoryNo);
      setCategoryName(matchingCategory ? matchingCategory.categoryName : "카테고리 정보 없음");
    } catch (error) {
      console.error("카테고리 정보를 가져오는 중 오류 발생:", error);
      setCategoryName("카테고리 정보 없음");
    }
  };

  const fetchMemberName = async (memberNo) => {
    try {
      const response = await memberApi.getMemberByMemberNo(memberNo); // 멤버 API 호출
      setMemberName(response.data.memberName );
    } catch (error) {
      console.error("멤버 이름 정보를 가져오는 중 오류 발생:", error);
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
            <h2>{memberName}</h2> {/* 멤버 이름 표시 */}
            <p>{mentorProfile?.mentorCareer || "멘토 경력 정보 없음"}</p>
            <div className="mentor-stats">
              멘토링 성공률:{" "}
              {mentorProfile?.mentorActivityCount
                ? Math.round((mentorProfile.mentorMentoringCount / mentorProfile.mentorActivityCount) * 100)
                : 0}
              %
              <span>멘토링 횟수: {mentorProfile?.mentorMentoringCount || 0}</span>
              <span>팔로워: {mentorProfile?.mentorFollowCount || 0}</span>
            </div>
          </div>
        </div>
        {/* 우측: 상세 정보 */}
        <div className="mentor-details-section">
          <div className="mentor-section">
            <h3>대표 멘토링 분야</h3>
            <p>{categoryName}</p>
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
