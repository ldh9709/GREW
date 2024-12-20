import React, { useState } from 'react'

export default function MemberReviewList() {
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);

    // 페이지 변경 시 데이터 갱신
    const paginate = (pageNumber) => {
      setCurrentPage(pageNumber);
    };

    // 페이지 번호 버튼 표시
    const pageNumbers = [];
    for (let i = 1; i <= totalPages; i++) {
      pageNumbers.push(i);
    }
  return (
    <div className="review-list">
      <div className="review-item">
        <p className="review-name">
          <strong>이름</strong>
        </p>
        <p className="review-date">작성일</p>
        <p className="review-comment">내용</p>
      </div>
      <div className="review-item">
        <p className="review-name">
          <strong>이름</strong>
        </p>
        <p className="review-date">작성일</p>
        <p className="review-comment">내용</p>
      </div>
      <div className="review-item">
        <p className="review-name">
          <strong>이름</strong>
        </p>
        <p className="review-date">작성일</p>
        <p className="review-comment">내용</p>
      </div>
      <div className="review-item">
        <p className="review-name">
          <strong>이름</strong>
        </p>
        <p className="review-date">작성일</p>
        <p className="review-comment">내용</p>
      </div>
      <div className="review-item">
        <p className="review-name">
          <strong>이름</strong>
        </p>
        <p className="review-date">작성일</p>
        <p className="review-comment">내용</p>
      </div>
      <div className="review-item">
        <p className="review-name">
          <strong>이름</strong>
        </p>
        <p className="review-date">작성일</p>
        <p className="review-comment">내용</p>
      </div>
      {/* 페이지네이션 버튼 */}
      <div className="pagenation pagenation-bottom">
        {pageNumbers.map((number) => (
          <button key={number} onClick={() => paginate(number)}>
            {number}
          </button>
        ))}
      </div>
    </div>
  )
}
