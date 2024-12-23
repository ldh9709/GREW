import React, { useEffect, useState } from "react";
import * as inquiryApi from "../../api/inquiryApi";
import "../../css/styles.css";

export default function InquiryItem({ inquiry }) {
  const [count, setCount] = useState([]);

  const fetchCount = async () => {
    try {
      const response = await inquiryApi.countAnswerByinquiryNo(
        inquiry.inquiryNo
      );
      setCount(response.data);
      console.log(response.data);
    } catch (error) {
      console.error("답변 가져오기 실패:", error);
    }
  };
  useEffect(() => {
    fetchCount();
  }, []);
  return (
    <>
      <link
        href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
        rel="stylesheet"
      ></link>
      <div className="inquiry-list-sort">
        <a
          className="inquiry-container"
          href={`inquiry/${inquiry.inquiryNo}`}
          inquiry_no={inquiry.inquiryNo}
        >
          <div className="inquiry-title">{inquiry.inquiryTitle}</div>
          <div className="inquiry-content">{inquiry.inquiryContent}</div>
          <div className="inquiry-desc">
            {/* <div>{inquiry.memberName}</div> */}
            {inquiry.categoryName} | 조회수 {inquiry.inquiryViews} |{" "}
            {inquiry.inquiryDate.substring(0, 10)}
             | 답변수 {count}
          </div>
          <div className="answer-btn">답변하러 가기 ▶</div>
        </a>
      </div>
    </>
  );
}
