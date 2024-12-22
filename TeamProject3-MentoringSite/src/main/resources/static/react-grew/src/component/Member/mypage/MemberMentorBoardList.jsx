import React, { useEffect, useState } from 'react'
import { getCookie } from "../../../util/cookieUtil"
import * as mentorBoardApi from '../../../api/mentorBoardApi'
import imageSrc from '../mentor-content1.jpg'


export default function MemberMentorContentList() {
    const memberCookie = getCookie("member");
    const token = memberCookie.accessToken;

    const [boardList, setBoardList] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);

    const fetchBoardList = async(page,size) => {
        const response = await mentorBoardApi.listBoardContentsByMemberNo(token,page,size);
        console.log(response);
        setBoardList(response.data.content);
        setTotalPages(response.data.totalPages);
    }

    // 페이지 변경 시 데이터 갱신
    const paginate = (pageNumber) => {
      setCurrentPage(pageNumber);
    };

    // 페이지 번호 버튼 표시
    const pageNumbers = [];
    for (let i = 1; i <= totalPages; i++) {
      pageNumbers.push(i);
  }

  useEffect(()=>{
      fetchBoardList(currentPage -1, 6);
  },[currentPage])

  return (
  <>
  <div className='tab-boards'>
    <div className="board-list">
      {boardList.map((board, index) => (
        <div className="board-card" key={index}>
          <div className="board-image-container">
            <img src={imageSrc} alt="컨텐츠 이미지" className="board-image" />
          </div>
          <div className="board-details">
            <h3 className="board-title">{board.mentorBoardTitle}</h3>
            <p className="board-description">{board.mentorBoardContent.substring(0,100)}...</p>
            <p className='board-subInfo'>
              <span className="board-date">{board.mentorBoardDate.substring(0,10)}</span>
              <span className="board-views"> | 조회수 : {board.mentorBoardViews}</span>
            </p>
          </div>
        </div>
      ))}
    </div>
    {/* 페이지네이션 버튼 */}
    <div className="pagenation pagenation-bottom">
      {pageNumbers.map((number) => (
          <button key={number} onClick={() => paginate(number)}>
              {number}
          </button>
      ))}
      </div>   
    </div>
  </>
  )
}
