import React from "react";

const MentorReview = ({ reviewData }) => {
  return (
    <div style={{ padding: "20px" }}>
      <h3>멘토 리뷰</h3>
      <ul style={{ listStyleType: "none", padding: 0 }}>
        {reviewData.map((review, index) => (
          <li
            key={index}
            style={{
              marginBottom: "20px",
              border: "1px solid #ddd",
              padding: "15px",
              borderRadius: "8px",
              boxShadow: "0px 2px 5px rgba(0,0,0,0.1)",
            }}
          >
            <p>
              <strong>{review.reviewer}</strong> ({review.date})
            </p>
            <p>{review.content}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default MentorReview;
