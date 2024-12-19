import React from "react";

const MentorBoard = ({ boardData }) => {
  return (
    <div style={{ padding: "20px" }}>
      <h3>멘토 콘텐츠</h3>
      <div style={{ display: "flex", flexWrap: "wrap", gap: "20px" }}>
        {boardData.map((item, index) => (
          <div
            key={index}
            style={{
              width: "300px",
              border: "1px solid #ddd",
              borderRadius: "8px",
              overflow: "hidden",
              boxShadow: "0px 2px 5px rgba(0,0,0,0.1)",
            }}
          >
            <img
              src={item.thumbnail}
              alt={item.title}
              style={{ width: "100%", height: "180px", objectFit: "cover" }}
            />
            <div style={{ padding: "10px" }}>
              <h4>{item.title}</h4>
              <p>{item.description}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default MentorBoard;
