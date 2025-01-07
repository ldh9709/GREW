import { useMemberAuth } from "../../util/AuthContext";
import React, { useEffect, useState } from "react";
import * as adminApi from '../../api/adminApi';
import PagenationItem from '../PagenationItem';

export function AdminInquiry() {
    const { token } = useMemberAuth();    
    const [boards, setBoards] = useState([]); // 게시판 목록
    const [category, setCategory] = useState("ALL"); // 카테고리 필터 
    const [page, setPage] = useState(0); // 페이지 번호
    const [size, setSize] = useState(10);//페이지 크기기
    const [totalPages, setTotalPages] = useState(1); // 전체 페이지 수
    const [currentPage, setCurrentPage] = useState(1); // 현재 페이지
    const [search, setSearch] = useState("");// 검색어
    
    // 게시판 목록 가져오기
    const fetchBoards = async () => {           
        try {
            let response; // response 선언
            if (category === "ALL") {
                response = await adminApi.adminInquiry(token, page, size);
            } else {
                response = await adminApi.adminCategoryInquiry(token, category, page, size);
            }            
            if (response && response.data) {
                setBoards(response.data.content || []);
                setTotalPages(response.data.totalPages || 0);
                console.log("응답 데이터: ",response);
                if(response.status===5300){
                }
            } else {
                throw new Error("응답 데이터가 유효하지 않습니다.");                
                setBoards([]);  // 응답이 없으면 빈 배열로 설정
                setTotalPages(0); // 총 페이지 수 초기화
            }
        } catch (error) {
            console.error("데이터를 불러오는 중 오류가 발생했습니다.", error);
        }        
    };
    useEffect(() => {
        fetchBoards();
    }, [page,size,category]);

    const paginate = (pageNumber) => {
        setPage(pageNumber - 1);
        setCurrentPage(pageNumber);
    };
    
    const handleCategoryChange = (event) => {
        const selectedCategory = event.target.value;        
        setCategory(selectedCategory);
        setCurrentPage(1);
    };
      
    
    const handleMoveInquiry = async (board) => {///inquiry/
        const url = `/inquiry/${board}`;
        window.open(url, "_blank");
    }

    const handleHideInquiry = async (inquiryNo) => {// "가려두기" 상태     
        const confirmation = window.confirm('게시글을 삭제하시겠습니까?')
        if(confirmation){
            const response = await adminApi.hideInquiry(token, inquiryNo);
            if (response.status===5200) {            
                fetchBoards();
            }        
        }
    }

    return (
        <div className="admin-table-container">          
        <div className="dropdown">
            <select  value={category} onChange={handleCategoryChange}>
                <option value="ALL">전체</option>
                <option value={1}>직무 상담</option>
                <option value={5}>학습/교육</option>
                <option value={9}>예술/창작</option>
                <option value={15}>창업/비지니스</option>
                <option value={22}>건강/운동</option>
            </select>
        </div>
            <table className="admin-table">
                <thead>
                    <tr>
                        <th>질문번호</th>
                        <th>제목</th>
                        <th>작성일</th>
                        <th>조회수</th>
                        <th>카테고리</th>
                        <th>작성자</th>
                        <th>삭제</th>
                    </tr>
                </thead>
                <tbody>
                    {boards.map((board, index) => (
                        <tr key={board.id || index}>
                            <td onClick={() => handleMoveInquiry(board.inquiryNo)}>{board.inquiryNo}</td>
                            <td onClick={() => handleMoveInquiry(board.inquiryNo)}>{board.inquiryTitle}</td>
                            <td onClick={() => handleMoveInquiry(board.inquiryNo)}>{board.inquiryDate.substring(0,10)}</td>
                            <td onClick={() => handleMoveInquiry(board.inquiryNo)}>{board.inquiryViews}</td>
                            <td onClick={() => handleMoveInquiry(board.inquiryNo)}>{board.categoryName}</td>
                            <td onClick={() => handleMoveInquiry(board.inquiryNo)}>{board.memberName}</td>
                            <td><button className="hide-button" onClick={(e) => { 
                                    e.stopPropagation(); // 클릭 이벤트가 부모(tr)로 전파되지 않도록 함
                                    handleHideInquiry(board.inquiryNo);
                                }}>삭제                                
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
                                paginate={paginate}
                            />
            </div>
        </div>
        );
    
}

export default AdminInquiry;
