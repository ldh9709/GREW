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

  const tabName = (tab) => {
    switch (tab) {
      case "member":
        return "회원목록"
        break;
      case "inquiry":
        return "질문게시글"
        break;
      case "board":
        return "멘토게시글"
        break;
      case "report":
        return "신고목록"
        break;
    }
  }

  return (
  <div className="admin-container">
    <div className="sidebar">
        <h2>관리자 페이지</h2>
        <ul>
          <li onClick={() => handleTabClick("member")}>회원 목록</li>
          <li onClick={() => handleTabClick("inquiry")}>질문 게시글</li>
          <li onClick={() => handleTabClick("board")}>멘토 게시글</li>
          <li onClick={() => handleTabClick("report")}>신고 목록</li>
        </ul>
    </div>
    <div className='admin-header'>
        <span>{tabName(activeTab)}</span>
    </div>
    <div className="admin-content">
      {activeTab === "member" && <AdminMember />}
      {activeTab === "report" && <AdminReport />}
    </div>
</div>
  )
}
