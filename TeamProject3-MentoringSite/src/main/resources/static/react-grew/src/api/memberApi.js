import axios from "axios"

const BACKEND_SERVER = "";
/*
GET  /member/check-memberId                 :아이디 중복 체크
POST /member                                :회원 가입
AXIOS/login                                 :로그인
POST /logout                                :로그아웃
PUT  /member/{memberNo}                     :회원 정보 수정
PUT  /member/{memberNo}/status/{statusNo}   :회원 상태 변경
GET  /member                                :회원 전체 조회
GET  /member/{memberNo}                     :특정 회원 조회
GET  /member/mentee-summary/{memberNo}      :멘티 회원 활동정보 요약 조회
GET  /member/mentor-summary/{memberNo}      :멘토 회원 활동정보 요약 조회

*/

//아이디 중복 체크

//팔로잉 리스트 조회
export const followList = async()=>{
    const response = await fetch(`${BACKEND_SERVER}/follow/mentee`,{
        method:'GET'
    })
    const responseJsonObject = await response.json();
    return responseJsonObject;
}

//로그인
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

export const logout = async (token) => {
    const response = await fetch(`${BACKEND_SERVER}/logout`, {
        method: 'POST',
        credentials: 'include',// 브라우저가 자동으로 쿠키를 포함하도록 설정
        headers: {
            'Content-Type': 'application/json'
        },
    });
    console.log("로그아웃 시 반환객체 : ",response);
    if(response.ok) {
        return true;
    } else {
        return false;
    }
};
  

//멘티 회원가입
export const menteeJoinAction = async (member, tempCode) => {
    console.log("Request Data: ", member);
    console.log("Request Data: ", tempCode);

    const response = await fetch(`${BACKEND_SERVER}/member/createMember`, {
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

//멘토 회원가입
export const mentorJoinAction = async (member, tempCode) => {
    console.log("Request Data: ", member);
    console.log("Request Data: ", tempCode);

    const response = await fetch(`${BACKEND_SERVER}/member/createMember/mentor`, {
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

//멘토 프로필 생성
export const mentorProfileCreateAction = async (memberNo, mentor) => {
    console.log("Request Data: ", memberNo);
    console.log("Request Data: ", mentor);
    const response = await fetch(`${BACKEND_SERVER}/mentor-profile/${memberNo}/create-profile`, {
        method:'POST', 
        headers:{
            'Content-type':'application/json'
        },
        body: JSON.stringify({
            memberNo: memberNo,
            categoryNo: mentor.categoryNo,
            mentorIntroduce: mentor.mentorIntroduce,
            mentorCareer: mentor.mentorCareer,
            mentorImage: mentor.mentorImage,
          })
    });

    const resultJsonObject = await response.json();
    console.log("Response Data:", resultJsonObject);
    return resultJsonObject;

}

//회원 정보 수정
export const updateAction = async (sendJsonObject) => {
    console.log("updateMember : ", sendJsonObject);

    const response = await fetch(`${BACKEND_SERVER}/member/profile/edit/${sendJsonObject.memberNo}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body:JSON.stringify(sendJsonObject)
      });
      const resultJsonObject = await response.json();

      console.log("resultJsonObject : ", resultJsonObject);

      return resultJsonObject;
    
}
//회원 상태 변경

//회원 전체 조회

//특정 회원 조회
export const memberInfo = async (token,memberNo) => {
    const response = await fetch(`${BACKEND_SERVER}/member/member-info?memberNo=${memberNo}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
}

//멤버 프로필 조회
export const memberProfile = async (token) => {
    const response = await fetch(`${BACKEND_SERVER}/member/profile`, {
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

//멘토 프로필 조회
export const getMentorProfile = async (memberNo) => {
    const response = await fetch(`${BACKEND_SERVER}/mentor-profile/`)
}

//인증코드 메일 발송
export const sendJoinCode = async (sendJsonObject) => {
    console.log("Request Data : ", sendJsonObject);

    const response = await fetch(`${BACKEND_SERVER}/member/sendJoinCode`, {
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

  
//멘티 회원 활동정보 요약
export const menteeSummary = async (token) => {

    const response = await fetch(`${BACKEND_SERVER}/member/mentee-summary`,{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}` // Authorization 헤더에 JWT 토큰 추가
          },
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
}

//멘토 회원 활동정보 요약
export const mentorSummary = async (token) => {

    const response = await fetch(`${BACKEND_SERVER}/member/mentor-summary`,{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}` // Authorization 헤더에 JWT 토큰 추가
          },
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
}
