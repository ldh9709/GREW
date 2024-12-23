import React, { createContext, useContext, useEffect, useState } from "react";
import { getCookie, setCookie, removeCookie } from "./cookieUtil";
import { jwtDecode } from "jwt-decode";

const AuthContext = createContext();

// AuthProvider 컴포넌트: 전역 인증 상태를 관리
export const AuthProvider = ({ children }) => {
  const [member, setMember] = useState({
    token:'',
    member:{}
  });

  useEffect(() => {
    const cookie = getCookie("member"); 
    if (cookie && cookie.accessToken) {
      const decoded = jwtDecode(cookie.accessToken); 
      setMember({
        token:cookie.accessToken,
        member:decoded
      }); 
    }
  }, []);

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
