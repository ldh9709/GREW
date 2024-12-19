import React, { useEffect, useState } from 'react'
import image from '../../../image/images.jpeg'
import * as chattingApi from '../../../api/ChattingApi'

export default function MemberCounselList() {
  const [counselList, setCounselList] = useState([]);

  const fetchCounselList = async() => {
    try {
      const response = await chattingApi.listChatRoom(6);
      console.log(response);
    } catch (error) {
      console.log('상담내역 조회 실패', error);
    }
  }

  useEffect(() => {
    fetchCounselList();
  },[])

  return (
    <div className='tab-content tab-counsel'>
        <ul className="mentor-list">
            <li className="mentor-item">
                <div>
                    <img src={image} alt="프로필 이미지" />
                </div>
                <div>
                <p className="mentor-name">송대현 멘토</p>
                <p className="mentor-status">상담상태: 진행 중</p>
                </div>
                <button className="review-button"> 리뷰작성 </button>
            </li>
        </ul>

    </div>
  )
}
