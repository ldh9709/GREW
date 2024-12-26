import React, { useEffect, useState } from 'react';
import { getCookie } from '../../../util/cookieUtil.js';
import * as ChattingApi from '../../../api/chattingApi.js';

const ChatRequests = ({ onRoomClick }) => {
    const [rooms, setRooms] = useState([]);
    const [currentPage, setCurrentPage] = useState(1); // 현재 페이지
    const [totalPages, setTotalPages] = useState(0); // 총 페이지 수

    const memberCookie = getCookie("member");
    const token = memberCookie.accessToken;

    const chatRoomList = async (page) => {
        console.log('토큰'+token);
        const responseJsonObject = await ChattingApi.waitListChatRoom(token, page, 7);
        console.log(responseJsonObject);
        console.log(responseJsonObject.data.content);
        if (responseJsonObject.status === 7010 && Array.isArray(responseJsonObject.data.content)) {
            // 각 채팅방 상태를 개별적으로 비교하여 필터링
            const activeRooms = responseJsonObject.data.content.filter((room) => { // filter()는 배열의 각 항목을 하나씩 검사하며, 주어진 콜백 함수에서 true를 반환하는 항목만 새로운 배열에 포함
                return (
                    room.chatRoomStatus === 7000
                );
            });
            console.log(activeRooms);  // 필터링된 유효한 채팅방만 출력
            setRooms(activeRooms);  // 필터링된 채팅방만 setRooms에 설정
            setTotalPages(responseJsonObject.data.totalPages);
        }
    }

    useEffect(() => {
        chatRoomList(currentPage - 1); // 페이지 번호는 0부터 시작
    }, [currentPage]);

    const leaveRoom = async (roomNo) => {
        const responseJsonObject = await ChattingApi.leaveChatRoom(roomNo, token);
        console.log(responseJsonObject);
        await chatRoomList(currentPage - 1); // 현재 페이지 갱신

    };

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
                        onClick={() => onRoomClick(room.chatRoomNo, room.chatRoomName)} // 채팅방 클릭 시 부모 컴포넌트에 해당 채팅방 id 전달
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
                                    leaveRoom(room.chatRoomNo); // 나가기 기능 호출
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
