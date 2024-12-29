import { useMemberAuth } from "../../util/AuthContext";
import React, { useEffect, useState } from 'react';
import * as adminApi from '../../api/adminApi';
import PagenationItem from '../PagenationItem';
import AdminReportDetail from "./AdminReportDetail";
import * as reportUtil from '../../util/reportUtil'

export const UserCard = () => {
    const { token } = useMemberAuth();

    const [reports, setReports] = useState([]); // 신고 목록
    const [selectReport ,setSelectReport] = useState(null)
    const [filter, setFilter] = useState(1); // 초기 필터 상태 (1: 전체)
    const [currentPage, setCurrentPage] = useState(1); // 현재 페이지 번호
    const [totalPages, setTotalPages] = useState(0); // 전체 페이지 수

    // 신고 목록 조회
    const fetchReports = async (filter, page) => {
        try {
            const response = await adminApi.getAdminReportList(token, filter, page);
            setReports(response.data.content);
            setTotalPages(response.data.totalPages);
            console.log(response);
        } catch (error) {
            console.error("신고 목록 조회 실패", error);
        }
    };

    // 페이지 변경 시 데이터 갱신
    const paginate = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    // 페이지 번호 버튼 표시
    const pageNumbers = [];
    for (let i = 1; i <= totalPages; i++) {
        pageNumbers.push(i);
    }


    // 필터 변경 핸들러
    const handleFilterChange = (event) => {
        const selectedFilter = parseInt(event.target.value, 10); // 드롭다운에서 선택된 필터 값
        setFilter(selectedFilter);
        fetchReports(token, selectedFilter, currentPage); // 선택된 필터 값에 맞는 신고 목록 조회
    };

    // 신고 상태 업데이트 핸들러
    const handleStatusUpdate = async (reportNo, status) => {
        try {
            const response = await adminApi.updateReportStatusForAdmin(token, reportNo, status);
            alert(`신고 상태가 '${status}'로 변경되었습니다.`);
            fetchReports(token, filter, currentPage); // 상태 업데이트 후 목록 새로고침
        } catch (error) {
            console.error("신고 상태 업데이트 실패", error);
            alert("상태 업데이트에 실패했습니다.");
        }
    };

    //아이디 마스킹 
    const maskId= (id) =>{
        if(id.length <=2 ){
            return id[0]+"*".repeat(id.length-1);
        }else {
            return id[0]+"*".repeat(id.length-2) + id[id.length-1];
        }
    }

    //신고 행 클릭 시 
    const handleReportClick = (report) =>{
        setSelectReport(report)
    }

    useEffect(() => {
        if (token) {
            fetchReports(filter, currentPage-1); // 초기 데이터 로드
        }
    }, [filter, currentPage]);

    return (
        <>
        <div className="admin-table-container">
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
                            <tr key={index} onClick={ () => {handleReportClick(report)}}>
                                <td>{index + 1}</td>
                                <td>{reportUtil.typeName(report.reportType)}</td>
                                <td>{reportUtil.reasonName(report.reportReason)}</td>
                                <td>{report.reportContent.length > 10 ? (report.reportContent.substring(0,10) + '...' ) : (report.reportContent)}</td>
                                <td>{report.reportDate.substring(0,10)}</td>
                                <td>{report.reportDate===report.resolvedDate ? " - " : report.resolvedDate.substring(0,10)}</td>
                                <td>{reportUtil.reportStatus(report.reportStatus)}</td>
                                <td>{maskId(report.memberId)}</td>
                                <td>
                                    <button className="in-progress"
                                    onClick={() => handleStatusUpdate(report.id, "IN_PROGRESS")}
                                    >처리</button>
                                    <button className="false-report"
                                    onClick={() => handleStatusUpdate(report.id, "IN_PROGRESS")}
                                    >무고</button>
                                </td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="9">신고 목록이 없습니다.</td>
                        </tr>
                    )}
                </tbody>
            </table>
            
            { selectReport && (<AdminReportDetail report={selectReport} onClose={() => setSelectReport(null)}/> )}

            <div className="admin-pagenation">
                <PagenationItem
                    currentPage={currentPage}
                    totalPages={totalPages}
                    paginate={paginate}
                />
            </div>
        </div>
        </>
    );
};

export default UserCard;
