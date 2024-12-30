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
    console.error("전체 게시글 API 호출 오류:", error);
    throw error;
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

//category별 게시판 목록 가져오기
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
    console.error("API 호출 오류 : ", error);
    throw error;
  }
};
