import "../../css/memberPage.css"
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import * as memberApi from "../../api/memberApi";
import google from '../../image/google.png';
import naver from '../../image/naver.png';
import kakao from '../../image/kakao.png';
import { useMemberAuth } from "../../util/AuthContext";

const MemberLoginFormPage = () => {
  const navigate = useNavigate();
  const { login } = useMemberAuth();
  const [member, setMember] = useState({
    memberId: "",
    memberPassword: "",
  });
  const BACKEND_SERVER = "http://localhost:8080";

  const handleChangeLoginForm = (e) => {
    setMember({ ...member, [e.target.name]: e.target.value });
  };

  const loginAction = async (e) => {
    e.preventDefault();
    try {
      const responseJsonObject = await memberApi.loginAction(member);

      /* 로그인 성공해서 토큰이 있을 때 */
      if (responseJsonObject.accessToken) {

        login(responseJsonObject.accessToken);

        if(responseJsonObject.mentorProfileNo !== 0) {
          /* 멘토 프로필 START */
          const mentorProfileResponse = await memberApi.getMentorProfile(responseJsonObject.mentorProfileNo);
          // categoryNo가 26인지 확인
          const isMentorCategory = mentorProfileResponse?.data?.categoryNo === 26;
          // 역할에 따라 페이지 이동
          navigate(isMentorCategory ? "/mentor/join" : "/main");
        } else {
          navigate('/main');
        }

      } else {
        console.log("responseJsonObject.status : ", responseJsonObject.status);
        alert("회원정보가 일치하지 않습니다.");
      }
    } catch(error) {
      console.error("로그인 요청 오류: ", error);
    }
  };
  
  return (
    <div className="member-login-container">
    <h2 className="member-login-title">로그인</h2>
    <form className="member-login-form" onSubmit={loginAction}>
        <div className="member-login-div">
            <input type="text" name="memberId" className="member-login-input" placeholder="아이디" value={member.memberId} onChange={handleChangeLoginForm} required />
        </div>
        <div className="member-login-div">
            <input type="password" name="memberPassword" className="member-login-input" placeholder="비밀번호" value={member.memberPassword} onChange={handleChangeLoginForm} required />
        </div>
        <Link to="/member/find-id" className="member-findPassword-link">
            <p className="member-findPassword">아이디를 잊으셨나요?</p>
        </Link>
        <Link to="/member/find-password" className="member-findPassword-link">
            <p className="member-findPassword">비밀번호를 잊으셨나요?</p>
        </Link>
        <button type="submit" className="member-login-button">로그인</button>
        <Link to="/member/join" className="member-join-link">
            <p>회원이 아니신가요? 회원가입</p>
        </Link>
        <h3 className="member-login-sub-title">- SNS 간편 로그인 -</h3>
        <div className="member-sns-login-group">
        <Link to={`${BACKEND_SERVER}/oauth2/authorization/google`}>
                <img src={google} alt="Google" className="member-sns-icon" />
            </Link>
            <Link to={`${BACKEND_SERVER}/oauth2/authorization/naver`}>
                <img src={naver} alt="Naver" className="member-sns-icon" />
            </Link>
            <Link to={`${BACKEND_SERVER}/oauth2/authorization/kakao`}>
                <img src={kakao} alt="Kakao" className="member-sns-icon" />
            </Link>
          </div>
      </form>
      </div>

  );
};

export default MemberLoginFormPage;
