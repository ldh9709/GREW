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

/*{const fetchReports = async () => {
  try {
    const token = "사용자가 로그인한 후 받은 액세스 토큰";  // 로그인 후 받은 토큰을 여기 넣어야 합니다.
    const response = await fetch('/admin/reports?filter=1&page=0&size=10', {
      method: 'GET', // GET 요청
      headers: {
        "Authorization": `Bearer ${token}`,  // 토큰을 헤더에 포함
        "Content-Type": "application/json;charset=UTF-8",
      },
    });
    
    if (!response.ok) {
      throw new Error('신고 목록을 가져오는 데 실패했습니다.');
    }

    const data = await response.json();
    if (data && Array.isArray(data.data)) {
      setReports(data.data); // 정상적인 배열이라면 상태에 저장
    } else {
      setReports([]);  // 빈 배열로 설정
      setError('유효하지 않은 데이터 형식입니다.'); // 에러 처리
    }
    console.log("response : ", data);
  } catch (err) {
    console.log("ERR : ", err);
    setError('신고 목록을 가져오는 데 실패했습니다.'); // 에러 처리
  } finally {
    setLoading(false); // 로딩 종료
  }
};

// 사용 예시:
// 1. 신고 목록 조회 (관리자)
const filter = 1; // 예시 필터 (실제 필터 값으로 변경하세요)
const page = 0;   // 페이지 번호
const size = 10;  // 페이지 당 항목 수
getAdminReportList(filter, page, size).then(data => {
  console.log(data); // 신고 목록 데이터 처리
});

// 2. 신고 상태 변경 (관리자)
const reportNo = 123;  // 예시 신고 번호 (실제 신고 번호로 변경하세요)
const status = 'IN_PROGRESS';  // 예시 상태 (IN_PROGRESS, RESOLVED, FALSE_REPORT)
updateReportStatusForAdmin(reportNo, status).then(data => {
  console.log(data); // 업데이트된 신고 데이터 처리
});}*/
