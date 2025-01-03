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
    const [search, setSearch] = useState("");// 검색어
    
    const fetchBoards = async () => {
        let response;
        if (search.trim()) {
            response = await adminApi.adminMentorBoardWithSearch(token, search, page, size);
        } else {// 검색어가 없는 경우            
            response = await adminApi.adminMentorBoard(token, page, size);
        }
        if (response && response.data && response.data.content) {
            setMentors(response.data.content);
            setTotalPages(response.data.totalPages); // 전체 페이지 수 설정
        } else {            
            setMentors([]); // 데이터가 없을 경우 빈 배열로 설정
        }        
    };

    useEffect(()=>{
        fetchBoards();
    },[page, size, search]);

    const paginate = (pageNumber) => {
        setPage(pageNumber - 1);
        setCurrentPage(pageNumber);
    };

    const handleMoveMentor = async (mentorBoardNo) => {///mentor-board/detail/5
        const url = `/mentor-board/detail/${mentorBoardNo}`;// 게시글 이동
        window.open(url, "_blank"); // 새 탭에서 열기
    };

    const handleSearchChange = (e) => {
        setSearch(e.target.value); // 검색어 변경 시
    };

    const handleSearchSubmit = (e) => {
        e.preventDefault(); // 폼 제출 시 페이지 리로드 방지
        fetchBoards(); // 검색 결과를 가져오기
    };

    return(
        <div className="admin-table-container">
            <div className="search-container">
                <form onSubmit={handleSearchSubmit}>
                    <input
                        type="text"
                        value={search}
                        onChange={handleSearchChange}
                        placeholder="검색어를 입력하세요"
                    />
                    <button type="submit">검색</button>
                </form>
            </div>
            <table className="admin-table">
                <thead>
                    <tr>
                        <th>회원 번호</th>
                        <th>제목</th>
                        <th>작성일</th>
                        <th>조회수</th>
                        <th>카테고리</th>
                    </tr>
                </thead>
                <tbody>
                    {mentors && mentors.length > 0 ? (
                        mentors.map((mentor, index) => (
                            <tr key={index} onClick={() => handleMoveMentor(mentor.mentorBoardNo)}>                                
                                <td>{mentor.memberNo}</td>
                                <td>{mentor.mentorBoardTitle}</td>
                                <td>{mentor.mentorBoardDate.substring(0,10)}</td>
                                <td>{mentor.mentorBoardViews}</td>
                                <td>{mentor.categoryName}</td>
                            </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="6">게시판 데이터가 없습니다.</td>
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
    );
}