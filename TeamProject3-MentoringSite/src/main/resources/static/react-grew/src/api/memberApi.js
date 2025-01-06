import axios from "axios"

const BACKEND_SERVER = "";
/*
GET  /member/check-memberId                 :아이디 중복 체크
POST /member                                :회원 가입
AXIOS/login                                 :로그인
POST /logout                                :로그아웃
POST /member/find-password                  :비밀번호 찾기
PUT  /member/{memberNo}                     :회원 정보 수정
PUT  /member/{memberNo}/status/{statusNo}   :회원 상태 변경
PUT  /member/update-role/{role}             :회원 권한 변경
GET  /member                                :회원 전체 조회
GET  /member/{memberNo}                     :특정 회원 조회
GET  /member/mentee-summary/{memberNo}      :멘티 회원 활동정보 요약 조회
GET  /member/mentor-summary/{memberNo}      :멘토 회원 활동정보 요약 조회

*/

//아이디 중복 체크
export const checkIdDupl = async (sendJsonObject) => {
    const response = await fetch(`${BACKEND_SERVER}/member/check-memberId?memberId=${encodeURIComponent(sendJsonObject.memberId)}`, {
        method:'GET',
        headers: {
            'Content-type': 'application/json'
        },
    });
    
    const responseJsonObject = await response.json();
    return responseJsonObject;
};

//이메일 중복 체크
export const checkEmailDupl = async (sendJsonObject) => {
    const response = await fetch(`${BACKEND_SERVER}/member/check-memberEmail?memberEmail=${encodeURIComponent(sendJsonObject.memberEmail)}`, {
        method: 'GET',
        headers: {
            'Content-type': 'application/json'
        },
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
}

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

    const header = {headers: {"Content-Type": "application/x-www-form-urlencoded"}, withCredentials: true }

    const form = new FormData()
    form.append('username', sendJsonObject.memberId)
    form.append('password', sendJsonObject.memberPassword)

    const response = await axios.post("http://localhost:8080/login", form, header,);
    console.log("Response : ", response);
    return response.data;
}
//로그아웃
export const logout = async () => {
    const response = await fetch(`${BACKEND_SERVER}/logout`, {
        method: 'POST',
        credentials: 'include',// 브라우저가 자동으로 쿠키를 포함하도록 설정
        headers: {
            'Content-Type': 'application/json'
        },
    });
    return response.url;
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

/* 1. 아이디 찾기 - 인증번호 전송  */
export const sendMailFindId = async (memberDto) => {
    const response = await fetch(`${BACKEND_SERVER}/member/findId/sendEmail`, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body:JSON.stringify({
            memberName:memberDto.memberName,
            memberEmail:memberDto.memberEmail    
        })
    });
    const resultJsonObject = await response.json();

    return resultJsonObject;
}

/* 2. 아이디 찾기 - 인증번호 확인  */
export const certificationCodeFindId = async (memberEmail, inputCode) => {
    const response = await fetch(`${BACKEND_SERVER}/member/findId/certificationCode`, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body:JSON.stringify({
            memberEmail:memberEmail, 
            inputCode:inputCode
        })
    });
    const resultJsonObject = await response.json();

    return resultJsonObject;
}

/* 비밀번호 찾기 */
export const findPassword = async (member) => {
    const response = await fetch(`${BACKEND_SERVER}/member/findPassword`, {
        method: 'POST',
        headers: {
           'Content-Type': 'application/json'
        },
        body:JSON.stringify({
            memberName : member.memberName,
            email : member.memberEmail
        })
    });
    const resultJsonObject = await response.json();
    console.log("Response Data:", resultJsonObject);
    return resultJsonObject;
}

/* 1. 이메일 인증 - 인증번호 전송  */
export const sendVerificationCode = async (memberEmail) => {
    const response = await fetch(`${BACKEND_SERVER}/member/sendVerificationCode`, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body:JSON.stringify({
            email:memberEmail    
        })
    });
    const resultJsonObject = await response.json();

    return resultJsonObject;
}

/* 2. 이메일 인증 - 인증번호 확인  */
export const verificationInputCode = async (memberEmail, inputCode) => {
    console.log("요청 URL:", `${BACKEND_SERVER}/sendVerificationCode/verificationCode`);
    console.log("요청 데이터:", { memberEmail, inputCode });

    const response = await fetch(`${BACKEND_SERVER}/member/sendVerificationCode/verificationCode`, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body:JSON.stringify({
            memberEmail:memberEmail, 
            inputCode:inputCode
        })
    });
    const resultJsonObject = await response.json();
    console.log("인증번호 resultJsonObject:", resultJsonObject);
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

//멘토 프로필 생성(생성)
export const mentorProfileCreateAction = async (token, mentor) => {
    const response = await fetch(`${BACKEND_SERVER}/mentor-profile/create-profile`, {
        method:'POST', 
        headers:{
            'Content-type':'application/json',
            'Authorization': `Bearer ${token}` // Authorization 헤더에 JWT 토큰 추가
        },
        body: JSON.stringify({
            categoryNo: mentor.categoryNo,
            mentorStatus: mentor.mentorStatus,
            mentorIntroduce: mentor.mentorIntroduce,
            mentorHeadline: mentor.mentorHeadline,
            careerDtos: mentor.careerDtos,
            mentorImage: mentor.mentorImage
          })
    });

    const resultJsonObject = await response.json();
    console.log("Response Data:", resultJsonObject);
    return resultJsonObject;

}

//멘토 프로필 생성(수정)
export const mentorProfileUpdateAction = async (mentorProfileNo, mentor) => {
    console.log("mentorProfileUpdateAction mentorProfileNo: ", mentorProfileNo);
    console.log("mentorProfileUpdateAction mentor: ", mentor);
    const response = await fetch(`${BACKEND_SERVER}/mentor-profile/${mentorProfileNo}`, {
        method:'PUT', 
        headers:{
            'Content-type':'application/json'
        },
        body: JSON.stringify({
            categoryNo: mentor.categoryNo,
            mentorIntroduce: mentor.mentorIntroduce,
            mentorHeadline: mentor.mentorHeadline,
            careerDtos: mentor.careerDtos,
            mentorImage: mentor.mentorImage,
          })
    });

    const resultJsonObject = await response.json();
    console.log("Response Data:", resultJsonObject);
    return resultJsonObject;

}

//회원 정보 수정
export const updateAction = async (memberDto, token) => {

    const response = await fetch(`${BACKEND_SERVER}/member/profile/modify`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body:JSON.stringify(memberDto)
      });
      const resultJsonObject = await response.json();

      console.log("resultJsonObject : ", resultJsonObject);

      return resultJsonObject;
    
}

//회원 탈퇴
export const deleteAction = async (token) => {

    const response = await fetch(`${BACKEND_SERVER}/member/profile/delete`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
      });
      const resultJsonObject = await response.json();
      console.log("resultJsonObject : ", resultJsonObject);

      return resultJsonObject;
    
}

//회원 권한 변경
export const updateMemberRole = async(token,role) => {
    const response = await fetch(`${BACKEND_SERVER}/member/update-role/${role}`,{
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
    })

    const responseJsonObject = await response.json();
    return responseJsonObject;
}

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
export const getMentorProfile = async (mentorProfileNo) => {
    const response = await fetch(`${BACKEND_SERVER}/mentor-profile/${mentorProfileNo}`, {
        method:'GET',
        headers: {
            'Content-Type': 'application/json',
            //'Authorization': `Bearer ${token}` // Authorization 헤더에 JWT 토큰 추가
        },
    });
    // 서버 응답 처리
    const resultJsonObject = await response.json();
    return resultJsonObject;
};

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

//멤버 넘버로 멤버객체찾기
export const getMemberByMemberNo = async (memberNo) => {

    const response = await fetch(`${BACKEND_SERVER}/member/member-info?memberNo=${memberNo}`,{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
          },
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
}

/* 멘토 프로필 이미지 업로드 */
export const uploadMentorProfileImage = async (mentorProfileNo, file) => {
    const formData = new FormData();
    formData.append("file", file);
  
    const response = await fetch(`${BACKEND_SERVER}/mentor-profile/${mentorProfileNo}/upload-image`, {
      method: "POST",
      body: formData,
    });
  
    const responseJson = await response.json();
    return responseJson;
  };

export const getCareer = async (mentorProfileNo) => {
    const response = await fetch(`${BACKEND_SERVER}/mentor-profile/career/${mentorProfileNo}`, {
        method:'GET'
    });
    
    const responseJsonObject = await response.json();
    return responseJsonObject;
};