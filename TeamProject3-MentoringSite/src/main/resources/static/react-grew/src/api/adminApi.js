const BACKEND_SERVER = ""; // 백엔드 서버 URL 정의

//회원 목록 조회
export const adminMember = async(token,role,order,page,size) =>{
  const response = await fetch(`${BACKEND_SERVER}/admin/member?role=${role}&order=${order}&page=${page}&size=${size}`, {
  method: 'GET',
  headers: {
    'Authorization': `Bearer ${token}`, 
    'Content-Type': 'application/json'
  }
  });
  const responseJsonObject= await response.json();
  return responseJsonObject;
}

//멘토 상태별 목록 조회 
export const adminMentorByStatus = async (token,status,order,page,size) => {
  const response = await fetch(`${BACKEND_SERVER}/admin/mentor/status?status=${status}&order=${order}&page=${page}&size=${size}`, {
  method: 'GET',
  headers: {
    'Authorization': `Bearer ${token}`,
    'Content-Type': 'application/json'
  }
  });
  const responseJsonObject= await response.json();
  return responseJsonObject;
}

//멘토 상태 변경
export const adminUpdateMentorStatus = async(token, memberNo, statusNo) =>{
    const response = await fetch(`${BACKEND_SERVER}/admin/mentor/update-state/${memberNo}?status=${statusNo}`, {
  method: 'PUT',
  headers: {
    'Authorization': `Bearer ${token}`,
    'Content-Type': 'application/json'
  }
  });
  const responseJsonObject= await response.json();
  return responseJsonObject;
}

//멘토컨텐츠 목록 조회
export const adminMentorBoard = async(token, page, size)=>{
  const response = await fetch(`${BACKEND_SERVER}/admin/mentor-board?page=${page}&size=${size}`,{
    method: "GET",
    headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
    },
  });
  if (!response.ok) {
    throw new Error("API 호출 실패");
  }
  const responseJsonObject= await response.json();
  return responseJsonObject;
}

//게시글 목록 조회
export const adminInquiry = async(token, page, size) => {
  try {
    const response = await fetch(
        `${BACKEND_SERVER}/admin/inquiry/board?page=${page}&size=${size}`,
        {
            method: "GET",
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
            },
        }
    );
    if (!response.ok) {
        throw new Error(`API 요청 실패: ${response.status}`);
    }
    return await response.json();
  } catch (error) {      
      throw error;
  }
};
export const hideInquiry = async(token, inquiryNo)=>{
  try {
    const response = await fetch(///admin/inquiry/delete/11
      `${BACKEND_SERVER}/admin/inquiry/delete/${inquiryNo}`,
      {
        method : 'PUT',
        headers : {
          'Authorization':`Bearer ${token}`,
          'content-Type':'application/json',
        },
      }
    );
    if (!response.ok){
      throw new Error (`Error : ${response.statusText}`);
    }
    const data = await response.json();
    return data;
  }catch (error){
    throw error;
  }
};

export const adminMentorBoardWithSearch = async (token, search, page, size) => {//mentorBoardSearch
  const url = `${BACKEND_SERVER}/admin/mentor-board/search?search=${encodeURIComponent(search)}&page=${page}&size=${size}`;
  const config = {
      method: "GET", // HTTP 메서드
      headers: {
          "Authorization": `Bearer ${token}`, // 토큰 헤더 추가
          "Content-Type": "application/json", // 요청 데이터 타입
      },
  };

  try {
      const response = await fetch(url, config);
      // HTTP 응답이 성공적이지 않을 경우 처리
      if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
      }
      // JSON 응답 데이터를 반환
      const data = await response.json();
      return data;
  } catch (error) {
      throw error; // 오류를 호출한 곳으로 전달
  }
};

// 관리자 - 신고 목록 조회
export const getAdminReportList = async (token, filter,page) => {
    const response = await fetch(`${BACKEND_SERVER}/admin/report?filter=${filter}&page=${page}&size=10`,{
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
};

// 관리자 - 신고 상태 변경
export const updateReportStatusForAdmin = async (token, reportNo, status) => {
  const response = await fetch(`${BACKEND_SERVER}/admin/report/${reportNo}/status/${status}`, {
    method: 'PUT', // HTTP 메서드
    headers: {
      'Authorization': `Bearer ${token}`,
      "Content-Type": "application/json;charset=UTF-8", // 요청 헤더
    },
  });

  const responseJson = await response.json();
  return responseJson;  // 상태 업데이트 응답 반환
};

//카테고리별 게시판 목록 가져오기
export const adminCategoryInquiry = async (categoryNo, page, token, size) => {
  try {
    const response = await fetch(
      `${BACKEND_SERVER}/admin/inquiry/category/${categoryNo}?page=${page}&size=${size}`,{
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        }
      }
    );
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    const responseJsonObject = await response.json();
    return responseJsonObject;
  } catch (error) {
    throw error;
  }
};

