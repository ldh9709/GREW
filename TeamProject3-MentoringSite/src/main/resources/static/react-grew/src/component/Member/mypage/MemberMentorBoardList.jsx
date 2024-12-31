import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useMemberAuth } from "../../../util/AuthContext";
import * as mentorBoardApi from "../../../api/mentorBoardApi";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye } from "@fortawesome/free-regular-svg-icons";
import PagenationItem from "../../PagenationItem";

export default function MemberMentorContentList() {
  /* Context에 저장된 토큰, 멤버정보 */
  const { token, member } = useMemberAuth();
  const [boardList, setBoardList] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);

  const navigate = useNavigate(); // useNavigate 훅 사용

  const fetchBoardList = async (page, size) => {
    const response = await mentorBoardApi.listBoardContentsByMemberNo(
      token,
      page,
      size
    );
    console.log(response);
    setBoardList(response.data.content);
    setTotalPages(response.data.totalPages);
  };

  // 페이지 변경 시 데이터 갱신
  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  // 페이지 번호 버튼 표시
  const pageNumbers = [];
  for (let i = 1; i <= totalPages; i++) {
    pageNumbers.push(i);
  }

  useEffect(() => {
    fetchBoardList(currentPage - 1, 6);
  }, [currentPage]);

  return (
    <>
      <div className="tab-boards">
        <div className="board-list">
          {boardList.map((board, index) => (
            <div
              className="board-card"
              key={index}
              onClick={() =>
                navigate(`/mentor-board/detail/${board.mentorBoardNo}`)
              }
              style={{ cursor: "pointer" }}
            >
              <div className="board-image-container">
                <img
                  src={board.mentorBoardImage}
                  alt="content-image"
                  className="board-image"
                />
              </div>
              <div className="board-details">
                <h3 className="board-title">{board.mentorBoardTitle}</h3>
                <p className="board-description">
                  {board.mentorBoardContent.substring(0, 100)}...
                </p>
                <p className="board-info">
                  <span className="board-date">
                    {board.mentorBoardDate.substring(0, 10)}
                  </span>
                  <span className="board-views">
                    <FontAwesomeIcon icon={faEye} />{" "}
                    <span>{board.mentorBoardViews}</span>
                  </span>
                </p>
              </div>
            </div>
          ))}
        </div>
        {/* 페이지네이션 버튼 */}
        <div className="common-pagination common-pagination-bottom">
        </div>
      </div>
          <PagenationItem
            currentPage={currentPage}
            totalPages={totalPages}
            paginate={paginate}
          />  
    </>
  );
}
