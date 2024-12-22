import { getCookie } from "../../../util/cookieUtil"
import React, { useEffect, useState } from 'react'
import image from '../../../image/images.jpeg'
import * as chattingApi from '../../../api/chattingApi'
import * as memberApi from '../../../api/memberApi'

export default function MemberCounselList() {
  const memberCookie = getCookie("member");
  const token = memberCookie.accessToken;
  const memberNo = memberCookie.memberNo;
  const role = memberCookie.memberRole;

  const [counselList, setCounselList] = useState([{}]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);

  const fetchName = async (memberNo) => {
    const response = await memberApi.memberInfo(token, memberNo)
    return response.data.memberName;
  }
  
  //상담 내역 조회
  const fetchCounselList = async (page) => {
    try {
      if (role === 'ROLE_MENTEE') {
        const response = await chattingApi.listChatRoom(token, page, 4);
        const chatRooms = response.data.content;
        const updateRooms = await Promise.all(
          chatRooms.map(async (chat) => {
            console.log(chat);
            const searchName = await fetchName(chat.mentorNo);
            return {
              ...chat,
              searchName
            };
          })
        )
        setCounselList(updateRooms);
        setTotalPages(response.data.totalPages);
      } else if (role === 'ROLE_MENTOR') {
        const response = await chattingApi.listChatRoom(token, page, 6);
        const chatRooms = response.data.content;
        console.log(chatRooms);
        const updateRooms = await Promise.all(
          chatRooms.map(async (chat) => {
            const searchName = await fetchName(chat.menteeNo);
            return {
              ...chat,
              searchName
            };
          })
        )
        setCounselList(updateRooms);
        setTotalPages(response.data.totalPages);
      }
    } catch (error) {
      console.log('상담내역 조회 실패', error);
    }
  }

    //리뷰 작성 여부 확인
    const fetchReview = async () => {
    }

    const counselStatus = (status) => {
      switch (status) {
        case 7000:
          return "대기 중";
          break;
        case 7100:
          return "진행 중";
          break;
        case 7200:
          return "완료";
          break;
        case 7300:
          return "멘토 미승인";
          break;
        case 7400:
          return "취소";
          break;
        case 7500:
          return "관리 종료 ";
          break;
      }
    }

    useEffect(() => {
      fetchCounselList(currentPage - 1);
    }, [currentPage])
  
    // 페이지 변경 시 데이터 갱신
    const paginate = (pageNumber) => {
      setCurrentPage(pageNumber);
    };

    // 페이지 번호 버튼 표시
    const pageNumbers = [];
    for (let i = 1; i <= totalPages; i++) {
      pageNumbers.push(i);
    }

    return (
      <div className='tab-content tab-counsel'>
        {counselList.length === 0 ? (
          <p> 진행한 상담내역이 없습니다. </p>
        ) : (
          role === "ROLE_MENTEE" ? (
            <ul className="mentor-list">
              {counselList.map((counsel, index) => (
                <li className="mentor-counsel-item" key={index}>
                  <div>
                    <img src={image} alt="프로필 이미지" />
                  </div>
                  <div>
                    <p className="mentor-name">{counsel.searchName} 멘토</p>
                    <p className="mentor-status">
                      상담 상태: {counselStatus(counsel.chatRoomStatus)}
                    </p>
                  </div>
                  <button
                    className={`review-button ${counsel.chatRoomStatus === 7200 ? "active" : ""
                      }`}
                  >
                    리뷰 작성
                  </button>
                </li>
              ))}
            </ul>
          ) : (
            <ul className="mentee-list">
              {counselList.map((counsel, index) => (
                <li className="mentee-counsel-item" key={index}>
                  <div>
                    <p className="mentee-name">멘티명: {counsel.searchName}</p>
                    <p className="counsel-date">
                      상담 기간: {counsel.chatRoomStartDate ? counsel.chatRoomStartDate.substring(0, 10) : ""} ~{" "}
                      {counsel.chatRoomEndDate ? counsel.chatRoomEndDate.substring(0, 10) : ""}
                    </p>
                    <p className="counsel-date">
                      리뷰작성 여부:
                    </p>
                    <div className={`counsel-type ${counsel.chatRoomStatus === 7200 ? "green white" : "" }`}>
                      {counselStatus(counsel.chatRoomStatus)}
                    </div>
                  </div>
                </li>
              ))}
            </ul>
          )
        )}
        {/* 페이지네이션 버튼 */}
        <div className="pagenation pagenation-bottom">
          {pageNumbers.map((number) => (
            <button key={number} onClick={() => paginate(number)}>
              {number}
            </button>
          ))}
        </div>
      </div>
    )
}
