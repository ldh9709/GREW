import { getCookie } from "../../../util/cookieUtil"
import React, { useEffect, useState } from 'react'
import image from '../../../image/images.jpeg'
import * as chattingApi from '../../../api/chattingApi'

export default function MemberCounselList() {
  const memberCookie = getCookie("member");
  const token = memberCookie.accessToken;
  const memberNo = memberCookie.memberNo;
  const memberRole = memberCookie.memberRole;

  const [counselList, setCounselList] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);

  const fetchCounselList = async(page) => {
    try {
      const response = await chattingApi.listChatRoom(token, page, 4);
      if (memberRole === 'ROLE_MENTEE') {
        const filteredCounselList = response.data.content.filter(item => item.menteeNo === memberNo);
        console.log(filteredCounselList)
        setCounselList(filteredCounselList);
        setTotalPages(response.data.totalPages);
      }
      // else if (memberRole === 'ROLE_MENTOR') {
      //   const filteredCounselList = response.data.filter(item => item.mentorNo === memberNo);
      // }
      } catch (error) {
      console.log('상담내역 조회 실패', error);
    }
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
        return "멘토 거절";
        break;
      case 7400:
        return "취소";
        break;
      case 7500:
        return "관리자 종료";
        break;
    }
  }

  const handleReview = (status) => {

  }
  useEffect(() => {
    fetchCounselList(currentPage-1);
  },[currentPage])
  
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
      ): (
          <ul className="mentor-list">
            {counselList.map((counselList, index) => (
              <li className="mentor-item" key={index}>
                  <div>
                      <img src={image} alt="프로필 이미지" />
                  </div>
                  <div>
                  <p className="mentor-name"> 멘토</p>
                  <p className="mentor-status">
                  상담상태 : {counselStatus(counselList.chatRoomStatus)}
                  </p>
                  </div>
                <button className={`review-button ${counselList.chatRoomStatus === 7200 ? "active" : ""} `}> 리뷰작성 </button>
              </li>
            ))}
          </ul>
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
