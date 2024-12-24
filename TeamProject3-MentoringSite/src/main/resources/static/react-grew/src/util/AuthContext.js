import React, { createContext, useContext, useEffect, useState } from "react";
import { getCookie, setCookie, removeCookie } from "./cookieUtil";
import { jwtDecode } from "jwt-decode";

/* 컴포넌트 전역에서 데이터를 공유하는 Context생성 */
const AuthContext = createContext();

// AuthProvider 컴포넌트: 전역 인증 상태를 관리, children : AuthProvider로 감싸진 컴포넌트들
export const AuthProvider = ({ children }) => {
  //token과 member정보를 가지고 있는 member객체 선언
  const [member, setMember] = useState({
    token:'',
    member:{}
  });

  //실행 시 한 번만 실행
  useEffect(() => {
    //member라는 이름의 쿠키를 가져온다
    const cookie = getCookie("member"); 
    //cookie나 cookie의 엑세스 토큰이 존재하면
    if (cookie && cookie.accessToken) {
      //엑세스 토큰을 디코딩(디코딩한 토큰에는 member정보가 들어가있음)
      const decoded = jwtDecode(cookie.accessToken); 
      //member객체에 토큰과 member정보 대입
      setMember({
        token:cookie.accessToken,
        member:decoded
      }); 
    }
  }, []);

  //로그인 시에 실행
  const login = (token) => {
    // 토큰과 디코딩된 사용자 정보를 Context에 저장
    const decoded = jwtDecode(token);
        setMember({
            token,
            member: decoded,
        });
    };

  return (
    <AuthContext.Provider value={{ token: member.token, member: member.member , login}}>
      {children}
    </AuthContext.Provider>
  );
};

export const useMemberAuth = () => useContext(AuthContext);
