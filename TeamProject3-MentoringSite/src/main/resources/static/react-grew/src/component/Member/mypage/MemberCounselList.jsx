import { getCookie } from "../../../util/cookieUtil"
import React, { useEffect, useState } from 'react'
import image from '../../../image/images.jpeg'
import * as chattingApi from '../../../api/ChattingApi'

export default function MemberCounselList() {
  const memberCookie = getCookie("member");
  const token = memberCookie.accessToken;
  const memberNo = memberCookie.memberNo;
  const memberRole = memberCookie.memberRole;

  const [counselList, setCounselList] = useState([]);

  const fetchCounselList = async() => {
    try {
      const response = await chattingApi.listChatRoom(token);
      if (memberRole === 'ROLE_MENTEE') {
        const filteredCounselList = response.data.filter(item => item.menteeNo === memberNo);
        setCounselList(filteredCounselList);
      } else if(memberRole === 'ROLE_MENTOR'){
        const filteredCounselList = response.data.filter(item => item.mentorNo === memberNo);
      }
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
    fetchCounselList();
  },[])

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

    </div>
  )
}
