import React, { useEffect, useState } from "react";
import * as reviewApi from "../../api/reviewApi"; // 리뷰 API

export default function ReviewListPage() {
  const [reviews, setReviews] = useState([]); // 리뷰 목록 상태
  const [loading, setLoading] = useState(true); // 로딩 상태

  // 리뷰 목록 데이터 불러오기
  useEffect(() => {
    const fetchReviews = async () => {
      try {
        const response = await reviewApi.listReviewAll();
        console.log(response); // 응답을 콘솔로 확인
        // 응답이 올바른 배열 형식인지 확인
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

  return (
    <div>
      <h1>전체 리뷰 목록</h1>
      <ul>
        {reviews.map((review) => (
          <li key={review.reviewNo}>
            <h3>{review.reviewTitle}</h3>
            <p>{review.reviewContent}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}
