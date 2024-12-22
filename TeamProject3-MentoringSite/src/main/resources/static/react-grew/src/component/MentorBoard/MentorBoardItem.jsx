import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../../css/mentorBoard.css';

export default function MentorBoardItem({ board }) {
  const navigate = useNavigate();

  const viewMentorBoard = () => {
    navigate(`/mentor-board/detail/${board.mentorBoardNo}`);
  };

  return (
    <div className="mentor-board-card" onClick={viewMentorBoard}>
      <img
        src={board.mentorBoardImage || '/default-thumbnail.png'}
        alt="보드 이미지"
        className="mentor-board-thumbnail"
      />
      <h3 className="mentor-board-title">{board.mentorBoardTitle || "제목 없음"}</h3>
      <p className="mentor-board-content">
        {board.mentorBoardContent?.substring(0, 50) || "내용 없음"}...
      </p>
      <div className="mentor-board-meta">
        <span>조회수: {board.mentorBoardViews}</span>
        <span>작성일: {board.mentorBoardDate}</span>
      </div>
    </div>
  );
}
