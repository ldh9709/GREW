import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "../../css/mentorProfile.css"; // 🔥 추가된 CSS 파일
import { getMentorProfileByNo } from "../../api/mentorProfileApi.js";
import { listReviewByMember } from "../../api/reviewApi.js"; // 리뷰 목록 API 추가
import { getCookie } from "../../util/cookieUtil.js";
import { jwtDecode } from "jwt-decode";

export default function MentorProfileDetail() {
  const { mentorProfileNo } = useParams();
  const [mentorProfile, setMentorProfile] = useState(null);
  const [reviews, setReviews] = useState([]); // 빈 배열로 초기화
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // 쿠키에서 member 객체를 가져와 JWT 토큰을 추출
  const memberCookie = getCookie("member");
  const token = memberCookie ? memberCookie.accessToken : null; // 여기서 accessToken을 정확히 추출해야 함
  const decodeToken = token ? jwtDecode(token) : null;

  useEffect(() => {
    const fetchMentorProfile = async () => {
      try {
        setLoading(true);

        // 1. 멘토 프로필 조회
        const mentorProfileResponse = await getMentorProfileByNo(
          mentorProfileNo
        );
        setMentorProfile(mentorProfileResponse.data);

        console.log("Decoded Token:", decodeToken); // 디코딩된 토큰 정보 확인

        // 2. 멘토 프로필 번호로 리뷰 목록 조회 (Authorization 헤더에 JWT 토큰 추가)
        const reviewsResponse = await listReviewByMember(
          mentorProfileNo, // memberNo 대신 mentorProfileNo를 바로 사용
          0,
          10,
          token // `token`을 Authorization 헤더에 포함시켜야 함
        );

        console.log("Reviews Response:", reviewsResponse);
        console.log("Reviews Response Data:", reviewsResponse.data); // 전체 데이터 확인
        console.log(
          "Reviews Response Data.content:",
          reviewsResponse.data?.content
        ); // content만 따로 확인

        // Check if there are reviews in the response
        if (reviewsResponse.data) {
          setReviews(reviewsResponse.data); // content 배열 처리
        } else {
          setReviews([]); // 데이터가 없으면 빈 배열 처리
        }
      } catch (error) {
        setError("멘토 프로필을 가져오는 중 오류가 발생했습니다.");
      } finally {
        setLoading(false);
      }
    };
    fetchMentorProfile();
  }, [mentorProfileNo, token]);
  console.log("Reviews:", reviews); // 이 줄을 추가하여 reviews 데이터를 확인

  if (loading) return <p>로딩 중...</p>;
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
            <h2>{mentorProfile?.memberName || "멘토 이름"}</h2>
            <p>{mentorProfile?.mentorCareer || "멘토 경력 정보 없음"}</p>
            <div className="mentor-stats">
              <span>멘토링 성공률: 72%</span>
              <span>
                멘토링 횟수: {mentorProfile?.mentorMentoringCount || 0}
              </span>
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
            <p>
              {mentorProfile?.mentorSpecialty || "대표 멘토링 분야 정보 없음"}
            </p>
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

      {/* 리뷰 목록 */}
      <div className="mentor-reviews">
        <h3>멘토 리뷰</h3>
        {reviews.length > 0 ? (
          <ul>
            {reviews.map((review) => (
              <li key={review.reviewNo}>
                <p>
                  <strong>{review.menteeName || "작성자 이름"}</strong> 님의
                  리뷰
                </p>
                <p>{review.reviewContent || "리뷰 내용이 없습니다."}</p>
                <p>평점: {review.reviewScore || "없음"}</p>
              </li>
            ))}
          </ul>
        ) : (
          <p>리뷰가 없습니다.</p> // 리뷰가 없으면 해당 메시지를 표시
        )}
      </div>
    </div>
  );
}
