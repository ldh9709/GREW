import axios from "axios"
import { getCookie } from "../util/cookieUtil";

const BACKEND_SERVER='http://localhost:8080';

export const loginAction = async (sendJsonObject) => {
    console.log("Request Data: ", sendJsonObject);

    const header = {headers: {"Content-Type": "x-www-form-urlencoded"}}

    const form = new FormData()
    form.append('username', sendJsonObject.memberId)
    form.append('password', sendJsonObject.memberPassword)

    console.log("memberId : " , sendJsonObject.memberId);
    console.log("memberPassword : ", sendJsonObject.memberPassword);

    const response = await axios.post("http://localhost:8080/login", form, header);

    console.log("response : " , response)

    return response.data;
}

export const joinAction = async (member, tempCode) => {
    console.log("Request Data: ", member);
    console.log("Request Data: ", tempCode);

    const response = await fetch(`${BACKEND_SERVER}/api/member/createMember`, {
        method:'POST', 
        headers:{
            'Content-type':'application/json'
        },
        body:JSON.stringify({
            memberDto : member,
            tempCode: tempCode
        })
    });

    const resultJsonObject = await response.json();
    console.log("Response Data:", resultJsonObject);
    return resultJsonObject;

}

export const sendJoinCode = async (sendJsonObject) => {
    console.log("Request Data : ", sendJsonObject);

    const response = await fetch(`${BACKEND_SERVER}/api/member/sendJoinCode`, {
        method:'POST',
        headers:{
            'Content-type':'application/json'
        },
        body:JSON.stringify(sendJsonObject)
    });

    const resultJsonObject = await response.json();
    console.log("Response Data:", resultJsonObject);
    return resultJsonObject;
}

export const memberProfile = async () => {
    // 저장된 토큰 가져오기
    const memberCookie = getCookie("member");
    console.log("memberCookie : ", memberCookie);

    const token = memberCookie.accessToken;

    const response = await fetch(`${BACKEND_SERVER}/api/member/profile`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}` // Authorization 헤더에 JWT 토큰 추가
      },
    });
    
    // 서버 응답 처리
    const resultJsonObject = await response.json();
    return resultJsonObject;
  };
