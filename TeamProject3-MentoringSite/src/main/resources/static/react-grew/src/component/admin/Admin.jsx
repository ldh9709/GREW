import React, { useState } from 'react'
import "../../css/admin.css";
import AdminMember from './AdminMember';

export default function Admin() {

  // const [activeTab, setActiveTab] = useState("member");

  // //탭 클릭시 실행되는 함수
  //   const handleTabClick = (tab) => {
  //     setActiveTab(tab);
  // }

  return (
    <div className="container">
    <div className="sidebar">
        <h2>관리자 페이지</h2>
        <ul>
            <li>회원</li>
            <li>질문 게시글</li>
            <li>지도자 게시글</li>
            <li>신고 목록</li>
        </ul>
    </div>
    <div className="admin-content">
      <AdminMember/>
    </div>
</div>
  )
}
