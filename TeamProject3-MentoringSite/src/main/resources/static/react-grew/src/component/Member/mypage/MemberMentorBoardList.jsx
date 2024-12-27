import React, { useEffect, useState } from 'react'
import { useMemberAuth } from "../../../util/AuthContext"
import * as mentorBoardApi from '../../../api/mentorBoardApi'
import imageSrc from '../mentor-content1.jpg'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye } from '@fortawesome/free-regular-svg-icons';

export default function MemberMentorContentList() {
    /* Context에 저장된 토큰, 멤버정보 */
    const { token, member } = useMemberAuth();

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
            <img src={imageSrc} alt="content-image" className="board-image" />
          </div>
          <div className="board-details">
            <h3 className="board-title">{board.mentorBoardTitle}</h3>
            <p className="board-description">{board.mentorBoardContent.substring(0,100)}...</p>
            <p className='board-info'>
              <span className="board-date">{board.mentorBoardDate.substring(0,10)}</span>
              <span className="board-views">
               <FontAwesomeIcon icon={faEye} /> <span>{board.mentorBoardViews}</span>
              </span>
            </p>
          </div>
        </div>
      ))}
    </div>
    {/* 페이지네이션 버튼 */}
    <div className="common-pagination common-pagination-bottom">
        {/* 이전 버튼 */}
        <button
          className="common-pagination-arrow"
          disabled={currentPage === 1}
          onClick={() => paginate(currentPage - 1)}
        >
          &lt;
        </button>

        {/* 페이지 번호 버튼 */}
        {pageNumbers.map((number) => (
          <button
            key={number}
            className={`common-pagination-number ${
              currentPage === number ? "active" : ""
            }`}
            onClick={() => paginate(number)}
          >
            {number}
          </button>
        ))}

        {/* 다음 버튼 */}
        <button
          className="common-pagination-arrow"
          disabled={currentPage === totalPages}
          onClick={() => paginate(currentPage + 1)}
        >
          &gt;
        </button>
      </div> 
    </div>
  </>
  )
}
