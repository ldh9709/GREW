import { getCookie } from "../../util/cookieUtil";
import React, { useEffect, useState } from 'react';
import * as adminApi from '../../api/adminApi';

export const UserCard = () => {
    const memberCookie = getCookie("member");
    const token = memberCookie.accessToken;

    const [reports,setReport] = useState([]);//초기설정
  
    const fetchReports = async (token,filter,page) => {//fetct 목록 조회 함수 생성
        try {
            const response = await adminApi.getAdminReportList(token,filter,0);
            setReport(response.data);
            console.log(response);
            console.log(response.data);            
        } catch (error) {
            console.log('신고 목록 조회 실패', error);
        }
    };  
    
    useEffect(() => {
        if (token) {  // token이 있을 경우에만 실행
            fetchReports(token, 1);  // 1번 필터링을 예시로 사용
        }
    }, [token]);  // token이 변경될 때마다 실행

    /*
    신고 접수 흐름
    1. 신고타켓번호를 통해 신고 내용을 확인하러감
    2. '접수' 버튼 클릭 1) 신고처리완료
                          2)무고처리
  */

    return (
        <div className="admin-member-container">  
            <table className="member-table">
                <thead>
                    <tr>
                        <th>신고번호</th>
                        <th>타켓타입</th>
                        <th>신고타켓번호</th>
                        <th>신고타입</th>
                        <th>신고내용</th>
                        <th>신고일자</th>
                        <th>처리일자</th>
                        <th>신고상태</th>
                        <th>신고자</th>
                        <th>처리상황</th>
                    </tr>
                </thead>
                <tbody>
                    {reports && reports.length > 0 ? (
                        reports.map((report, index) => (
                            <tr key={index}>
                                <td>{index+1}</td>
                                <td>{report.reportType}</td>
                                <td>{report.reportTarget}</td>
                                <td>{report.reportReason}</td>
                                <td>{report.reportContent}</td>
                                <td>{report.reportDate}</td>
                                <td>{report.resolvedDate}</td>
                                <td>{report.reportStatus}</td>
                                <td>{report.memberNo}</td>
                                <td>
                                    <button className="check">접수</button>
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