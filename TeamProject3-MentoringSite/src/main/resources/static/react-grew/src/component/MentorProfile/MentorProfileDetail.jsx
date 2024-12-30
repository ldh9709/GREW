import { useMemberAuth } from "../../util/AuthContext";
import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom"; // useNavigate 추가
import "../../css/mentorProfile.css"; // 🔥 추가된 CSS 파일
import { getMentorProfileByNo } from "../../api/mentorProfileApi.js";
import { listReviewByMember } from "../../api/reviewApi.js"; // 리뷰 목록 API 추가
import * as categoryApi from "../../api/categoryApi";
import * as mentorBoardApi from "../../api/mentorBoardApi";
import MentorProfileInfo from "./MentorProfileInfo.jsx";
import MentorBoardItem from "../MentorBoard/MentorBoardItem";

export default function MentorProfileDetail() {
  const { token } = useMemberAuth();
  const { mentorProfileNo } = useParams();
  const navigate = useNavigate(); // navigate 훅 추가
  const [mentorProfile, setMentorProfile] = useState({});
  const [reviews, setReviews] = useState([]); // 빈 배열로 초기화
  const [boards, setBoards] = useState([]); // 멘토 보드 상태
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [categoryName, setCategoryName] = useState("카테고리 정보 없음");

  // 멘토 프로필 데이터 가져오기
  const fetchMentorProfile = async () => {
    try {
      setLoading(true);

      // 1. 멘토 프로필 조회
      const mentorProfileResponse = await getMentorProfileByNo(mentorProfileNo);
      if (!mentorProfileResponse?.data || Object.keys(mentorProfileResponse.data).length === 0) {
        console.warn("Invalid mentor profile number:", mentorProfileNo);
        navigate("/mentor-profile/list", { replace: true });
        return;
      }
      setMentorProfile(mentorProfileResponse.data);

      // 2. 리뷰 목록 조회
      const reviewsResponse = await listReviewByMember(mentorProfileNo, 0, 5, token);
      if (reviewsResponse?.data) {
        setReviews(reviewsResponse.data.content);
      } else {
        setReviews([]);
      }

      // 3. 카테고리 이름 가져오기
      if (mentorProfileResponse.data.categoryNo) {
        fetchCategoryName(mentorProfileResponse.data.categoryNo);
      }
    } catch (error) {
      setError("멘토 프로필을 가져오는 중 오류가 발생했습니다.");
      navigate("/mentor-profile/list", { replace: true });
    } finally {
      setLoading(false);
    }
  };

  // 멘토 보드 데이터 가져오기
  const fetchMentorBoards = async () => {
    try {
      const response = await mentorBoardApi.listMentorBoardsByProfile(mentorProfileNo, 0, 5);
      setBoards(response?.data?.content || []);
    } catch (error) {
      console.error("멘토 보드 데이터를 가져오는 중 오류 발생:", error);
    }
  };

  useEffect(() => {
    fetchMentorProfile();
    fetchMentorBoards();
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

  if (loading) return <p>로딩 중...</p>;
  if (error) return <p className="error-message">{error}</p>;

  return (
    <div className="mentor-profile-detail-container">
      <div className="mentor-header">
        {/* 좌측: 멘토 개별 정보*/}
        <MentorProfileInfo mentorProfile={mentorProfile} />

        {/* 우측: 상세 정보 */}
        <div className="mentor-details-section">
          <span className="mentor-category-box">{categoryName}</span>
          <div className="mentor-section mentor-headline">
            <h2>"{mentorProfile.mentorHeadline}"</h2>
          </div>
          <div className="mentor-section">
            <h2>멘토 소개</h2>
            <pre>{mentorProfile?.mentorIntroduce || "멘토 소개 정보 없음"}</pre>
          </div>
          <div className="mentor-section">
            <h2>주요 경력</h2>
            <pre>{mentorProfile?.mentorCareer || "멘토 경력 정보 없음"}</pre>
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
                  <strong>{review.menteeName || "작성자 이름"}</strong> 님의 리뷰
                </p>
                <p>{review.reviewContent || "리뷰 내용이 없습니다."}</p>
                <p>평점: {review.reviewScore || "없음"}</p>
              </li>
            ))}
          </ul>
        ) : (
          <p>리뷰가 없습니다.</p>
        )}
      </div>

      {/* 멘토 보드 목록 */}
      <div className="mentor-boards">
        <h3>멘토 보드</h3>
        {boards.length > 0 ? (
          boards.map((board) => (
            <MentorBoardItem
              key={board.mentorBoardNo}
              board={board}
              onClick={() => navigate(`/mentor-board/detail/${board.mentorBoardNo}`)}
            />
          ))
        ) : (
          <p>등록된 멘토 보드가 없습니다.</p>
        )}
      </div>
    </div>
  );
}
