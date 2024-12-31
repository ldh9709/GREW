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

//게시글 목록 조회
export const adminInquiry = async(token, category, page, size) => {
  const response = await fetch(`${BACKEND_SERVER}/admin/inquiry?category=${category}&page=${page}&size=${size}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
  });
  const responseJsonObject = await response.json();
  return responseJsonObject;
}

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


