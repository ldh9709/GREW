import React from "react";

export default function PagenationItem({ currentPage, totalPages, paginate }) {
  // 페이지 번호 배열 만들기
  const pageNumbers = [];
  for (let i = 1; i <= totalPages; i++) {
    pageNumbers.push(i);
  }
  return (
    <>
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
    </>
  );
}
