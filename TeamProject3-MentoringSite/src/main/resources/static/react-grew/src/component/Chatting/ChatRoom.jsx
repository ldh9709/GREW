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
            if(responseJsonObject.status === 7010){
                console.log(responseJsonObject.data[0]);
                setRooms(responseJsonObject);
            }else{
                alert('채팅창 리스트를 받아오지 못했습니다.');
            }
        })();
    },[]);

    const openEditModal = (room) => {                       // 특정 채팅방을 수정하기 위해 모달을 열고, 해당 채팅방의 정보를 저장하는 함수
        setCurrentRoom(room);                               // 현재 수정 중인 채팅방 정보를 저장
        setNewRoomName(room.name);                          // 수정할 채팅방 이름을 입력창에 보여줌
        setIsModalOpen(true);                               // 모달창 열기
    };

    const saveRoomName = () => {                            // 채팅방 이름을 저장하는 함수 (모달창에서 이름 수정 후 저장 버튼 클릭 시 호출됨)
        if (currentRoom && newRoomName.trim()) {
            setRooms((prevRooms) =>                         // 이전 rooms 리스트를 가져와서 수정
                prevRooms.map((room) =>                     // 모든 채팅방을 순회하며 수정할 채팅방만 이름을 변경
                    room.chatRoomNo === currentRoom.chatRoomNo
                        ? { ...room, name: newRoomName.trim() } // 현재 수정 중인 채팅방의 이름만 변경
                        : room
                )
            );
            setIsModalOpen(false);
        }
    };
    

    return (
        <div>
            {/* Panel */}
            <div>
                <div className="chat-header">채팅방 목록</div>
                <ul className="chat-room-list">
                    {/* 채팅방 목록을 화면에 출력 */}
                    {rooms.map((room) => (
                        <li
                            key={room.id} // 고유 키 설정 (React에서 반복문에 필수)
                            className="chat-room-item"
                            onClick={() => onRoomClick(room.id)} // 채팅방 클릭 시 부모 컴포넌트에 해당 채팅방 id 전달
                        >
                            <span className="room-name">{room.name}</span>
                            <button
                                className="edit-button"
                                onClick={(e) => {
                                    e.stopPropagation(); // 부모 이벤트 (채팅방 클릭) 전파 방지
                                    openEditModal(room); // 채팅방 이름 수정 모달 열기
                                }}
                            >
                                수정
                            </button>
                        </li>
                    ))}
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
