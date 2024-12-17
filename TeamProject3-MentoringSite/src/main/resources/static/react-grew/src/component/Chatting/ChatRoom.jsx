import React, { useEffect, useState } from 'react';
import * as ChattingApi from '../../api/ChattingApi.js';

const ChatRoom = ({ onRoomClick }) => {
    const memberNo = 1;
    const [isModalOpen, setIsModalOpen] = useState(false);  // 모달창의 열림/닫힘 상태를 관리하기 위한 state
    const [rooms, setRooms] = useState([]);
    const [currentRoom, setCurrentRoom] = useState(null);   // 수정 중인 채팅방 정보를 담는 state
    const [newRoomName, setNewRoomName] = useState('');     // 수정할 채팅방 이름을 저장하는 state

    useEffect(function(){
        (async ()=>{
            const responseJsonObject = await ChattingApi.listChatRoom(memberNo);
            console.log(responseJsonObject);
            if (responseJsonObject.status === 7010 && Array.isArray(responseJsonObject.data)) {
                // 각 채팅방 상태를 개별적으로 비교하여 필터링
                const activeRooms = responseJsonObject.data.filter((room) => { // filter()는 배열의 각 항목을 하나씩 검사하며, 주어진 콜백 함수에서 true를 반환하는 항목만 새로운 배열에 포함
                    return (
                        (room.chatRoomStatus === 7100 && room.chatRoomLeaveStatus === 7600) || (room.chatRoomStatus === 7200 && room.chatRoomLeaveStatus === 7600)
                    );
                });
                console.log(activeRooms);  // 필터링된 유효한 채팅방만 출력
                setRooms(activeRooms);  // 필터링된 채팅방만 setRooms에 설정
            }
        })();
    },[]);

    const openEditModal = (room) => {                       // 특정 채팅방을 수정하기 위해 모달을 열고, 해당 채팅방의 정보를 저장하는 함수
        setCurrentRoom(room);                               // 현재 수정 중인 채팅방 정보를 저장
        setNewRoomName(room.chatRoomName);                          // 수정할 채팅방 이름을 입력창에 보여줌
        setIsModalOpen(true);                               // 모달창 열기
    };

    const saveRoomName = async ()=>{                            // 채팅방 이름을 저장하는 함수 (모달창에서 이름 수정 후 저장 버튼 클릭 시 호출됨)
        if (currentRoom && newRoomName.trim()) {
                const responseJsonObject = await ChattingApi.changeChatRoomName(currentRoom.chatRoomNo, memberNo, newRoomName.trim());
                console.log(responseJsonObject.data);
                setRooms((prevRooms) =>
                    prevRooms.map((room) =>                                 //맵으로 동일한 번호를 찾아서 바뀐 정보를 새로고침없이 바로 반영
                        room.chatRoomNo === currentRoom.chatRoomNo
                            ? { ...room, chatRoomName: newRoomName.trim() } // 변경된 채팅방 정보로 교체
                            : room
                    )
                );
                setIsModalOpen(false); // 닫기
        }
    };
    
    const leaveRoom = async (roomNo) => {
        const responseJsonObject = await ChattingApi.leaveChatRoom(roomNo, memberNo);
        console.log(responseJsonObject);
    }

    return (
        <div>
            {/* Panel */}
            <div>
                <div className="chat-header">채팅방 목록</div>
                <ul className="chat-room-list">
                {rooms.length > 0 ? ( // 채팅방 리스트의 갯수로 화면 출력 판단 여부 
                    rooms.map((room) => (
                        <li
                            key={room.chatRoomNo} // 고유 키 설정 (React에서 반복문에 필수)
                            className="chat-room-item"
                            onClick={() => onRoomClick(room.chatRoomNo)} // 채팅방 클릭 시 부모 컴포넌트에 해당 채팅방 id 전달
                        >
                            <span className="room-name">{room.chatRoomName}</span>
                            <button
                                className="edit-button"
                                onClick={(e) => {
                                    e.stopPropagation(); // 부모 이벤트 (채팅방 클릭) 전파 방지
                                    openEditModal(room); // 채팅방 이름 수정 모달 열기
                                }}
                            >
                                수정
                            </button>
                            <button
                                className="leave-button" // 나가기 버튼
                                onClick={(e) => {
                                    e.stopPropagation(); // 부모 이벤트 전파 방지
                                    leaveRoom(room.chatRoomNo); // 나가기 기능 호출
                                }}
                            >
                                나가기
                            </button>
                        </li>
                    ))
                    ) : (
                        <div className="no-rooms-message">
                            현재 생성된 채팅방은 존재하지 않습니다.
                        </div>
                    )}
                </ul>
            </div>

            {/* 채팅방 이름 수정 panel */}
            {isModalOpen && (
                <div className={`modal ${isModalOpen ? 'active' : ''}`}>
                    <div
                        className="modal-content"
                        onClick={(e) => e.stopPropagation()}  // 클릭 이벤트 전파 방지
                    >
                        <div className="modal-header">채팅방 이름 변경</div>
                        <input
                            type="text"
                            value={newRoomName}
                            onChange={(e) => setNewRoomName(e.target.value)} // 입력된 값을 newRoomName에 저장
                            placeholder="새 채팅방 이름"
                        />
                        <button onClick={saveRoomName}>저장</button>
                        <button
                            className="close-button"
                            onClick={() => setIsModalOpen(false)} // 닫기 버튼
                        >
                            닫기
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default ChatRoom;
