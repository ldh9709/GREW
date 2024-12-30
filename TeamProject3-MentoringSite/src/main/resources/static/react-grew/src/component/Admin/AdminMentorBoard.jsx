import { useMemberAuth } from "../../util/AuthContext";
import React, { useEffect, useState } from "react";
import * as adminApi from '../../api/adminApi';
import PagenationItem from '../PagenationItem';

export function AdminMentorBoard() {
    const { token } = useMemberAuth();    
    const [boards, setBoards] = useState([]); // 게시판 목록
    const [currentPage, setCurrentPage] = useState(0); // 현재 페이지
    const [totalPages, setTotalPages] = useState(0); // 전체 페이지 수
    const [category, setCategory] = useState("전체"); // 카테고리 필터

    // 게시판 목록 가져오기
    const fetchBoards = async () => {
        try {
            const response = await adminApi.adminInquiry(token, category, currentPage, 10); // 10은 페이지 크기

            // 응답 객체 검증
            if (response && response.data) {
                setBoards(response.data.content || []); // content가 없는 경우 빈 배열로 설정
                setTotalPages(response.data.totalPages || 0); // totalPages가 없는 경우 0으로 설정
            } else {
                throw new Error("응답 데이터가 유효하지 않습니다.");
            }
        } catch (error) {
            console.error("게시판 목록 가져오기 실패", error);
        }
    };

    // 페이지네이션 핸들러
    const paginate = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    // 카테고리 필터 변경 핸들러
    const handleCategoryChange = (event) => {
        setCategory(event.target.value);
        setCurrentPage(0); // 필터 변경 시 첫 페이지로 이동
    };

    useEffect(() => {
        if (token) {
            fetchBoards();
        }
    }, [token, currentPage, category]);

    return (
        <>
            <div className="admin-board-container">
                <div className="board-controls">
                    <label htmlFor="category-select">카테고리:</label>
                    <select id="category-select" value={category} onChange={handleCategoryChange}>
                        <option value="전체">전체</option>
                        <option value="직무 상담">직무 상담</option>
                        <option value="학습 / 교육">학습 / 교육</option>
                        <option value="예술 / 창작">예술 / 창작</option>
                        <option value="창업 / 비지니스">창업 / 비지니스</option>
                    </select>
                </div>

                <table className="admin-board-table">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>4</th>
                            <th>4</th>
                            <th>4</th>
                            <th>4</th>
                            <th>4</th>
                            <th>4</th>
                            <th>4</th>
                            <th>4</th>
                        </tr>
                    </thead>
                    <tbody>
                    {boards.map((board, index) => (
                        <>
                            <tr key={index}>
                                <td>{index+1}</td>
                                <td>{board.inquiryTitle}</td>
                                <td>{board.inquiryContent}</td>
                                <td>{board.inquiryDate}</td>
                                <td>{board.inquiryStatus}</td>
                                <td>{board.inquiryViews}</td>
                                <td>{board.parentsCategoryNo}</td>
                                <td>{board.parentsCategoryName}</td>
                                <td>{board.categoryNo}</td>
                                <td>{board.categoryName}</td>
                                <td>{board.memberNo}</td>
                                <td>{board.memberName}</td>
                            </tr>
                        </>
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

export default AdminMentorBoard;
