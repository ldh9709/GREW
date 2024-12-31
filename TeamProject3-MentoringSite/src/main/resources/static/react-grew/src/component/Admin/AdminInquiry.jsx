import { useMemberAuth } from "../../util/AuthContext";
import React, { useEffect, useState } from "react";
import * as adminApi from '../../api/adminApi';
import PagenationItem from '../PagenationItem';

export function AdminInquiry() {
    const { token } = useMemberAuth();    
    const [boards, setBoards] = useState([]); // 게시판 목록
    const [currentPage, setCurrentPage] = useState(0); // 현재 페이지
    const [totalPages, setTotalPages] = useState(0); // 전체 페이지 수
    const [category, setCategory] = useState("ALL"); // 카테고리 필터   

    // 게시판 목록 가져오기
    const fetchBoards = async (categoryNo, page = 0, size = 10) => {
        try {
            console.log("데이터 요청 시작:", { categoryNo, page, size });
            console.log("Token 확인:", token);

            let response;
            if (categoryNo === "ALL") {
                response = await adminApi.adminInquiry(token, page, size);
            } else {
                response = await adminApi.adminCategoryInquiry(token, categoryNo, page, size);
            }

            console.log("API 응답 데이터:", response);

            if (response && response.data) {
                setBoards(response.data.content || []);
                setTotalPages(response.data.totalPages || 0);
            } else {
                throw new Error("응답 데이터가 유효하지 않습니다.");
            }
        } catch (error) {
            console.error("게시판 목록 가져오기 실패:", error);
        }
    };

    const handleCategoryChange = (event) => {
        const selectedCategory = event.target.value;
        setCategory(selectedCategory);
        setCurrentPage(0);
    };

    const paginate = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    useEffect(() => {
        fetchBoards(category, currentPage, 10);
    }, [category, currentPage]);

    return (
        <div className="admin-board-container">
            {/*<div className="dropdown">
                <select className="dropdown-style" value={category} onChange={handleCategoryChange}>
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
                        <th>번호</th>
                        <th>제목</th>
                       {/* <th>내용</th>*/}
                        <th>작성일</th>
                        
                        <th>조회수</th>
                        <th>카테고리</th>
                        <th>작성자</th>
                    </tr>
                </thead>
                <tbody>
                    {boards.map((board, index) => (
                        <tr key={board.id || index}>
                            <td>{index + 1}</td>
                            <td>{board.inquiryTitle}</td>
                           {/* <td>{board.inquiryContent}</td>*/}
                            <td>{board.inquiryDate}</td>
                            
                            <td>{board.inquiryViews}</td>
                            <td>{board.categoryName}</td>
                            <td>{board.memberName}</td>
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
    );
}

export default AdminInquiry;
