import React, { useState } from 'react'
import "../../css/admin.css";
import AdminMember from './AdminMember';
import AdminReport from './AdminReport';

export default function Admin() {

  const [activeTab, setActiveTab] = useState("member");

  // //탭 클릭시 실행되는 함수
    const handleTabClick = (tab) => {
      setActiveTab(tab);
  }

  return (
  <div className="admin-container">
    <div className="sidebar">
        <h2>관리자 페이지</h2>
        <ul>
          <li onClick={() => handleTabClick("member")}>회원</li>
          <li onClick={() => handleTabClick("inquiry")}>질문 게시글</li>
          <li>멘토 게시글</li>
          <li onClick={() => handleTabClick("report")}>신고 목록</li>
        </ul>
    </div>
    <div className='admin-header'>
      <span>신고 목록</span>
    </div>
    <div className="admin-content">
      {activeTab === "member" && <AdminMember />}
      {activeTab === "report" && <AdminReport />}
      
    </div>
</div>
  )
}
