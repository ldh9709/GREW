import React, { useState, useEffect } from 'react';
import { listMentorBoardsByViews } from '../../api/mentorBoardApi';
import MentorBoardItem from './MentorBoardItem';
import '../../css/mentorBoard.css';

const MentorBoardList = () => {
  const [boards, setBoards] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    const fetchBoards = async () => {
      const response = await listMentorBoardsByViews(currentPage, 10);
      setBoards(response.data.content);
      setTotalPages(response.data.totalPages);
    };
    fetchBoards();
  }, [currentPage]);

  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  return (
    <div className="mentor-board-list-container">
      <h1>멘토 보드</h1>
      <div className="mentor-board-grid">
        {boards.map((board) => (
          <MentorBoardItem key={board.mentorBoardNo} board={board} />
        ))}
      </div>
      <div className="pagination">
        {[...Array(totalPages)].map((_, index) => (
          <button
            key={index}
            onClick={() => handlePageChange(index)}
            className={index === currentPage ? 'active-page' : ''}
          >
            {index + 1}
          </button>
        ))}
      </div>
    </div>
  );
};

export default MentorBoardList;
