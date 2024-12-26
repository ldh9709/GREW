import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"; // useNavigate를 추가
import * as reviewApi from "../../api/reviewApi"; // 리뷰 API
import "../../css/review.css"; // CSS 파일 임포트

export default function ReviewListPage() {
  const [reviews, setReviews] = useState([]); // 리뷰 목록 상태
  const [loading, setLoading] = useState(true); // 로딩 상태
  const navigate = useNavigate(); // useNavigate 훅 사용

  // 리뷰 목록 데이터 불러오기
  useEffect(() => {
    const fetchReviews = async () => {
      try {
        const response = await reviewApi.listReviewAll();
        console.log(response); // 응답을 콘솔로 확인
        if (response && Array.isArray(response.data.content)) {
          setReviews(response.data.content);
        } else {
          console.error(
            "리뷰 목록 데이터가 배열이 아닙니다:",
            response.data.content
          );
          setReviews([]); // 빈 배열로 설정
        }
      } catch (error) {
        console.error("리뷰 목록 불러오기 실패:", error);
        setReviews([]); // 오류 발생 시 빈 배열로 설정
      } finally {
        setLoading(false); // 로딩 상태 종료
      }
    };

    fetchReviews();
  }, []);

  // 데이터가 없거나 로딩 중일 때 처리
  if (loading) {
    return <div>Loading...</div>;
  }

  if (!reviews || reviews.length === 0) {
    return <div>리뷰가 없습니다.</div>;
  }

  // 리뷰 클릭 시 상세 페이지로 이동
  const handleReviewClick = (reviewNo) => {
    navigate(`/review/${reviewNo}`); // reviewNo를 URL로 넘겨서 상세 페이지로 이동
  };

  // 별점 생성 함수
  const renderStars = (score) => {
    const stars = [];
    for (let i = 0; i < 5; i++) {
      if (i < score) {
        stars.push(<span key={i} className="star filled">★</span>);
      } else {
        stars.push(<span key={i} className="star">★</span>);
      }
    }
    return stars;
  };

  return (
    <div className="review-list-container">
      <h1>전체 리뷰 목록</h1>
      <ul className="review-list">
        {reviews.map((review) => (
          <li
            key={review.reviewNo}
            onClick={() => handleReviewClick(review.reviewNo)}
            className="review-item"
          >
            <h3>{review.reviewTitle}</h3>
            <p>{review.reviewContent}</p>
            <div className="review-stars">
              {renderStars(review.reviewScore)} {/* 별점 표시 */}
            </div>
            <p>작성일: {new Date(review.reviewDate).toLocaleDateString()}</p>
            <p>작성자 번호: {review.memberNo}</p>
            <p>멘티 이름: {review.menteeName}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}
