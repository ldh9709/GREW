// 백엔드 서버 URL 정의
const BACKEND_SERVER = " "; // 실제 백엔드 URL로 교체해주세요

// 관리자 - 신고 목록 조회 (Admin - 전체 신고 목록 조회)
export const getAdminReportList = async (filter, page = 0, size = 10) => {
  const response = await fetch(`${BACKEND_SERVER}/admin/reports?filter=${filter}&page=${page}&size=${size}`, {
    method: 'GET', // HTTP 메서드
    headers: {
      "Content-Type": "application/json;charset=UTF-8", // 요청 헤더
    },
  });

  const responseJson = await response.json();
  return responseJson;  // 신고 목록 응답 반환
};

// 관리자 - 신고 상태 변경 (Admin - 신고 상태 업데이트)
export const updateReportStatusForAdmin = async (reportNo, status) => {
  const response = await fetch(`${BACKEND_SERVER}/admin/report/${reportNo}/status?status=${status}`, {
    method: 'PUT', // HTTP 메서드
    headers: {
      "Content-Type": "application/json;charset=UTF-8", // 요청 헤더
    },
  });

  const responseJson = await response.json();
  return responseJson;  // 업데이트된 신고 상태 응답 반환
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
});
