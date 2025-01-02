import React, { useEffect, useState } from "react";
import MemberProfileFormPage from "./MemberProfileFormPage";
import MentorModifyFormPage from "./MentorModifyFormPage"
import "../../css/memberPage.css";
import  grew  from "../../image/logo.png";
import { useLocation, useNavigate, useParams } from "react-router-dom";

export default function MemberProfileLayoutFormPage() {
  /* 왼쪽 사이드 바 CSS */
  const [activeTab, setActiveTab] = useState("profile");
  
  /* URL 추적을 위한 선언 */
  const { tab } = useParams();

  const navigate = useNavigate();

  const handleTabClick = (tab) => {
    setActiveTab(tab);
    navigate(`/profile/${tab}`); // 클릭한 탭에 따라 URL 업데이트
  }

  const tabName = (tab) => {
    switch (tab) {
      case "memberProfile":
        return "멤버 프로필 관리"
        break;
      case "mentorProfile":
        return "멘토 프로필 관리리"
    }
  }

  useEffect(() => {
     // URL에서 가져온 `tab` 값을 기반으로 활성 탭 설정
     setActiveTab(tab || "memberProfile");
    }, [tab]);

  
  return (
    <div className="profile-layout">
      {/* 사이드바 */}
        <div className="profile-sidebar">
            <div
            className={`profile-sidebar-item ${
                activeTab === "memberProfile" ? "active" : ""
            }`}
            onClick={() => setActiveTab("memberProfile")}
            >
            멤버 프로필 관리
            </div>
            <div
            className={`profile-sidebar-item ${
                activeTab === "mentorProfile" ? "active" : ""
            }`}
            onClick={() => setActiveTab("mentorProfile")}
            >
            멘토 프로필 관리
            </div>
        </div>

      {/* 오른쪽 콘텐츠 */}
      <div className="content">
        {activeTab === "memberProfile" && <MemberProfileFormPage/>}
        {activeTab === "mentorProfile" && <MentorModifyFormPage/>}
      </div>

      {/* 사이드바 */}
      <div className="profile-sidebar-right">
        <img src={grew} alt="logo" className="profile-sidebar-img" />
      </div>
    </div>
  );
};
