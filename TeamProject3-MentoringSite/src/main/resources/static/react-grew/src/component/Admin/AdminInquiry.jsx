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
    const [page, setPage] = useState(0); // 페이지 번호

    // 게시판 목록 가져오기
    const fetchBoards = async (categoryNo, page = 0, size = 10) => {   
        let response;
        if (categoryNo === "ALL") {
            response = await adminApi.adminInquiry(token, page, size);
        } else {
            response = await adminApi.adminCategoryInquiry(token, categoryNo, page, size);
        }
        if (response && response.data) {
            setBoards(response.data.content || []);
            setTotalPages(response.data.totalPages || 0);
        } else {
            throw new Error("응답 데이터가 유효하지 않습니다.");
        }        
    };

    const handleCategoryChange = (event) => {
        const selectedCategory = event.target.value;
        setCategory(selectedCategory);
        setCurrentPage(0);
    };

    const paginate = (pageNumber) => {
        setCurrentPage(pageNumber);
        setPage(pageNumber - 1);
    };

    useEffect(() => {
        fetchBoards(category, currentPage, 10);
    }, [category, currentPage]);

    
    const handleMoveInquiry = async (board) => {///inquiry/
        const url = `/inquiry/${board}`;
        window.open(url, "_blank");
    }

    const handleHideInquiry = async (inquiryNo) => {// "가려두기" 상태        
        const response = await adminApi.hideInquiry(token, inquiryNo);
        if (response.data.success) {
            // 상태 변경 성공 시 게시판 목록을 새로 고침
            fetchBoards(category, currentPage, 10);
        }        
    }

    return (
        <div className="admin-board-container">            
            <table className="admin-table">
                <thead>
                    <tr>
                        <th>질문번호</th>
                        <th>제목</th>
                        <th>작성일</th>
                        <th>조회수</th>
                        <th>카테고리</th>
                        <th>작성자</th>
                        <th>상태변경</th>
                    </tr>
                </thead>
                <tbody>
                    {boards.map((board, index) => (
                        <tr key={board.id || index}>
                            <td onClick={() => handleMoveInquiry(board.inquiryNo)}>{board.inquiryNo}</td>
                            <td onClick={() => handleMoveInquiry(board.inquiryNo)}>{board.inquiryTitle}</td>
                            <td onClick={() => handleMoveInquiry(board.inquiryNo)}>{new Date(board.inquiryDate).toLocaleDateString()}</td>
                            <td onClick={() => handleMoveInquiry(board.inquiryNo)}>{board.inquiryViews}</td>
                            <td onClick={() => handleMoveInquiry(board.inquiryNo)}>{board.categoryName}</td>
                            <td onClick={() => handleMoveInquiry(board.inquiryNo)}>{board.memberName}</td>
                            <td><button className="hide-button" onClick={(e) => { 
                                    e.stopPropagation(); // 클릭 이벤트가 부모(tr)로 전파되지 않도록 함
                                    handleHideInquiry(board.inquiryNo);
                                }}>가려두기                                
                            </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div className="admin-pagenation">
                    <PagenationItem
                        currentPage={currentPage}
                        totalPages={totalPages}
                        paginate={paginate}/>
            </div>
        </div>
    );
}

export default AdminInquiry;
