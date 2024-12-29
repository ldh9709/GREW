import React, { useEffect, useState } from 'react';
import * as adminApi from '../../api/adminApi';
import { getCookie } from "../../util/cookieUtil";

export const UserCard = () => {
    const memberCookie = getCookie("member");
    const token = memberCookie.accessToken;

    const [reports, setReports] = useState([]); // 신고 목록
    const [filter, setFilter] = useState(1); // 초기 필터 상태 (1: 전체)
    const [page, setPage] = useState(0); // 페이지 번호

    // 신고 목록 조회
    const fetchReports = async (token, filter, page) => {
        try {
            const response = await adminApi.getAdminReportList(token, filter, page);
            setReports(response.data);
            console.log(response);
        } catch (error) {
            console.error("신고 목록 조회 실패", error);
        }
    };

    // 필터 변경 핸들러
    const handleFilterChange = (event) => {
        const selectedFilter = parseInt(event.target.value, 10); // 드롭다운에서 선택된 필터 값
        setFilter(selectedFilter);
        fetchReports(token, selectedFilter, page); // 선택된 필터 값에 맞는 신고 목록 조회
    };

    // 신고 상태 업데이트 핸들러
    const handleStatusUpdate = async (reportNo, status) => {
        try {
            const response = await adminApi.updateReportStatusForAdmin(token, reportNo, status);
            alert(`신고 상태가 '${status}'로 변경되었습니다.`);
            fetchReports(token, filter, page); // 상태 업데이트 후 목록 새로고침
        } catch (error) {
            console.error("신고 상태 업데이트 실패", error);
            alert("상태 업데이트에 실패했습니다.");
        }
    };

    //신고 유형 이름
    const typeName = (type) => {
        switch (type) {
            case "MEMBER":
                return '유저'
                break;
        
            case "ANSWER":
                return '답변게시글'
                break;
                
                case "INQUIRY":
                return '질문게시글'
                break;
        }
    }

    useEffect(() => {
        if (token) {
            fetchReports(token, filter, page); // 초기 데이터 로드
        }
    }, [token, filter, page]);

    return (
        <div className="admin-member-container">
            {/* 드롭다운 */}
            <div className="dropdown">
                <select onChange={handleFilterChange} value={filter}>
                    <option value={1}>전체보기</option>
                    <option value={2}>접수순서</option>                    
                </select>
            </div>

            {/* 신고 목록 테이블 */}
            <table className="admin-table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>신고 유형</th>
                        <th>신고 사유</th>
                        <th>신고 내용</th>
                        <th>신고 일자</th>
                        <th>처리 일자</th>
                        <th>신고 상태</th>
                        <th>신고자</th>
                        <th>처리 상황</th>
                    </tr>
                </thead>
                <tbody>
                    {reports && reports.length > 0 ? (
                        reports.map((report, index) => (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td>{typeName(report.reportType)}</td>
                                <td>{report.reportReason}</td>
                                <td>{report.reportContent}</td>
                                <td>{report.reportDate.substring(0,10)}</td>
                                <td>{report.resolvedDate.substring(0,10)}</td>
                                <td>{report.reportStatus}</td>
                                <td>{report.memberNo}</td>
                                <td>
                                    <button className="check"
                                    onClick={() => handleStatusUpdate(report.id, "IN_PROGRESS")}
                                    >신고</button>
                                    <button className="check"
                                    onClick={() => handleStatusUpdate(report.id, "IN_PROGRESS")}
                                    >무고</button>
                                </td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="10">신고 목록이 없습니다.</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
};

export default UserCard;
