import React, { useEffect, useState } from "react";
import * as inquiryApi from "../../api/inquiryApi";
import "../../css/styles.css";

export default function InquiryItem({ answer, index }) {
  const [inquiry, setInquiry] = useState([]);

  const fetchInquiry = async () => {
    const inquiry = await inquiryApi.viewInquiry(answer.inquiryNo);
    setInquiry(inquiry.data);
  };
  useEffect(() => {
    fetchInquiry();
  }, []);
  return (
    <>
      <div className="answer-best-list">
        <a
          className="answer-best-container"
          href={`inquiry/${answer.inquiryNo}`}
          inquiry_no="answer.inquiryNo"
        >
            <span className="index-span">{index + 1}</span>
          <div className="answer-best-content">
            <span className="inquiry-span">Q.</span>
            <span>{inquiry.inquiryTitle}</span>
            <div>
              <span className="answer-span">A.</span>
              <span>{answer.answerContent}</span>
            </div>
            <div className="best-detail">
              <span>{answer.memberName} 멘토</span>
              <span> ㆍ </span>
              <span>{answer.answerDate.substring(0,10)}</span>
            </div>
          </div>
        </a>
      </div>
    </>
  );
}
