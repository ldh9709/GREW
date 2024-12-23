import React, { useState } from "react";
import { getCookie } from "../util/cookieUtil";
import { useNavigate } from "react-router-dom";
import { logout } from "../api/memberApi"
import "../css/styles.css";

export default function HeaderMenu() {
  const navigate = useNavigate();

  const memberCookie = getCookie("member");
  const token = memberCookie ? memberCookie.accessToken : null; // 안전하게 접근
  
  console.log("멤버 쿠키 : ", memberCookie);
  const handleLoginNavigate = async () => {
    navigate('/member/login');
  };
  
  const handleLogoutAction = async () => {
    const isLogout = await logout();
    if(isLogout) {
      navigate('/main');
      alert("로그아웃 성공");
    } else {
      alert("로그아웃 실패");
    }
  };

  const handleJoinNavigate = async () => {
    navigate('/member/join');
  };

  const handleProfileNavigate = async () => {
    navigate('/member/profile');
  };

  /* 로그인 성공 시 화면 리로드 */
  useState(()=> {
    navigate(0);
  }, []);


  const navStyle = {
    fontFamily: "'Noto Sans KR', sans-serif",
    padding: "10px 20px",
  };

  const rightMenuBarStyle = {
    display: "flex",
    justifyContent: "flex-end", // 오른쪽 정렬
    gap: "20px",
  };

 
  return (
    <div className="header" style={navStyle}>
      <div className="rightMenu" style={rightMenuBarStyle}>
      {token ? (
        <>
          {/* <a href="/member/profile" className="mypage">
          마이페이지
        </a> */}
        <input type="button" className="profile-button" onClick={handleProfileNavigate} value="마이페이지"/>
        {/* <a href="/logout" onClick={handleLogoutAction} className="mypage">
        로그아웃
        </a> */}
        <input type="button" className="logout-button" onClick={handleLogoutAction} value="로그아웃"/>
        </>
      ) : (
         <>
         <input type="button" className="login-button" onClick={handleLoginNavigate} value="로그인"/>

         <input type="button" className="join-button" onClick={handleJoinNavigate} value="회원가입"/>

         </>
        )}
      </div>
    </div>
  );
}
