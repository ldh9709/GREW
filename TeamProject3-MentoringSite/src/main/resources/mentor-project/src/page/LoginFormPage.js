import React, { useState } from "react";
import "../css/LoginFormPage.css";
import { Link, useNavigate } from "react-router-dom";
import * as memberApi from "../api/memberApi";


function LoginFormPage() {
  const navigate = useNavigate();

  const [member, setMember] = useState({
    memberId: "",
    memberPassword: "",
  });

  const handleChangeLoginForm = (e) => {
    setMember({
      ...member,
      [e.target.name]: e.target.value
    });
    console.log(e.target.name);
    console.log(e.target.value);
  };

  const loginAction = async (e) => {
    e.preventDefault()//새로고침을 방지

    const responseJsonObject = await memberApi.loginAction(member);
    
    console.log(member);
    console.log(responseJsonObject);

    switch (responseJsonObject.status) {

      case 1:
        // 로그인 성공 처리
        navigate("/main");
        console.log("성공 : ", responseJsonObject)
        break;
      default:
        console.log("실패 : ", responseJsonObject)
        
        break;
    }
  };

  return (
    <div className="signup-container">
      <h2 className="signup-title">로그인</h2>

      <form className="signup-form" onSubmit={loginAction}>
        <div>
          <label htmlFor="id">아이디</label>
          <input
            type="text"
            id="memberId"
            name="memberId"
            placeholder="아이디를 입력하세요"
            value={member.memberId}
            onChange={handleChangeLoginForm}
            required
          />
        </div>

        <div>
          <label htmlFor="password">비밀번호</label>
          <input
            type="password"
            id="password"
            name="memberPassword"
            placeholder="비밀번호를 입력하세요"
            value={member.memberPassword}
            onChange={handleChangeLoginForm}
            required
          />
        </div>

        <button type="submit" className="signup-button" onClick={loginAction}>
          로그인
        </button>

        <button
          type="button"
          className="signup-button"
          onClick={() => navigate("/join")}
        >
          회원가입
        </button>

        <h3 className="signup-sub-title">SNS 로그인</h3>

        <div className="form-sns-group">
          <Link to={"http://localhost:8080/oauth2/authorization/google"}>
          <img
            src="google_icon.svg"
            alt="google"
            style={{ height: "50px", marginRight: "50px" }}
          />
          </Link>

          <Link to={"http://localhost:8080/oauth2/authorization/naver"}>
          <img
            src="naver_icon.png"
            alt="naver"
            style={{ height: "50px", marginRight: "50px" }}
          />
          </Link>

          <Link to={"http://localhost:8080/oauth2/authorization/kakao"}>
          <img
            src="kakao_icon.svg"
            alt="Kakao"
            style={{ height: "50px", marginRight: "50px" }}
          />
          </Link>
        </div>
      </form>
    </div>
  );
}

export default LoginFormPage;
