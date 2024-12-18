import React, { useState } from "react";
import "../css/LoginFormPage.css";
import { Link, useNavigate } from "react-router-dom";
import * as memberApi from "../api/memberApi";
import { setCookie, getCookie } from "../util/cookieUtil";

const LoginFormPage = () => {
  const navigate = useNavigate();

  const [member, setMember] = useState({
    memberId: "",
    memberPassword: "",
  });

  const handleChangeLoginForm = (e) => {
    setMember({ ...member, [e.target.name]: e.target.value });
  };

  const loginAction = async (e) => {
    e.preventDefault();
    const responseJsonObject = await memberApi.loginAction(member);
    console.log("responseJsonObject.memberId : ", responseJsonObject.memberId);

    /* 로그인 성공해서 토큰이 있을 때 */
    if (responseJsonObject.accessToken) {

      /* 쿠키 설정 */
      setCookie("member", JSON.stringify(responseJsonObject), 1);
      console.log("getCookies : " , getCookie("member"));
      console.log("getCookies.accessToken : " , getCookie("member").accessToken);

      /* 로그인 성공 시 이동 */
      navigate("/main");
    } else {
      console.log("responseJsonObject.status : ", responseJsonObject.status);
      alert("로그인 실패");
    }
  };

  return (
    <div className="login-container">
      <h2 className="login-title">로그인</h2>
      <form className="login-form" onSubmit={loginAction}>
        <div className="login-input">
          <input
            type="text"
            name="memberId"
            placeholder="아이디"
            value={member.memberId}
            onChange={handleChangeLoginForm}
            required
          />
        </div>
        <div className="login-input">
          <input
            type="password"
            name="memberPassword"
            placeholder="비밀번호"
            value={member.memberPassword}
            onChange={handleChangeLoginForm}
            required
          />
        </div>
        <button type="submit" className="login-button">
          로그인
        </button>
        <Link to="/join" className="join-link">
          <p>회원이 아니신가요? 회원가입</p>
        </Link>
        <h3 className="login-sub-title">SNS 로그인</h3>
        <div className="sns-login-group">
          <Link to="http://localhost:8080/oauth2/authorization/google">
            <img src="google_icon.svg" alt="Google" className="sns-icon" />
          </Link>
          <Link to="http://localhost:8080/oauth2/authorization/naver">
            <img src="naver_icon.png" alt="Naver" className="sns-icon" />
          </Link>
          <Link to="http://localhost:8080/oauth2/authorization/kakao">
            <img src="kakao_icon.svg" alt="Kakao" className="sns-icon" />
          </Link>
        </div>
      </form>
    </div>
  );
};

export default LoginFormPage;
