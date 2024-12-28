import React, { useEffect, useState } from 'react';
import * as ChattingApi from '../../../api/chattingApi.js';
import { useMemberAuth } from '../../../util/AuthContext.js';

const ChatRequests = () => {
    const [rooms, setRooms] = useState([]);
    const [currentPage, setCurrentPage] = useState(1); // 현재 페이지
    const [totalPages, setTotalPages] = useState(0); // 총 페이지 수

    const { token } = useMemberAuth();

    const chatRoomList = async (page) => {
        const responseJsonObject = await ChattingApi.waitListChatRoom(token, page, 8);
        if (responseJsonObject.status === 7010 && Array.isArray(responseJsonObject.data.content)) {
            // 각 채팅방 상태를 개별적으로 비교하여 필터링
            const activeRooms = responseJsonObject.data.content.filter((room) => { // filter()는 배열의 각 항목을 하나씩 검사하며, 주어진 콜백 함수에서 true를 반환하는 항목만 새로운 배열에 포함
                return (
                    (room.chatRoomStatus === 7000 && room.chatRoomLeaveStatus === 7500) || (room.chatRoomStatus === 7000 && room.chatRoomLeaveStatus === 7600)
                );
            });
            console.log(activeRooms);
            setRooms(activeRooms);  // 필터링된 채팅방만 setRooms에 설정
            setTotalPages(responseJsonObject.data.totalPages);
        }
    }

    // 채팅방 상태 변경 핸들러
    const handleChatRoomUpdate = async (chatRoomNo, action) => {
        if (action === "active") {
            await ChattingApi.activeChatRoom(chatRoomNo);
        } else if (action === "reject") {
            await ChattingApi.rejectedChatRoom(chatRoomNo);
        } else if (action === "cancel") {
            await ChattingApi.canceledChatRoom(chatRoomNo);
        }
        // 상태 변경 후 채팅방 목록 새로고침
        chatRoomList(currentPage - 1);
    };

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
                        {room.chatRoomName.includes("채팅방 신청이 도착했습니다.") ? (
                            // 조건 1: 신청이 도착했을 때
                            <>
                            <button
                                className="edit-button"
                                onClick={(e) => {
                                e.stopPropagation(); // 부모 이벤트 (채팅방 클릭) 전파 방지
                                handleChatRoomUpdate(room.chatRoomNo, "active");
                                }}
                            >
                                수락
                            </button>
                            <button
                                className="leave-button"
                                onClick={(e) => {
                                e.stopPropagation(); // 부모 이벤트 전파 방지
                                handleChatRoomUpdate(room.chatRoomNo, "reject");
                                }}
                            >
                                거절
                            </button>
                            </>
                        ) : (
                            // 조건 2: 채팅방을 신청했을 때
                            <button
                            className="leave-button"
                            onClick={(e) => {
                                e.stopPropagation(); // 부모 이벤트 전파 방지
                                handleChatRoomUpdate(room.chatRoomNo, "cancel");
                            }}
                            >
                            신청 취소
                            </button>
                        )}
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
