import React, { useState } from 'react'
import "../../css/admin.css";
import AdminMember from './AdminMember';
import AdminReport from './AdminReport';
import AdminInquiry from './AdminInquiry';
import AdminMentorBoard from './AdminMentorBoard';
import { useNavigate } from 'react-router-dom';
import { logout } from '../../api/memberApi'
import { useMemberAuth } from "../../util/AuthContext";

export default function Admin() {

  const auth = useMemberAuth();
  const [activeTab, setActiveTab] = useState("member");
  const navigate = useNavigate();
  console.log("Active Tab:", activeTab);
  // //탭 클릭시 실행되는 함수
    const handleTabClick = (tab) => {
      setActiveTab(tab);
  }

  const tabName = (tab) => {
    switch (tab) {
      case "member":
        return "회원목록"
      case "inquiry":
        return "질문게시글"
      case "board":
        return "멘토게시글"
      case "report":
        return "신고목록"
    }
  }

  const handleLogoutAction = async () => {
    try {
      await logout();
      auth.logout();
      navigate("/main");
      alert("로그아웃 되었습니다..")
    } catch (error) {
      alert("로그아웃 실패")
    }
  }


  return (
  <div className="admin-container">
    <div className="sidebar">
        <h2>관리자 페이지</h2>
        <ul>

          <li onClick={() => handleTabClick("member")}>회원</li>
          <li onClick={() => handleTabClick("board")}>질문 게시글</li>
          <li onClick={() =>handleTabClick("mentor") }>멘토 게시글</li>

          <li onClick={() => handleTabClick("report")}>신고 목록</li>
        </ul>
    </div>
    <div className='admin-header'>
        <span>{tabName(activeTab)}</span>
        <div className='admin-header-btn'>
          <button className='main-btn' onClick={()=>(navigate(`/main`))}>메인</button>
          <button className='logout-btn' onClick={handleLogoutAction}>로그아웃</button>
        </div>
    </div>
    <div className="admin-content">
      {activeTab === "member" && <AdminMember />}
      {activeTab === "board" && <AdminInquiry />}      
      {activeTab === "mentor" && <AdminMentorBoard />}      
      {activeTab === "report" && <AdminReport />}
    </div>
</div>
  )
}
