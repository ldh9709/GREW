import { getCookie } from "../../util/cookieUtil";
import React, { useEffect, useState } from 'react'
import * as adminApi from '../../api/adminApi'

export default function AdminMember() {
  const boardCookie = getCookie("board");
  const token = boardCookie.accessToken;

  const [boards,setBoard] = useState([]);

  //목록 조회 함수<=여기부터 다시 만들기!!!!!
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

  // 회원 삭제 함수
  const handleDelete = () => {

  };

  


  return (
    <table className="member-table">
    <thead>
      <tr>
        <th>번호</th>
        <th>이름</th>
        <th>아이디</th>
        <th>이메일</th>
        <th>가입일자</th>
        <th>회원상태</th>
        <th>Role</th>
        <th>신고횟수</th>
        <th>관리</th>
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
                <td>{member.memberRole}</td>
                <td>{member.memberReportCount}</td>
                <td>
                    <button
                    className="delete"
                    onClick={() => handleDelete()}
                    >
                    삭제
                    </button>
                </td>
            </tr>
            </>
        ))}
    </tbody>
  </table>
  )
}