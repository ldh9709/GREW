import React, { useEffect, useState } from "react";
import "../css/styles.css";
import { useNavigate } from "react-router-dom";
import { useMemberAuth } from "../util/AuthContext";
import { logout, memberProfile, getMentorProfile, updateMemberRole } from "../api/memberApi";
import * as memberApi from "../api/memberApi";

export default function HeaderMenu() {
  /* 1. 상태 선언 부분 */
  const navigate = useNavigate();
  const auth = useMemberAuth();
  const token = auth?.token || null;
  const member = auth?.member || {};
  const login = auth.login;
  const mentorProfileNo = token ? member.mentorProfileNo : null;
  const [mentorProfile, setMentorProfile] = useState({});
  const [mentorProfileLoading, setMentorProfileLoading] = useState(true);




  /* 2. 함수 선언 부분 */

  // 네비게이션 로직 함수화
  const navigateTo = (path) => navigate(path);

  // 기존 함수 간소화
  const handleLoginNavigate = () => navigateTo("/member/login"); //로그인 페이지로 이동
  const handleJoinNavigate = () => navigateTo("/member/join"); //회원가입 페이지로 이동
  const handleAdminNavigate = () => navigateTo("/admin"); //어드민 페이지로 이동

  // 로그아웃 처리
  const handleLogoutAction = async () => {
    try {
      navigate(`/main`);
      await logout();
      auth.logout();
      alert("로그아웃하셨습니다.");
    } catch (error) {
      alert("오류 발생.");
      console.error("로그아웃 실패: ", error);
    }
  };

  // 프로필 페이지로 이동
  const handleProfileNavigate = async () => {
    try {
      const memberProfileResponse = await memberProfile(token);
      
      const mentorProfileResponse = await getMentorProfile(mentorProfileNo);
      setMentorProfile(mentorProfileResponse);
      
      /* 멤버의 관심사가 19번인지 확인(SNS로그인 시 기본값) */
      const checkMemberCategory = memberProfileResponse?.data?.interests?.some(
        (interest) => interest.categoryNo === 19
      );

    if (checkMemberCategory) {
        navigate("/member/profile/edit");
      } else {
        navigate("/member/profile"); // 기본 프로필로 이동
      }
    } catch (error) {
      console.error("프로필 이동 중 오류 발생: ", error);
      navigate("/member/profile"); // 오류 시 기본 경로로 이동
    }
  };

  const handleUpdateRole = async (role) => {
    try {

      if (mentorProfileLoading) {
        alert("멘토 정보를 불러오는 중입니다. 잠시 후 다시 시도해주세요.");
        return;
      }
      
      if (member.mentorProfileNo === 0) {
          const confirmation = window.confirm('멘토를 신청 하시겠습니까?')
          if (!confirmation) {
              return;
          }
          navigate(`/mentor/join`);
          return;
      } else if (mentorProfile.mentorStatus === 2) {
        alert("가입 심사 중입니다.");
        return;
      } else {
          const confirmation = window.confirm(
              member.memberRole === "ROLE_MENTEE" 
              ? "멘토로 변경하시겠습니까?" 
              : "멘티로 변경하시겠습니까?"
          );
          if (!confirmation) {
              return;
          }
      }          
      const response = await updateMemberRole(token, role);
      if (response.status === 2012) {
          login(response.data.accessToken);
        }
          navigate(`/main`);
  } catch (error) {
    console.error('회원 권한 변경 실패', error);
  }
};

  //1. memberApi에서 멘토 프로필 가져오기
  const fetchMentorProfile = async () => {
    try {
      setMentorProfileLoading(true);
      const response = await memberApi.getMentorProfile(mentorProfileNo);
      setMentorProfile(response.data);
    } catch (error) {
      console.error("멘토 프로필 로딩 실패 : ", error)
    } finally {
      setMentorProfileLoading(false);
    }
  };

  /* 3. useEffect 선언 부분 */
  //2. 멘토 프로필 가져오기
  useEffect(() => {
    if (mentorProfileNo && mentorProfileNo !== 0) {
      fetchMentorProfile();
    }
  }, [mentorProfileNo, navigate]);


  // 스타일 정의
  const navStyle = {
    padding: "10px 20px",
  };

  //오른쪽 바 스타일
  const rightMenuBarStyle = {
    display: "flex",
    justifyContent: "flex-end", // 오른쪽 정렬
    gap: "20px",
  };
  

  return (
    <div className="header" style={navStyle}>
      <div className="rightMenu" style={rightMenuBarStyle}>
        {token ? (
          member.memberRole === 'ROLE_ADMIN' ? ( // 관리자일 때
            <>
              <input
                type="button"
                className="admin-button"
                onClick={handleAdminNavigate}
                value="관리자"
              />
              <input
                type="button"
                className="logout-button"
                onClick={handleLogoutAction}
                value="로그아웃"
              />
            </>
          ) : ( // 일반 사용자일 때
            <>
              <input
                type="button"
                className="profile-button"
                onClick={handleProfileNavigate}
                value="마이페이지"
              />
              <input
                type="button"
                className="logout-button"
                onClick={handleLogoutAction}
                value="로그아웃"
              />
              <input
                type="button"
                className="header-role"
                onClick={() => {
                  if (!mentorProfileLoading) {
                    member.memberRole === "ROLE_MENTEE"
                      ? handleUpdateRole("ROLE_MENTOR")
                      : handleUpdateRole("ROLE_MENTEE");
                  }
                }}
                disabled={mentorProfileLoading} // 로드 중일 때 비활성화
                value={member.memberRole === "ROLE_MENTEE" ? "멘티" : "멘토"}
              />
            </>
          )
        ) : (
          // 비로그인 상태
          <>
            <input
              type="button"
              className="login-button"
              onClick={handleLoginNavigate}
              value="로그인"
            />
            <input
              type="button"
              className="join-button"
              onClick={handleJoinNavigate}
              value="회원가입"
            />
          </>
        )}
      </div>
    </div>
  );
}
