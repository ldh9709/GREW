import { useMemberAuth } from "../../util/AuthContext";
import React, { useEffect, useState } from "react";
import * as adminApi from "../../api/adminApi";
import PagenationItem from "../PagenationItem";

function AdminMember() {
    const { token } = useMemberAuth();
    const [members, setMember] = useState([]);
    const [mentorRegisterCount, setMentorRegisterCount] = useState(0);
    const [role, setRole] = useState("ALL");
    const [order, setOrder] =useState(1);
    const [activeTab, setActiveTab] = useState("전체회원");
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0); 

    const fetchMembers = async (role, order, currentPage, size) => {
        try {
            const response = await adminApi.adminMember(token, role, order, currentPage-1, size);
            setMember(response.data.content);
            setTotalPages(response.data.totalPages);
            console.log(response)
        } catch (error) {
            console.log("회원 목록 조회 실패", error);
        }
    };
    

    // 멘토 상태별 회원 수를 조회 (목록은 업데이트하지 않음)
    const fetchMentorRegisterCount = async (status) => {
        try {
            const response = await adminApi.adminMentorByStatus(token, status, 0, 1); 
            setMentorRegisterCount(response.data.totalElements);
        } catch (error) {
            console.error("멘토 상태별 조회 실패", error);
        }
    };

    //멘토 상태별 회원 목록 조회
    const fetchMentorRegister = async(status, currentPage, size) => {
        try {
            const response = await adminApi.adminMentorByStatus(token, status, currentPage-1, size)
            setMember(response.data.content)
            console.log(response.data);
        } catch (error) {
            console.log("멘토 상태별 조회 실패",error)
        }
    }
    
    
    const handleTabClick = (tab) => {
        setActiveTab(tab); // 클릭된 탭을 활성화
        switch (tab) {
            case "전체회원":
                fetchMembers("ALL", 1,1,10);
                break;
            case "멘티회원":
                fetchMembers("ROLE_MENTEE", 1,1,10);
                break;
            case "멘토회원":
                fetchMembers("ROLE_MENTOR", 1,1,10);
                break;
            case "멘토신청":
                fetchMentorRegister(2,1,10);
                break;
            default:
                break;
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

    // 실행 함수
    useEffect(() => {
        fetchMembers(role,order,currentPage - 1,10);
        fetchMentorRegisterCount(2);
    }, [currentPage]);

    return (
        <>
            <div className="admin-member-submenu">
                <ul className="submenu">
                    <li
                        className={`submenu-item ${activeTab === "전체회원" ? "active" : ""}`}
                        onClick={() => handleTabClick("전체회원")}
                    >
                        전체회원
                    </li>
                    <li
                        className={`submenu-item ${activeTab === "멘티회원" ? "active" : ""}`}
                        onClick={() => handleTabClick("멘티회원")}
                    >
                        멘티회원
                    </li>
                    <li
                        className={`submenu-item ${activeTab === "멘토회원" ? "active" : ""}`}
                        onClick={() => handleTabClick("멘토회원")}
                    >
                        멘토회원
                    </li>
                    <li
                        className={`submenu-item ${activeTab === "멘토신청" ? "active" : ""}`}
                        onClick={() => handleTabClick("멘토신청")}
                    >
                        멘토신청
                        <span className="member-notification-badge">{mentorRegisterCount}</span>
                    </li>
                </ul>
            </div>
            <div className="admin-table-container">
                <table className="admin-table">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>이름</th>
                            <th>아이디</th>
                            <th>이메일</th>
                            <th>가입일자</th>
                            <th>가입방식</th>
                            <th>회원상태</th>
                            <th>권한</th>
                            <th>누적신고 수</th>
                        </tr>
                    </thead>
                    <tbody>
                        {members && members.map((member, index) => (
                            <tr key={index}>
                                <td>{member.memberNo}</td>
                                <td>{member.memberName}</td>
                                <td>{member.memberId}</td>
                                <td>{member.memberEmail}</td>
                                <td>{member.memberJoinDate.substring(0,10)}</td>
                                <td>
                                    {member.memberProvider === 'Email'
                                        ? "일반가입"
                                        : member.memberProvider}
                                </td>
                                <td>
                                    {member.memberStatus === 1
                                        ? "정상"
                                        : "탈퇴"}
                                </td>
                                <td>
                                    {member.mentorProfile
                                        ? "멘토, 멘티"
                                        : "멘티"}
                                </td>
                                <td>{member.memberReportCount}</td>
                                <td>
                                    <button className="check">상세</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
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
}

export default AdminMember;
