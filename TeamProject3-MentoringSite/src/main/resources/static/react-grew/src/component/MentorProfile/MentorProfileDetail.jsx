import { useMemberAuth } from "../../util/AuthContext";
import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom"; // useNavigate 추가
import "../../css/mentorProfile.css"; // 🔥 추가된 CSS 파일
import * as memberApi from "../../api/memberApi";
import { listReviewByMember } from "../../api/reviewApi.js"; // 리뷰 목록 API 추가
import * as categoryApi from "../../api/categoryApi";
import * as mentorBoardApi from "../../api/mentorBoardApi";
import PagenationItem from "../PagenationItem";

import MentorProfileInfo from "./MentorProfileInfo.jsx";
import MentorBoardItem from "../MentorBoard/MentorBoardItem";

export default function MentorProfileDetail() {
  const { token, member } = useMemberAuth();
  const { mentorProfileNo } = useParams();
  const navigate = useNavigate(); // navigate 훅 추가
  const [mentorProfile, setMentorProfile] = useState({});
  const [reviews, setReviews] = useState([]); // 리뷰 상태
  const [boards, setBoards] = useState([]); // 멘토 보드 상태
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [categoryName, setCategoryName] = useState("카테고리 정보 없음");
  const [currentPage, setCurrentPage] = useState(1); // 현재 페이지 번호
  const [totalPages, setTotalPages] = useState(0); // 전체 페이지 수
  const [itemsPerPage] = useState(3); // 페이지당 항목 수 (예: 한 페이지에 3개 항목)

  const fetchMentorProfile = async () => {
    try {
      setLoading(true);
      const mentorProfileResponse = await memberApi.getMentorProfile(
        mentorProfileNo
      );
      console.log(mentorProfileResponse.data);
      if (
        !mentorProfileResponse?.data ||
        Object.keys(mentorProfileResponse.data).length === 0
      ) {
        console.warn("Invalid mentor profile number:", mentorProfileNo);
        navigate("/mentor-profile/list", { replace: true });
        return;
      }
      setMentorProfile(mentorProfileResponse.data);

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

  const fetchMentorBoards = async () => {
    try {
      const response = await mentorBoardApi.listMentorBoardsByProfile(
        mentorProfileNo,
        0,
        5
      );
      setBoards(response?.data?.content || []);
    } catch (error) {
      console.error("멘토 보드 데이터를 가져오는 중 오류 발생:", error);
    }
  };

  const fetchReview = async (page) => {
    try {
      setLoading(true);
      const reviewsResponse = await listReviewByMember(
        mentorProfileNo,
        page - 1,
        itemsPerPage,
        token
      );

      if (reviewsResponse?.data) {
        const reviewsData = reviewsResponse.data.content;

        if (reviewsData.length > 0) {
          setReviews(reviewsData);
          setTotalPages(reviewsResponse.data.totalPages);
        } else {
          setReviews([]);
        }
      } else {
        setReviews([]);
      }
    } catch (error) {
      setError("리뷰 가져오는 중 오류가 발생했습니다.");
      navigate("/mentor-profile/list", { replace: true });
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchMentorProfile();
    fetchMentorBoards();
  }, [mentorProfileNo]);

  useEffect(() => {
    fetchReview(currentPage);
  }, [currentPage, itemsPerPage]);

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

  const handleReviewClick = (reviewNo) => {
    navigate(`/review/${reviewNo}`);
  };

  // 별점 표시 함수
  const renderStars = (score) => {
    const filledStars = Math.round(score); // 점수를 반올림하여 별을 채우기
    const emptyStars = 5 - filledStars;
    return (
      <div>
        {[...Array(filledStars)].map((_, index) => (
          <span key={index} className="review star filled">
            ★
          </span>
        ))}
        {[...Array(emptyStars)].map((_, index) => (
          <span key={index + filledStars} className="review star">
            ★
          </span>
        ))}
      </div>
    );
  };

  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };
  // 페이지네이션 버튼 표시 (3개씩 끊어서 표시)
  const pageNumbers = [];
  const pagesToShow = 10; // 한 번에 보여줄 페이지 수
  const startPage =
    Math.floor((currentPage - 1) / pagesToShow) * pagesToShow + 1;
  const endPage = Math.min(startPage + pagesToShow - 1, totalPages);

  for (let i = startPage; i <= endPage; i++) {
    pageNumbers.push(i);
  }
  const maskName = (name) => {
    if (name.length <= 2) {
      return name[0] + "*".repeat(name.length - 1);
    }
    return name[0] + "*".repeat(name.length - 2) + name[name.length - 1];
  };

  if (loading) return <p>로딩 중...</p>;
  if (error) return <p className="error-message">{error}</p>;

  return (
    <div className="mentor-profile-detail-container">
      <div className="mentor-header">
        <MentorProfileInfo mentorProfile={mentorProfile} />

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
                <p className="review-score">
                  {renderStars(review.reviewScore || 0)}
                </p>
                <p>{maskName(review.menteeName) || "작성자 이름"} 님의 리뷰</p>
              </li>
            ))}
          </ul>
        ) : (
          <p>리뷰가 없습니다.</p>
        )}
      </div>
      {/* 페이지네이션 버튼 */}
      <div className="pagenation1">
        {/* 이전 버튼 */}
        <button
          className="common-pagination-arrow"
          disabled={currentPage === 1}
          onClick={() => paginate(currentPage - 1)}
        >
          &lt;
        </button>
        {startPage > 1 && (
          <button onClick={() => paginate(startPage - 1)}>이전</button>
        )}{" "}
        {/* 페이지 번호 버튼 */}
        {pageNumbers.map((number) => (
          <button
            key={number}
            className={`common-pagination-number ${
              currentPage === number ? "active" : ""
            }`}
            onClick={() => paginate(number)}
          >
            {number}
          </button>
        ))}
        {/* 다음 버튼 */}
        <button
          className="common-pagination-arrow"
          disabled={currentPage === totalPages}
          onClick={() => paginate(currentPage + 1)}
        >
          &gt;
        </button>
      </div>

      <div className="mentor-boards">
        <h3>멘토 보드</h3>
        {boards.length > 0 ? (
          boards.map((board) => (
            <MentorBoardItem
              key={board.mentorBoardNo}
              board={board}
              onClick={() =>
                navigate(`/mentor-board/detail/${board.mentorBoardNo}`)
              }
            />
          ))
        ) : (
          <p>등록된 멘토 보드가 없습니다.</p>
        )}
      </div>
    </div>
  );
}
