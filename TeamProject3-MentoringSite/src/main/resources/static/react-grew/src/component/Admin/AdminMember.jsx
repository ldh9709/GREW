import { useMemberAuth } from "../../util/AuthContext";
import React, { useEffect, useState } from "react";
import * as adminApi from "../../api/adminApi";
import PagenationItem from "../PagenationItem";

function AdminMember() {
    const { token } = useMemberAuth();
    const [loading, setLoading] = useState(false);
    const [members, setMember] = useState([]);
    const [memberCount, setMemberCount] = useState(0);
    const [mentorRegisterCount, setMentorRegisterCount] = useState(0);
    const [state, setState] = useState({
        role: "ALL",
        order: 1,
        mentorStatus: 2,
        activeTab: ""
    });
    // const [role, setRole] = useState("ALL");
    // const [order, setOrder] =useState(1);
    // const [mentorStatus, setmentorStatus] =useState(0);
    // const [activeTab, setActiveTab] = useState("전체회원");
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
            console.log(response.data)
            setMember(response.data.content)
        } catch (error) {
            console.log("멘토 상태별 조회 실패",error)
        }
    }

    // const updateState = (key, value) => {
    //     setState((prevState) => ({
    //         ...prevState,
    //         [key]: value,
    //     }));
    // };

    //탭 변경 시
    const handleTabClick = (tab) => {
        if (state.activeTab === tab) return; // 이미 활성화된 탭 클릭 시 무시
    
        const updatedState = {};
        switch (tab) {
            case "전체회원":
                updatedState.role = "ALL";
                updatedState.order = 1;
                updatedState.mentorStatus = 0;
                break;
            case "멘티회원":
                updatedState.role = "ROLE_MENTEE";
                updatedState.order = 1;
                updatedState.mentorStatus = 0;
                break;
            case "멘토회원":
                updatedState.role = "";
                updatedState.order = 1;
                updatedState.mentorStatus = 3;
                break;
            case "멘토신청":
                updatedState.role = "";
                updatedState.order = 1;
                updatedState.mentorStatus = 2;
                break;
            default:
                break;
        }
    
        setState((prev) => ({
            ...prev,
            ...updatedState,
            activeTab: tab,
        }));
        paginate(1);
    };


    const handleMentorDetail = () => {
        
    }

    //필터링 변경 시
    const handleFilterChange = (event) => {
        setState((prev) => ({
            ...prev,
            order: event.target.value
        }))
    }

    // 페이지 변경 시 데이터 갱신
    const paginate = (pageNumber) => {
        setCurrentPage(pageNumber);
    };
    // 실행 함수
    // useEffect(() => {
    //     fetchMentorRegisterCount(2,1)
    // }, []);

    // 실행 함수
   useEffect(() => {
    const fetchData = async () => {
        setLoading(true);
        try {
            if (state.activeTab === "멘토신청" || state.activeTab === "멘토회원") {
                await fetchMentorByStatus(state.mentorStatus, state.order, currentPage - 1, 10);
            } else {
                await fetchMembers(state.role, state.order, currentPage - 1, 10);
            }
        } catch (error) {
            console.error("데이터 로딩 실패", error);
        } finally {
            setLoading(false);
        }
    };


    fetchData();
   }, [state, currentPage]);
    
    
    return (
        <>
            <div className="admin-member-submenu">
                <ul className="submenu">
                    <li
                        className={`submenu-item ${state.activeTab === "전체회원" ? "active" : ""}`}
                        onClick={() => handleTabClick("전체회원")}
                    >
                        전체회원
                    </li>
                    <li
                        className={`submenu-item ${state.activeTab === "멘티회원" ? "active" : ""}`}
                        onClick={() => handleTabClick("멘티회원")}
                    >
                        멘티회원
                    </li>
                    <li
                        className={`submenu-item ${state.activeTab === "멘토회원" ? "active" : ""}`}
                        onClick={() => handleTabClick("멘토회원")}
                    >
                        멘토회원
                    </li>
                    <li
                        className={`submenu-item ${state.activeTab === "멘토신청" ? "active" : ""}`}
                        onClick={() => handleTabClick("멘토신청")}
                    >
                        멘토신청
                        <span className="member-notification-badge">{mentorRegisterCount}</span>
                    </li>
                </ul>
                <div className="dropdown">
                    <select onChange={handleFilterChange} value={state.order}>
                        <option value={1}>최신순</option>
                        <option value={2}>이름순</option>                
                    </select>
                </div>
            </div>
            <div className="admin-table-container member-table-container">
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
                            { state.activeTab === '멘토신청' ? (<th>신청내용</th>) : ("")}
                        </tr>
                    </thead>
                    {loading ? <tbody></tbody> :
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
                                    {state.activeTab ==="멘토신청" ?
                                    (
                                    <td>
                                        <button className="check"
                                        onClick={handleMentorDetail}
                                        >상세</button>
                                    </td>
                                    ): ("")}

                                </tr>
                            ))}
                        </tbody>
                    }
                </table>
                <span className="member-count">
                    총 : {state.activeTab === "멘토신청" ? mentorRegisterCount :memberCount}
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
