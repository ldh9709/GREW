import React from "react";
import * as inquiryApi from "../../api/inquiryApi";
import "../../css/styles.css";

export default function InquiryItem({ inquiry }) {
  return (
    <a
      className="inquiry-container"
      href={`inquiry/${inquiry.inquiryNo}`}
      inquiry_no={inquiry.inquiryNo}
      onClick={() => inquiryApi.increaseView(inquiry.inquiryNo)}
    >
      <div className="inquiry-title">{inquiry.inquiryTitle}</div>
      <br />
      <div className="inquiry-content">{inquiry.inquiryContent}</div>
      <br />
      <br />
      <div className="inquiry-desc">
        <br />
        <div>{inquiry.categoryName}</div>
        <br />
        {inquiry.memberName} | 조회수 {inquiry.inquiryViews} |{" "}
        {inquiry.inquiryDate.substring(0, 10)}
      </div>
    </a>
  );
}
