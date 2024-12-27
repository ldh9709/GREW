import { useMemberAuth } from "../../../util/AuthContext";
import React, { useState, useEffect } from 'react'
import * as reviewApi from '../../../api/reviewApi'

export default function MemberReviewList() {
    const { token, member } = useMemberAuth();
  
    const [reviewList, setReviewList] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);

  
  const fetchReview = async (page) => {
    try {
      const response = await reviewApi.listReviewByMember(member.mentorProfileNo , page, 5, token);
      console.log('response',response)
      setReviewList(response.data.content);
      setTotalPages(response.data.totalPages);
    } catch (error) {
      console.log('리뷰 조회 실패', error);
    }
  }
  
    // 페이지 변경 시 데이터 갱신
    const paginate = (pageNumber) => {
      setCurrentPage(pageNumber);
    };

    // 페이지 번호 버튼 표시
    const pageNumbers = [];
    for (let i = 1; i <= totalPages; i++) {
      pageNumbers.push(i);
  }

  useEffect(() => {
    fetchReview(currentPage - 1);
  },[currentPage])

  return (
    <div className="review-list">
      {reviewList.map((review, index) => (
      <div className="review-item" key={index}>
        <p className="review-name">
          <strong>{review.menteeName}</strong>
        </p>
        <p className="review-date">{review.reviewDate.substring(0,10)}</p>
        <p className="review-comment">{review.reviewContent}</p>
      </div>
      ))}

      {/* 페이지네이션 버튼 */}
      <div className="common-pagination common-pagination-bottom">
        {/* 이전 버튼 */}
        <button
          className="common-pagination-arrow"
          disabled={currentPage === 1}
          onClick={() => paginate(currentPage - 1)}
        >
          &lt;
        </button>

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
    </div>
  )
}
