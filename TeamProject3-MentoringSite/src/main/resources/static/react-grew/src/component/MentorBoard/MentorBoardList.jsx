import "../../css/styles.css";
import React, { useEffect, useState } from "react";
import api from '../../api/mentorBoardApi'; // API 경로를 올바르게 설정하세요
import '../../css/mentorBoard.css';

const MentorBoardList = () => {
    const [boards, setBoards] = useState([]); // 멘토 보드 목록
    const [status, setStatus] = useState(1); // 멘토 보드 상태 (기본값: 1)
    const [page, setPage] = useState(0); // 현재 페이지
    const [totalPages, setTotalPages] = useState(1); // 전체 페이지 수
    const [loading, setLoading] = useState(true); // 로딩 상태
    const [error, setError] = useState(null); // 에러 상태

    useEffect(() => {
        const fetchBoards = async () => {
            try {
                setLoading(true); // 로딩 상태 시작
                const data = await api.getMentorBoardsByStatus(status, page, 10); // 상태와 페이지에 따른 API 호출
                const boardList = Array.isArray(data.content) ? data.content : [];
                setBoards(boardList);
                setTotalPages(data.totalPages); // 전체 페이지 수 설정
            } catch (err) {
                console.error('멘토 보드 데이터를 가져오는 중 오류 발생:', err);
                setError('멘토 보드 데이터를 불러오는 데 실패했습니다.');
            } finally {
                setLoading(false); // 로딩 상태 종료
            }
        };

        fetchBoards();
    }, [status, page]); // 상태나 페이지가 변경될 때 데이터 가져오기

    // 로딩 중 메시지
    if (loading) {
        return <p>데이터를 불러오는 중입니다...</p>;
    }

    // 에러 메시지
    if (error) {
        return <p>{error}</p>;
    }

    // 데이터가 없는 경우
    if (boards.length === 0) {
        return <p>등록된 멘토 보드가 없습니다.</p>;
    }

    // 페이지네이션 버튼 렌더링
    const renderPagination = () => (
        <div className="pagination">
            <button
                disabled={page === 0}
                onClick={() => setPage((prevPage) => Math.max(prevPage - 1, 0))}
            >
                이전
            </button>
            <span>
                {page + 1} / {totalPages}
            </span>
            <button
                disabled={page + 1 === totalPages}
                onClick={() => setPage((prevPage) => Math.min(prevPage + 1, totalPages - 1))}
            >
                다음
            </button>
        </div>
    );

    return (
        <div className="mentor-board-list">
            <h1>멘토보드 리스트</h1>
            <div className="status-filter">
                <label>상태:</label>
                <select value={status} onChange={(e) => setStatus(Number(e.target.value))}>
                    <option value={1}>활성화된 보드</option>
                    <option value={0}>비활성화된 보드</option>
                </select>
            </div>
            {boards.map((board) => (
                <div className="mentor-board-item" key={board.mentorBoardNo}>
                    <h2>{board.mentorBoardTitle}</h2>
                    <p>{board.mentorBoardContent}</p>
                    {board.mentorBoardImage && (
                        <img
                            src={board.mentorBoardImage}
                            alt="Mentor Board"
                            className="mentor-board-image"
                        />
                    )}
                </div>
            ))}
            {renderPagination()}
        </div>
    );
};

export default MentorBoardList;
