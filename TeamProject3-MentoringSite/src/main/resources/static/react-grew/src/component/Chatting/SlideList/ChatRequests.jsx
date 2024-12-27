import React, { useEffect, useState } from 'react';
import * as ChattingApi from '../../../api/chattingApi.js';
import { useMemberAuth } from '../../../util/AuthContext.js';

const ChatRequests = () => {
    const [rooms, setRooms] = useState([]);
    const [currentPage, setCurrentPage] = useState(1); // 현재 페이지
    const [totalPages, setTotalPages] = useState(0); // 총 페이지 수

    const { token } = useMemberAuth();

    const chatRoomList = async (page) => {
        const responseJsonObject = await ChattingApi.waitListChatRoom(token, page, 7);
        setRooms(responseJsonObject.data.content);  // 필터링된 채팅방만 setRooms에 설정
        setTotalPages(responseJsonObject.data.totalPages);
    }

    useEffect(() => {
        chatRoomList(currentPage - 1); // 페이지 번호는 0부터 시작
    }, [currentPage]);

    const paginate = (pageNumber) => {
        setCurrentPage(pageNumber); // 페이지 변경
    };

    // 페이지 번호 버튼 생성
    const pageNumbers = [];
    for (let i = 1; i <= totalPages; i++) {
        pageNumbers.push(i);
    };




    return (
        <div>
            <ul className="chat-room-list">
            {rooms.length > 0 ? ( // 채팅방 리스트의 갯수로 화면 출력 판단 여부 
                rooms.map((room) => (
                    <li
                        key={room.chatRoomNo} // 고유 키 설정 (React에서 반복문에 필수)
                        className="chat-room-item"
                    >
                        <span className="room-name">{room.chatRoomName}</span>
                        <div className="button-container">
                            <button
                                className="edit-button"
                                onClick={(e) => {
                                    e.stopPropagation(); // 부모 이벤트 (채팅방 클릭) 전파 방지
                                }}
                            >
                                수락
                            </button>
                            <button
                                className="leave-button" // 나가기 버튼
                                onClick={(e) => {
                                    e.stopPropagation(); // 부모 이벤트 전파 방지
                                }}
                            >
                                거절
                            </button>
                        </div>
                    </li>
                ))
                ) : (
                    <div className="no-rooms-message">
                        현재 멘토에게 신청한 채팅방은 존재하지 않습니다.
                    </div>
                )}
            </ul>
            {/* 페이지네이션 */}
            <div className="pagination">
                {pageNumbers.map((number) => (
                    <button
                        key={number}
                        onClick={() => paginate(number)}
                        className={number === currentPage ? "active" : ""}
                    >
                        {number}
                    </button>
                ))}
            </div>
        </div>
    )
};

export default ChatRequests;
