import React, { useEffect, useState } from "react";
import "../../css/mentorBoard.css";
import { useNavigate } from "react-router-dom";
import imageSrc from '../../image/images.jpeg'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye } from '@fortawesome/free-regular-svg-icons';
import * as mentorProfileApi from '../../api/mentorProfileApi'

export default function MentorBoardItem({ board, onClick }) {
  const navigate = useNavigate();
  const [mentor, setMentor] = useState({});

  // 작성 멘토 정보 가져오기
  const fetchMentorInfo = async () => {
    try {
      // 🔥 board가 undefined일 가능성을 대비해 안전하게 접근
      if (!board?.memberNo) {
        console.warn("board.memberNo가 유효하지 않습니다:", board);
        return;
      }

      const response = await mentorProfileApi.getMentorProfileByMemberNo(board.memberNo);
      setMentor(response.data);
    } catch (error) {
      console.error("Mentor 정보 가져오는 중 오류 발생:", error);
    }
  };

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

  useEffect(() => {
    fetchMentorInfo(); // 🔥 board가 유효한 경우만 호출됨
  }, [board]); // 🔥 board를 의존성에 추가하여 상태 변경 시 다시 호출

  return (
    <div className="board-card" onClick={onClick}>
      <div className="board-image-container">
        <img src={imageSrc} alt="content-image" className="board-image" />
      </div>
      <div className="board-details">
        {/* 🔥 categoryName가 없는 경우 안전하게 대체 */}
        <span className="board-category">{board?.categoryName || "카테고리 정보 없음"}</span>
        <h3 className="board-title">{board?.mentorBoardTitle || "제목 없음"}</h3>
        <p className="board-description">{board?.mentorBoardContent?.substring(0, 100) || "내용 없음"} ...</p>
        <p className="board-info">
          <span className="board-author">{mentor?.memberName || "작성자 정보 없음"} 멘토</span>
          <span className="board-date">{board?.mentorBoardDate?.substring(0, 10) || "날짜 없음"}</span>
          <span className="board-views">
            <FontAwesomeIcon icon={faEye} /> <span>{board?.mentorBoardViews || 0}</span>
          </span>
        </p>
      </div>
    </div>
  );
}
