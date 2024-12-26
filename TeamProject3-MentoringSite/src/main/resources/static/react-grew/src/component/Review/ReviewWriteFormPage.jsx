import React, { useState } from "react";
import * as reviewApi from "../../api/reviewApi"; // 리뷰 API
import { useNavigate } from "react-router-dom";
import "../../css/review.css"; // 리뷰 폼 스타일

export default function ReviewWriteFormPage() {
  const navigate = useNavigate();

  // 초기 리뷰 상태
  const initReview = {
    reviewNo: 0,
    reviewTitle: "",
    reviewContent: "",
    reviewDate: "",
    reviewScore: 1,
    reviewStatus: 1,
    chatRoomNo: 1,
  };

  const [review, setReview] = useState(initReview); // 리뷰 상태

  // 리뷰 폼 변경 핸들러
  const onChangeReviewForm = (e) => {
    setReview({
      ...review,
      [e.target.name]: e.target.value,
    });
  };

  // 리뷰 점수 변경 핸들러 (별점으로 처리)
  const onChangeReviewScore = (score) => {
    setReview({
      ...review,
      reviewScore: score,
    });
  };

  // 리뷰 작성 액션
  const reviewWriteAction = async () => {
    // 폼 유효성 검사 (제목, 내용 확인)
    if (!review.reviewTitle || !review.reviewContent) {
      alert("제목과 내용을 모두 입력해주세요.");
      return;
    }

    // 리뷰 작성 API 호출
    try {
      const responseJsonObject = await reviewApi.writeReview(review);
      console.log(responseJsonObject.data);
      // 리뷰 작성 후 해당 리뷰 상세 페이지로 이동
      navigate(`/review/${responseJsonObject.data.reviewNo}`);
    } catch (error) {
      console.error("리뷰 작성 오류:", error);
    }
  };

  // 별점 표시 함수
  const renderStars = (score) => {
    const filledStars = Math.round(score); // 점수를 반올림하여 별을 채우기
    const emptyStars = 5 - filledStars;
    return (
      <div className="stars">
        {[...Array(filledStars)].map((_, index) => (
          <span
            key={index}
            className="star filled"
            onClick={() => onChangeReviewScore(index + 1)}
          >
            ★
          </span>
        ))}
        {[...Array(emptyStars)].map((_, index) => (
          <span
            key={index + filledStars}
            className="star"
            onClick={() => onChangeReviewScore(filledStars + index + 1)}
          >
            ★
          </span>
        ))}
      </div>
    );
  };

  return (
    <div className="review-form-container">
      <form method="POST" className="review-form">
        <h2>리뷰 작성</h2>

        {/* 리뷰 제목 입력 */}
        <div>
          <input
            type="text"
            name="reviewTitle"
            onChange={onChangeReviewForm}
            value={review.reviewTitle}
            placeholder="리뷰 제목을 입력하세요"
            required
          />
        </div>

        {/* 리뷰 내용 입력 */}
        <div>
          <textarea
            name="reviewContent"
            onChange={onChangeReviewForm}
            value={review.reviewContent}
            placeholder="리뷰 내용을 입력하세요"
            required
          />
        </div>

        {/* 리뷰 점수 입력 (별점으로 변경) */}
        <div>
          <label>리뷰 점수 (1~5)</label>
          {renderStars(review.reviewScore)} {/* 별점 표시 */}
        </div>

        {/* 작성 버튼 */}
        <div className="review-write-btn">
          <input
            type="button"
            value="리뷰 작성"
            onClick={reviewWriteAction}
            id="btn_review_write_action"
          />
        </div>
      </form>
    </div>
  );
}
