import { getCookie } from "../../util/cookieUtil";
import React, { useEffect, useState } from 'react'
import * as adminApi from '../../api/adminApi'

export default function AdminMember() {
    const memberCookie = getCookie("member");
    const token = memberCookie.accessToken;

    const [members,setMember] = useState([]);
    const [role, setRole] = useState("ROLE_MENTEE"); // 기본적으로 ROLE_MENTEE로 설정

    //목록 조회 함수
    const fetchMembers = async (role,order) => {
        try {
            const response = await adminApi.adminMember(token,role,order);
            setMember(response.data);
            
        } catch (error) {
            console.log('회원 목록 조회 실패', error);
        }
    }

    //실행 함수
    useEffect(()=>{
        fetchMembers('ROLE_MENTEE',1);
    },[])

    // 드롭다운 선택에 따른 목록 조회
    const handleRoleChange = (event) => {
        const selectedRole = event.target.value;
        setRole(selectedRole);
        fetchMembers(selectedRole, 1); // 선택된 역할에 맞는 목록 조회
    };

    // 회원 삭제 함수
    const handleDelete = () => {

    };

    // 지도자 목록
{/*    const getMentee = () => {
        setRole("ROLE_MENTOR"); // ROLE_MENTOR로 변경하여 멘토 목록을 조회
        fetchMembers("ROLE_MENTOR", 1); // 1: 가입 순으로 정렬
    }*/}

    return (
        <div className="admin-member-container">
            {/* 드롭다운 */}
            <div className="dropdown">
                <select onChange={handleRoleChange} value={role}>
                    <option value="ROLE_MENTEE">MENTEE</option>
                    <option value="ROLE_MENTOR">MENTOR</option>
                    <option value="전체">WITHDRAWAL</option>
                    <option value="WITHDRAW">REMOVAL</option>
                </select>
            </div>
            <table className="member-table">
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
                    {members.map((member,index)=>(
                        <>
                        <tr key={index}>    
                            <td>{index+1}</td>
                            <td>{member.memberName}</td>
                            <td>{member.memberId}</td>
                            <td>{member.memberEmail}</td>
                            <td>{member.memberJoinDate}</td>
                            <td>{member.memberStatus === 1 ? "정상회원" : "탈퇴회원"}</td>
                            <td>{member.memberRole === 1 ? "ROLE_MENTEE":"ROLE_MENTOR"}</td>
                            <td>{member.memberReportCount}</td>
                            <td>
                                <button
                                className="check"
                                onClick={() => handleDelete()}>
                                상세
                                </button>
                            </td>
                        </tr>
                        </>
                    ))}
                </tbody>
            </table>
        </div>
    )
}