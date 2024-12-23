import React, { useState } from "react";
import * as reviewApi from "../../api/reviewApi"; // 리뷰 API
import { useNavigate } from "react-router-dom";

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
    chatRoomNo:1
  };

  const [review, setReview] = useState(initReview); // 리뷰 상태

  // 리뷰 폼 변경 핸들러
  const onChangeReviewForm = (e) => {
    setReview({
      ...review,
      [e.target.name]: e.target.value,
    });
  };

  // 리뷰 점수 변경 핸들러
  const onChangeReviewScore = (e) => {
    // 리뷰 점수를 숫자로 처리하도록 수정
    setReview({
      ...review,
      reviewScore: parseInt(e.target.value), // 점수를 숫자로 변경
    });
  };

  // 리뷰 작성 액션
  const reviewWriteAction = async (e) => {
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

  return (
    <>
      <link
        href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
        rel="stylesheet"
      ></link>
      <div>
        <form method="POST" className="review-form">
          <div>
            <div>리뷰 작성</div>

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

            {/* 리뷰 점수 입력 */}
            <div>
              <label>리뷰 점수 (1~5)</label>
              <input
                type="number"
                name="reviewScore"
                onChange={onChangeReviewScore}
                value={review.reviewScore}
                min="1"
                max="5"
                required
              />
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
          </div>
        </form>
      </div>
    </>
  );
}
