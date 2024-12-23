import React from "react";
import { getCookie } from "../util/cookieUtil";
import { useNavigate } from "react-router-dom";
import { logout, memberProfile, getMentorProfile } from "../api/memberApi";
import "../css/styles.css";
import { jwtDecode } from "jwt-decode";

export default function HeaderMenu() {
  const navigate = useNavigate();

  const memberCookie = getCookie("member");
  console.log("멤버 쿠키 : ", memberCookie);

  const token = memberCookie ? memberCookie.accessToken : null; // 안전하게 접근
  console.log("토큰 : ", token);

  const DecodeToken = token ? jwtDecode(token) : null; // 안전한 접근
  if (!DecodeToken) {
    console.error("Decode토큰이 Null입니다.(Token이 널이라 디코딩이 불가하다는 뜻)");
  } else {
    console.log("Decode 토큰 : ", DecodeToken);
  }

  const memberNo = DecodeToken ? DecodeToken.memberNo : null;
  console.log("멤버 넘버 : ", memberNo);

  const mentorProfileNo = DecodeToken ? DecodeToken.mentorProfileNo : null;
  console.log("멘토 프로필 넘버 : ", mentorProfileNo);

  

  // 로그인 페이지로 이동
  const handleLoginNavigate = () => {
    navigate("/member/login");
  };

  // 로그아웃 처리
  const handleLogoutAction = async () => {
    try {
      const isLogout = await logout();
      console.log("로그아웃 성공 여부 : ", isLogout);
      navigate("/main");
    } catch (error) {
      console.error("로그아웃 실패: ", error);
    }
  };

  // 회원가입 페이지로 이동
  const handleJoinNavigate = () => {
    navigate("/member/join");
  };

  // 프로필 페이지로 이동
  const handleProfileNavigate = async () => {
    try {
      const memberProfileResponse = await memberProfile(token);
      console.log("멤버 프로필 : ", memberProfileResponse);

      const mentorProfileResponse = await getMentorProfile(mentorProfileNo);
      console.log("멘토 프로필 : ", mentorProfileResponse);

      if (mentorProfileResponse.data.categoryNo === 26) {
        navigate("/mentor/join");

      } else if (memberProfileResponse.data.categoryNo === 19) {
        navigate("/member/profile/edit");

      } else {
        navigate("/member/profile"); // 기본 프로필로 이동
      }
    } catch (error) {
      console.error("프로필 이동 중 오류 발생: ", error);
      navigate("/member/profile"); // 오류 시 기본 경로로 이동
    }
  };

  // 스타일 정의
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
        <input type="button" className="profile-button " onClick={handleProfileNavigate} value="마이페이지"/>
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
