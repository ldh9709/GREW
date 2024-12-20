import React from "react";
import * as inquiryApi from "../../api/inquiryApi";
import "../../css/styles.css";

export default function InquiryItem({ inquiry }) {
  return (
    <>
      <link
        href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
        rel="stylesheet"
      ></link>
      <a
        className="inquiry-container"
        href={`inquiry/${inquiry.inquiryNo}`}
        inquiry_no={inquiry.inquiryNo}
        onClick={() => inquiryApi.increaseView(inquiry.inquiryNo)}
      >
        <div className="inquiry-title">{inquiry.inquiryTitle}</div>
        <div className="inquiry-content">{inquiry.inquiryContent}</div>
        <div className="inquiry-desc">
          {/* <div>{inquiry.memberName}</div> */}
          {inquiry.categoryName} | 조회수 {inquiry.inquiryViews} |{" "}
          {inquiry.inquiryDate.substring(0, 10)}
        </div>
      </a>
    </>
  );
}
