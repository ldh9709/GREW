const BACKEND_SERVER = ""; // 백엔드 서버 URL 정의

//회원 목록 조회
export const adminMember = async(token,role,order) =>{
  const response = await fetch(`${BACKEND_SERVER}/admin/member?role=${role}&order=${order}`, {
  method: 'GET',
  headers: {
    'Authorization': `Bearer ${token}`, // 전달받은 JWT 토큰 사용
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

//신고 상태 상세 보기
{/*export const getAdminReportDetails=async(token, filter, page) => {

}*/}

// 관리자 - 신고 상태 변경
export const updateReportStatusForAdmin = async (reportNo, status) => {
  const response = await fetch(`${BACKEND_SERVER}/admin/report/${reportNo}/status?status=${status}`, {
    method: 'PUT', // HTTP 메서드
    headers: {
      "Content-Type": "application/json;charset=UTF-8", // 요청 헤더
    },
  });

  const responseJson = await response.json();
  return responseJson;  // 상태 업데이트 응답 반환
};


