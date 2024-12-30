import { useMemberAuth } from "../../util/AuthContext";
import React, { useEffect, useState } from "react";
import * as adminApi from "../../api/adminApi";

function AdminMember() {
    const { token } = useMemberAuth();
    const [members, setMember] = useState([]);
    const [role, setRole] = useState("ALL");
    const [activeTab, setActiveTab] = useState("전체회원"); // 현재 활성화된 버튼 상태

    const fetchMembers = async (role, order) => {
        try {
            const response = await adminApi.adminMember(token, role, order);
            setMember(response.data);
            console.log(response.data);
            console.log(response);
        } catch (error) {
            console.log("회원 목록 조회 실패", error);
        }
    };

    const handleRoleChange = (event) => {
        const selectedRole = event.target.value;
        setRole(selectedRole);
        if (selectedRole === "ALL") {
            fetchMembers("ALL", 1); // 전체 회원 조회
        } else {
            fetchMembers(selectedRole, 1); // 선택된 역할에 맞는 목록 조회
        }
    };

    const handleTabClick = (tab) => {
        setActiveTab(tab); // 클릭된 탭을 활성화
        switch (tab) {
            case "전체회원":
                fetchMembers("ALL", 1);
                break;
            case "멘티회원":
                fetchMembers("ROLE_MENTEE", 1);
                break;
            case "멘토회원":
                fetchMembers("ROLE_MENTOR", 1);
                break;
            case "멘토신청":
                fetchMembers("ROLE_MENTOR_REQUEST", 1); // 멘토 신청 로직
                break;
            default:
                break;
        }
    };

    // 실행 함수
    useEffect(() => {
        fetchMembers("ALL", 1);
    }, []);

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
                        <span className="member-notification-badge">3</span>
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
                            <th>회원상태</th>
                            <th>역할</th>
                            <th>신고횟수</th>
                            <th>보기</th>
                        </tr>
                    </thead>
                    <tbody>
                        {members.map((member, index) => (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td>{member.memberName}</td>
                                <td>{member.memberId}</td>
                                <td>{member.memberEmail}</td>
                                <td>{member.memberJoinDate}</td>
                                <td>
                                    {member.memberStatus === 1
                                        ? "정상회원"
                                        : "탈퇴회원"}
                                </td>
                                <td>
                                    {member.memberRole === 1
                                        ? "ROLE_MENTEE"
                                        : "ROLE_MENTOR"}
                                </td>
                                <td>{member.memberReportCount}</td>
                                <td>
                                    <button className="check">상세</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </>
    );
}

export default AdminMember;
