import { getCookie } from "../../util/cookieUtil";
import React, { useEffect, useState } from 'react'
import * as adminApi from '../../api/adminApi';


function AdminMember() {
    const memberCookie = getCookie("member");
    const token = memberCookie.accessToken;

    const [members,setMember] = useState([]);
    //const [role, setRole] = useState("ROLE_MENTEE");  기본적으로 ROLE_MENTEE로 설정
    const [role, setRole] = useState("ALL"); // 기본적으로 ALL로 설정 (전체 회원 조회)

    //목록 조회 함수
    const fetchMembers = async (role,order) => {
        try {
            const response = await adminApi.adminMember(token,role,order);
            setMember(response.data);
            console.log(response);
        } catch (error) {
            console.log('회원 목록 조회 실패', error);
        }
    }

    //실행 함수
    useEffect(()=>{
        //fetchMembers('ROLE_MENTEE',1);
        fetchMembers('ALL', 1);  // 페이지가 처음 렌더링될 때는 전체 회원을 불러옵니다.
    },[])

    // 드롭다운 선택에 따른 목록 조회
    const handleRoleChange = (event) => {
        const selectedRole = event.target.value;
        setRole(selectedRole);
        if (selectedRole === "ALL") {
            fetchMembers("ALL", 1);  // 전체 회원 조회
        } else {
            fetchMembers(selectedRole, 1);  // 선택된 역할에 맞는 목록 조회
        }
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
                    {/*<option value="ALL">ALL</option>   전체 회원 */}
                    <option value="ROLE_MENTEE">MENTEE</option>  {/* 멘티 */}
                    <option value="ROLE_MENTOR">MENTOR</option>  {/* 멘토 */}
                    
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


export default AdminMember;