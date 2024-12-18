import React from 'react'
import image from '../../../image/images.jpeg'

export default function MemberCounselList() {
  return (
    <div>
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
