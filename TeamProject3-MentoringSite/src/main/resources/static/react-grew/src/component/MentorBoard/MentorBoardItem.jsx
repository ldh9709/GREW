import React from "react";
import "../../css/mentorBoard.css";
import { useNavigate } from "react-router-dom";

export default function MentorBoardItem({ board }) {
  const navigate = useNavigate();

  // 멘토 보드 상세 페이지로 이동
  const viewMentorBoard = () => {
    navigate(`/mentor-board/detail/${board.mentorBoardNo}`);
  };

  // 상대적 날짜 계산 함수
  const calculateRelativeDate = (dateString) => {
    const now = new Date();
    const postDate = new Date(dateString);
    const diffInSeconds = Math.floor((now - postDate) / 1000);

    if (diffInSeconds < 60) return "방금 전";
    if (diffInSeconds < 3600) return `${Math.floor(diffInSeconds / 60)}분 전`;
    if (diffInSeconds < 86400) return `${Math.floor(diffInSeconds / 3600)}시간 전`;
    if (diffInSeconds < 604800) return `${Math.floor(diffInSeconds / 86400)}일 전`;
    if (diffInSeconds < 2419200) return `${Math.floor(diffInSeconds / 604800)}주 전`;
    return `${Math.floor(diffInSeconds / 2419200)}개월 전`;
  };
  return (
    <div className="mentor-board-card" onClick={viewMentorBoard}>
      <img
        src={board.mentorBoardImage || "/default-thumbnail.png"}
        alt="보드 이미지"
        className="mentor-board-thumbnail"
      />
      <h3 className="mentor-board-title">{board.mentorBoardTitle || "제목 없음"}</h3>
      <p className="mentor-board-content">
        {board.mentorBoardContent?.substring(0, 50) || "내용 없음"}...
      </p>
      <div className="mentor-board-meta">
        <span>카테고리: {board.categoryName}</span>
        <span>조회수: {board.mentorBoardViews}</span>
        <span>작성일: {calculateRelativeDate(board.mentorBoardDate)}</span>
      </div>
    </div>
  );
}
