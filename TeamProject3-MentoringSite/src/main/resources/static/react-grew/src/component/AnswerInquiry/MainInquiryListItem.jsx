import React, { useState } from "react";
import * as inquiryApi from "../../api/inquiryApi";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCommentDots, faEye } from "@fortawesome/free-regular-svg-icons";
export default function MainInquiryListItem({ inquiry }) {
  const [count, setCount] = useState([]);
  const maskName = (name) => {
    if (name.length <= 2) {
      return name[0] + "*".repeat(name.length - 1);
    }
    return name[0] + "*".repeat(name.length - 2) + name[name.length - 1];
  };
  const fetchCount = async () => {
    try {
      const response = await inquiryApi.countAnswerByinquiryNo(
        inquiry.inquiryNo
      );
      setCount(response.data);
    } catch (error) {
    }
  };
  useState(() => {
    fetchCount();
  }, []);
  return (
    <div className="inquiry-list-item">
      <a
        className="inquiry-container"
        href={`inquiry/${inquiry.inquiryNo}`}
        inquiry_no={inquiry.inquiryNo}
      >
        <div className="inquiry-item-top">
          <span className="inquiry-category">{inquiry.categoryName}</span>
          <span>{maskName(inquiry.memberName)}멘티 | </span>
          <span> {inquiry.inquiryDate.substring(0, 10)}</span>
        </div>
        <div className="inquiry-title">{inquiry.inquiryTitle}</div>
        <div className="inquiry-content">
          {inquiry.inquiryContent.substring(0, 100)}
        </div>
        <div className="inquiry-desc">
          <FontAwesomeIcon icon={faEye} /> {inquiry.inquiryViews} |{" "}
          <FontAwesomeIcon icon={faCommentDots} /> {count}
        </div>
      </a>
    </div>
  );
}
