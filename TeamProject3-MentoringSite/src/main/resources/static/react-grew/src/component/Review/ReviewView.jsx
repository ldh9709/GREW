import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom"; // useParams를 사용하여 URL 파라미터 받기
import * as reviewApi from "../../api/reviewApi"; // 리뷰 API

export default function ReviewDetailPage() {
  const { reviewNo } = useParams(); // URL에서 reviewNo를 파라미터로 받음
  const [review, setReview] = useState(null); // 리뷰 데이터 상태
  const [loading, setLoading] = useState(true); // 로딩 상태
  const [error, setError] = useState(null); // 에러 상태

  // 리뷰 데이터 가져오기
  useEffect(() => {
    const fetchReviewDetail = async () => {
      try {
        const response = await reviewApi.viewReview(reviewNo); // 리뷰 상세 데이터 가져오기
        setReview(response.data); // 리뷰 데이터를 상태에 저장
      } catch (err) {
        setError("리뷰를 가져오는 데 실패했습니다.");
      } finally {
        setLoading(false); // 로딩 상태 종료
      }
    };

    fetchReviewDetail();
  }, [reviewNo]); // 리뷰 번호가 변경될 때마다 데이터 다시 가져오기

  // 로딩 중일 때
  if (loading) {
    return <div>로딩 중...</div>;
  }

  // 에러 발생 시
  if (error) {
    return <div>{error}</div>;
  }

  // 리뷰가 없을 때
  if (!review) {
    return <div>해당 리뷰를 찾을 수 없습니다.</div>;
  }

  // 별점 표시 함수
  const renderStars = (score) => {
    const filledStars = Math.round(score); // 점수를 반올림하여 별을 채우기
    const emptyStars = 5 - filledStars;
    return (
      <div>
        {[...Array(filledStars)].map((_, index) => (
          <span key={index} className="star filled">★</span>
        ))}
        {[...Array(emptyStars)].map((_, index) => (
          <span key={index + filledStars} className="star">★</span>
        ))}
      </div>
    );
  };

  return (
    <div className="review-detail-container">
      <h2>리뷰 자세히 보기</h2>
      <div className="review-detail">
        <h3>{review.reviewTitle}</h3> {/* 리뷰 제목 */}
        <p>{review.reviewContent}</p> {/* 리뷰 내용 */}
        <div className="review-score">
          <span>점수: </span>
          {renderStars(review.reviewScore)} {/* 별점 표시 */}
        </div>
        <div>
          <span>상태: {review.reviewStatus === 1 ? "활성" : "비활성"}</span> {/* 리뷰 상태 */}
        </div>
        <div>
          <span>작성일: {new Date(review.reviewDate).toLocaleDateString()}</span> {/* 리뷰 작성일 */}
        </div>
        <div>
          <span>작성자 번호: {review.memberNo}</span> {/* 작성자 번호 */}
        </div>
        <div>
          <span>멘티 이름: {review.menteeName}</span> {/* 멘티 이름 */}
        </div>
      </div>
    </div>
  );
}
