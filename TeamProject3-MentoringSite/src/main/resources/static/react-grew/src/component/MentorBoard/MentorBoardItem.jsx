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
  //작성 멘토 정보
  const fetchMentorInfo = async() => {
    const response = await mentorProfileApi.getMentorProfileByMemberNo(board.memberNo)
    setMentor(response.data)
  }

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

  useEffect(()=>{
    fetchMentorInfo();
  },[])


  return (
    <div className="board-card" onClick={onClick}>
          <div className="board-image-container">
            <img src={board.mentorBoardImage} alt="content-image" className="board-image" />
          </div>
          <div className="board-details">
            <span className="board-category">{board.categoryName}</span>
            <h3 className="board-title">{board.mentorBoardTitle}</h3>
            <p className="board-description">{board.mentorBoardContent.substring(0,100)} ...</p>
            <p className='board-info'>
              <span className="board-author">{mentor.memberName || ""} 멘토</span>
              <span className="board-date">{board.mentorBoardDate.substring(0,10)}</span>
              <span className="board-views">
               <FontAwesomeIcon icon={faEye} /> <span>{board.mentorBoardViews}</span>
              </span>
            </p>
          </div>
    </div>  
  );
}
