import { useMemberAuth } from "../../util/AuthContext";
import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom"; // useNavigate 추가
import "../../css/mentorProfile.css"; // 🔥 추가된 CSS 파일
import { getMentorProfileByNo } from "../../api/mentorProfileApi.js";
import { listReviewByMember } from "../../api/reviewApi.js"; // 리뷰 목록 API 추가
import * as categoryApi from "../../api/categoryApi";
import PagenationItem from "../PagenationItem";

import MentorProfileInfo from "./MentorProfileInfo.jsx";

export default function MentorProfileDetail() {
  const { token, member } = useMemberAuth();
  const { mentorProfileNo } = useParams();
  const navigate = useNavigate(); // navigate 훅 추가
  const [mentorProfile, setMentorProfile] = useState({});
  const [reviews, setReviews] = useState([]); // 빈 배열로 초기화
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [categoryName, setCategoryName] = useState("카테고리 정보 없음");
  const [currentPage, setCurrentPage] = useState(1); // 현재 페이지 번호
  const [totalPages, setTotalPages] = useState(0); // 전체 페이지 수
  const [itemsPerPage] = useState(5); // 페이지당 항목 수 (예: 한 페이지에 5개 항목)

  const fetchMentorProfile = async () => {
    try {
      setLoading(true);

      // 1. 멘토 프로필 조회
      const mentorProfileResponse = await getMentorProfileByNo(mentorProfileNo);
      // 🔥 데이터 유효성 검사 추가
      if (
        !mentorProfileResponse?.data ||
        Object.keys(mentorProfileResponse.data).length === 0
      ) {
        console.warn("Invalid mentor profile number:", mentorProfileNo);
        // 프로필 데이터가 없으면 리다이렉트
        navigate("/mentor-profile/list", { replace: true });
        return;
      }
      console.log(mentorProfileResponse.data);
      setMentorProfile(mentorProfileResponse.data);

      // 멘토 프로필 데이터에서 categoryNo와 memberNo 가져오기
      if (mentorProfile.categoryNo) {
        fetchCategoryName(mentorProfile.categoryNo);
      }
    } catch (error) {
      setError("멘토 프로필을 가져오는 중 오류가 발생했습니다.");
      // 오류 발생 시 리다이렉트
      navigate("/mentor-profile/list", { replace: true });
    } finally {
      setLoading(false);
    }
  };

  const fetchReview = async (page) => {
    try {
      console.log("Requesting page:", page); // page 값 확인
      console.log("Using token:", token); // 토큰 값 확인
      setLoading(true);

      // (page - 1) * itemsPerPage 로 시작 인덱스 계산
      const reviewsResponse = await listReviewByMember(
        mentorProfileNo, // mentorProfileNo를 바로 사용
        (page - 1) * itemsPerPage, // 시작 인덱스 (0부터 시작, 1페이지는 0, 2페이지는 5)
        itemsPerPage, // 한 페이지에 표시할 리뷰 수
        token // Authorization 헤더에 포함시켜야 함
      );

      console.log("reviewsResponse :", reviewsResponse); // 서버 응답 로그 출력

      if (reviewsResponse.data) {
        console.log("reviewsResponse data:", reviewsResponse.data); // 데이터 확인

        const reviewsData = reviewsResponse.data.content;

        // 데이터가 있으면 리뷰를 설정, 없으면 빈 배열로 처리
        if (reviewsData.length > 0) {
          setReviews(reviewsData); // content 배열 처리
          setTotalPages(reviewsResponse.data.totalPages); // 전체 페이지 수 업데이트
        } else {
          setReviews([]); // 데이터가 없으면 빈 배열 처리
        }
      } else {
        setReviews([]); // 데이터가 없으면 빈 배열 처리
      }

      // totalPages가 이미 업데이트 되었을 수 있기 때문에, 중복 호출 제거
    } catch (error) {
      setError("리뷰 가져오는 중 오류가 발생했습니다.");
      navigate("/mentor-profile/list", { replace: true }); // 오류 발생 시 리다이렉트
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchMentorProfile();
    fetchReview(currentPage);
  }, [currentPage]);

  if (loading) return <p>로딩 중...</p>;
  const fetchCategoryName = async (categoryNo) => {
    try {
      const response = await categoryApi.ListCategory();
      const allCategories = response.data || [];
      const matchingCategory = allCategories.find(
        (cat) => cat.categoryNo === categoryNo
      );
      setCategoryName(
        matchingCategory ? matchingCategory.categoryName : "카테고리 정보 없음"
      );
    } catch (error) {
      console.error("카테고리 정보를 가져오는 중 오류 발생:", error);
      setCategoryName("카테고리 정보 없음");
    }
  };
  // 리뷰 클릭 시 상세 페이지로 이동
  const handleReviewClick = (reviewNo) => {
    navigate(`/review/${reviewNo}`); // reviewNo를 URL로 넘겨서 상세 페이지로 이동
  };

  // 별점 생성 함수
  const renderStars = (score) => {
    const stars = [];
    for (let i = 0; i < 5; i++) {
      if (i < score) {
        stars.push(
          <span key={i} className="star filled">
            ★
          </span>
        );
      } else {
        stars.push(
          <span key={i} className="star">
            ★
          </span>
        );
      }
    }
    return stars;
  };

  // 페이지네이션 버튼 표시 (5개씩 끊어서 표시)
  const pageNumbers = [];
  const pagesToShow = 5; // 한 번에 보여줄 페이지 수
  const startPage =
    Math.floor((currentPage - 1) / pagesToShow) * pagesToShow + 1;
  const endPage = Math.min(startPage + pagesToShow - 1, totalPages);

  for (let i = startPage; i <= endPage; i++) {
    pageNumbers.push(i);
  }
  // 페이지 변경 시 데이터 갱신
  const paginate = (pageNumber) => {
    console.log("Paginate to page:", pageNumber);
    setCurrentPage(pageNumber);
  };

  if (error) return <p className="error-message">{error}</p>;

  return (
    <div className="mentor-profile-detail-container">
      <div className="mentor-header">
        {/* 좌측: 멘토 개별 정보*/}
        <MentorProfileInfo mentorProfile={mentorProfile} />

        {/* 우측: 상세 정보 */}
        <div className="mentor-details-section">
          <span className="mentor-category-box">
            {mentorProfile.categoryName}
          </span>
          <div className="mentor-section mentor-headline">
            <h2>"{mentorProfile.mentorHeadline}"</h2>
          </div>
          <div className="mentor-section">
            <h2>멘토 소개</h2>
            <pre>{mentorProfile?.mentorIntroduce || "멘토 소개 정보 없음"}</pre>
          </div>
          <div className="mentor-section">
            <h2>주요 경력</h2>
            {mentorProfile?.careerDtos &&
            mentorProfile.careerDtos.length > 0 ? (
              <ul>
                {mentorProfile.careerDtos.map((career, index) => (
                  <li key={index}>
                    <strong>회사:</strong> {career.careerCompanyName} <br />
                    <strong>직책:</strong> {career.careerJobTitle} <br />
                    <strong>기간:</strong> {career.careerStartDate} ~{" "}
                    {career.careerEndDate || "현재"} <br />
                  </li>
                ))}
              </ul>
            ) : (
              <pre>멘토 경력 정보 없음</pre>
            )}
          </div>
        </div>
      </div>

      {/* 리뷰 목록 */}
      <div className="mentor-reviews">
        <h3>멘토 리뷰</h3>
        {reviews.length > 0 ? (
          <ul>
            {reviews.map((review) => (
              <li
                key={review.reviewNo}
                onClick={() => handleReviewClick(review.reviewNo)}
                className="mentor-review-item"
              >
                <p>
                  <strong>{review.reviewTitle}</strong>
                </p>
                <p>{review.reviewContent || "리뷰 내용이 없습니다."}</p>
                <p className="review-stars">
                  평점: {renderStars(review.reviewScore || 0)}
                </p>
                <p>{review.menteeName || "작성자 이름"} 님의 리뷰</p>
              </li>
            ))}
          </ul>
        ) : (
          <p>리뷰가 없습니다.</p>
        )}
      </div>
      <PagenationItem
        currentPage={currentPage}
        totalPages={totalPages}
        paginate={paginate}
      />
    </div>
  );
}
