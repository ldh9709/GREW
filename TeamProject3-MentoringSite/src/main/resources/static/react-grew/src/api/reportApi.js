const BACKEND_SERVER = ""; // 실제 백엔드 서버 주소로 변경

/*
PUT  /report/{report_no}/resolved               :신고상태 <처리완료>
PUT  /report/{report_no}/in-progress            :신고상태 <검토중>
PUT  /report/{report_no}/false-report           :신고상태 <무고처리>
GET  /report?filter=1&page=0&size=10            :전체 목록 조회
POST /report                                    :신고 등록
GET  /report/detail/{report_no}                 :특정 신고 상세보기
*/


// 신고 상태 처리완료 
export const updateReportResolved = async (reportNo) => {
    const response = await fetch(`${BACKEND_SERVER}/report/${reportNo}/resolved`, {
        method: 'PUT',
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
        },
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
};

// 신고 상태 검토중으
export const updateReportInProgress = async (reportNo) => {
    const response = await fetch(`${BACKEND_SERVER}/report/${reportNo}/in-progress`, {
        method: 'PUT',
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
        },
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
};

// 신고 상태 무고처리
export const updateReportFalseReport = async (reportNo) => {
    const response = await fetch(`${BACKEND_SERVER}/report/${reportNo}/false-report`, {
        method: 'PUT',
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
        },
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
};

// 전체 신고 목록 조회
export const getReports = async (filter = 1, page) => {
    const response = await fetch(`${BACKEND_SERVER}/report?filter=${filter}&page=${page}&size=10`, {
        method: 'GET',
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
};

// 신고 등록
export const createReport = async (token,reportData) => {
    const response = await fetch(`${BACKEND_SERVER}/report`, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(reportData),
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
};

// 특정 신고 상세보기
export const getReportDetail = async (reportNo) => {
    const response = await fetch(`${BACKEND_SERVER}/report/detail/${reportNo}`, {
        method: 'GET',
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
};