import { useMemberAuth } from "../../util/AuthContext";
import React, { useEffect, useState } from "react";
import * as adminApi from "../../api/adminApi";
import PagenationItem from "../PagenationItem";

function AdminMember() {
    const { token } = useMemberAuth();
    const [members, setMember] = useState([]);
    const [memberCount, setMemberCount] = useState(0);
    const [mentorRegisterCount, setMentorRegisterCount] = useState(0);
    const [role, setRole] = useState("ALL");
    const [order, setOrder] =useState(1);
    const [activeTab, setActiveTab] = useState("전체회원");
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0); 

    const fetchMembers = async (role, order, page, size) => {
        try {
            const response = await adminApi.adminMember(token, role, order, page, size);
            setMember(response.data.content);
            setTotalPages(response.data.totalPages);
            setMemberCount(response.data.totalElements);
        } catch (error) {
            console.log("회원 목록 조회 실패", error);
        }
    };
    

    // 멘토 상태별 회원 수를 조회 (목록은 업데이트하지 않음)
    const fetchMentorRegisterCount = async (status,order) => {
        try {
            const response = await adminApi.adminMentorByStatus(token, status, order, 0, 1); 
            setMentorRegisterCount(response.data.totalElements);
        } catch (error) {
            console.error("멘토 상태별 조회 실패", error);
        }
    };

    //멘토 상태별 회원 목록 조회
    const fetchMentorByStatus = async(status, order, page, size) => {
        try {
            const response = await adminApi.adminMentorByStatus(token, status, order, page, size)
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
                setRole("ALL")
                setOrder(1)
                paginate(1);
                fetchMembers(role, order, 0, 10);
                break;
                case "멘티회원":
                setRole("ROLE_MENTEE")
                setOrder(1)
                paginate(1);
                fetchMembers(role, order, 0,10);
                break;
                case "멘토회원":
                setRole("ROLE_MENTOR")
                setOrder(1)
                paginate(1);
                fetchMentorByStatus(3,order,0,10);
                break;
                case "멘토신청":
                setOrder(1)
                paginate(1);
                fetchMentorByStatus(2,order,0,10);
                break;
            default:
                break;
        }
    };
    
    const handleFilterChange = (event) => {
        setOrder(event.target.value);
        fetchMembers(role, order, 0, 10);
    }

    // 페이지 변경 시 데이터 갱신
    const paginate = (pageNumber) => {
        setCurrentPage(pageNumber);
    };
    // 실행 함수
    useEffect(() => {
        fetchMembers(role,order,currentPage-1,10);
    }, [currentPage,role,order]);

    // 실행 함수
    useEffect(() => {
        fetchMentorRegisterCount(2,1);
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
                        <span className="member-notification-badge">{mentorRegisterCount}</span>
                    </li>
                </ul>
                <div className="dropdown">
                    <select onChange={handleFilterChange} value={order}>
                        <option value={1}>최신순</option>
                        <option value={2}>이름순</option>                
                    </select>
                </div>
            </div>
            <div className="admin-table-container">
                <table className="admin-table">
                    <thead>
                        <tr>
                            <th>회원번호</th>
                            <th>이름</th>
                            <th>아이디</th>
                            <th>이메일</th>
                            <th>가입일자</th>
                            <th>가입방식</th>
                            <th>회원상태</th>
                            <th>누적신고 수</th>
                            { activeTab === '멘토신청' ? (<th>회원상세</th>) : ("")}
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
                                <td>{member.memberReportCount}</td>
                                {activeTab === '멘토신청' ?
                                (
                                <td>
                                    <button className="check">상세</button>
                                </td>
                                ): ("")}

                            </tr>
                        ))}
                    </tbody>
                </table>
                <span className="member-count">
                    총 : {activeTab === '멘토신청' ? mentorRegisterCount :memberCount}
                </span>
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
