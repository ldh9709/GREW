import React, { useEffect, useState } from "react";
import * as inquiryApi from "../../api/inquiryApi";
import "../../css/styles.css";

export default function InquiryItem({ answer,index }) {
  useEffect(() => {}, []);
  return (
    <>
      <div className="answer-best-list">
        <a className="answer-best-container" href={`inquiry/${answer.inquiryNo}`} inquiry_no="answer.inquiryNo">
        <div className="answer-best-content">{index+1}.{answer.answerContent}</div>
        </a>
      </div>
    </>
  );
}
