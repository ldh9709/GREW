import { useMemberAuth } from "../../util/AuthContext";
import React, { useEffect, useState } from "react";
import * as adminApi from '../../api/adminApi';
import PagenationItem from '../PagenationItem';

export default function AdminMentorBoard () {
    const {token} = useMemberAuth();
    const [mentors, setMentors] = useState([]); //1. 초기설정 (게시판 목록)
    const [role, setRole] = useState("ALL"); // 기본적으로 ALL로 설정
    const [page, setPage] = useState(0); // 페이지 번호
    const [size, setSize] = useState(10); // 페이지 크기
    const [totalPages, setTotalPages] = useState(1); // 전체 페이지 수
    const [currentPage, setCurrentPage] = useState(1); // 현재 페이지
    
    const fetchBoards = async () => {// API 응답의 콘텐츠 설정
        try {
            const response = await adminApi.adminMentorBoard(token, page, size);
            // 응답 데이터 확인 후 처리
            if (response && response.data && response.data.content) {
                setMentors(response.data.content);
            } else {
                console.error("유효하지 않은 응답 데이터:", response);
                setMentors([]); // 데이터가 없을 경우 빈 배열로 설정
            }

            console.log(response.data);
            console.log(response);
        } catch (error) {
            console.error("게시판 데이터를 가져오는 중 오류 발생:", error);
            setMentors([]); // 오류 발생 시 빈 배열로 설정
        }
    }

    useEffect(()=>{
        fetchBoards();
    },[page, size]);

    const paginate = (pageNumber) => {
        setPage(pageNumber - 1);
        setCurrentPage(pageNumber);
    };

    return(
        <div className="admin-table-container">
           {/* <div className="dropdwon"> //드롭다운 
                <select className="dropdown-style" 
                value={role} onChange={(e)=> setRole(e.target.value)}>
                    <option value="ALL">전체 보기</option>
                    <option value="1">직무 상담</option>
                    <option value="2">학습 / 교육</option>
                    <option value="3">예술 / 창작</option>
                    <option value="4">창업 / 비즈니스</option>
                </select>
            </div>*/}
            <table className="admin-table">
                <thead>
                    <tr>
                        <th>    </th>
                        <th>제목</th>
                        {/*<th>내용</th>*/}
                        <th>작성일</th>
                        <th>조회수</th>
                       
                        <th>회원 번호</th>
                        
                        <th>카테고리</th>
                    </tr>
                </thead>
                <tbody>
                    {mentors && mentors.length > 0 ? (
                        mentors.map((mentor, index) => (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                {/*<td>{mentor.mentorBoardTitle}</td>*/}
                                <td>{mentor.mentorBoardContent.substring(0,50)}</td>
                                <td>{mentor.mentorBoardDate.substring(0,10)}</td>
                                <td>{mentor.mentorBoardViews}</td>
                                
                                <td>{mentor.memberNo}</td>
                                
                                <td>{mentor.categoryName}</td>
                            </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="10">게시판 데이터가 없습니다.</td>
                            </tr>
                    )}
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
    )
}